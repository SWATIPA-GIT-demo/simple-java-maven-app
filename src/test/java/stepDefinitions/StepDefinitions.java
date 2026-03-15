package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.Location;
import pojo.Maps;
import resources.PlaceAPIResource;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils{

	RequestSpecification request;
	Response res;
	String response;
	Maps map;
	
	@Given("Payload for add place API is added as {string}, {string}, {string}")
	public void payload_for_add_place_api_is_added_as_name_address_language(String name, String address, String language) {
		
		TestDataBuild data = new TestDataBuild();
		map = data.addPlacePayloadBuild(name,address,language);
	    
	}

	@When("User calls the {string} through {string} Http request")
	public void user_calls_the_through_http_request(String resourceName, String method)  throws IOException {
		PlaceAPIResource path = PlaceAPIResource.valueOf(resourceName);
		String resource = path.getResource();
		request = createRequestSpecAddPlace();
		if(method.equalsIgnoreCase("post")) {
			res = given().log().all().spec(request)
					.body(map)
					.when().log().all().post(resource)
					.then().log().all().extract().response();
		}
		else if(method.equalsIgnoreCase("get")) {
			res = given().log().all().spec(request)
					.body(map)
					.when().log().all().get(resource)
					.then().log().all().extract().response();
		}
	}

	@Then("User gets {int} as status code for successful response")
	public void user_gets_as_status_code_for_successful_response(int statusCode) {
		System.out.println(res.getStatusCode());
		assertEquals(statusCode, res.getStatusCode());
	}

	@Then("User gets {string} as {string} in response body")
	public void user_gets_as_in_response_body(String key, String value) {
		JsonPath jsp = new JsonPath(res.asString());
		System.out.println(jsp.getString(key));
		System.out.println(value);
		assertEquals(jsp.getString(key),value);
	}


}
