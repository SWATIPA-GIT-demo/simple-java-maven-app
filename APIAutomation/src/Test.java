import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.Maps;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import files.MyUtilities;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//RestAssured.baseURI = "https://rahulshettyacademy.com";
		Location l = new Location();
		Maps maps = new Maps();
		List<String> input = new ArrayList<String>();
		input.add("shoe park");
		input.add("shop");
		l.setLat(-38.383494);
		l.setLng(33.427362);
		maps.setLocation(l);
		maps.setAccuracy(50);
		maps.setAddress("Bangalore");
		maps.setPhone_number("(+91) 9148754404");
		maps.setLanguage("French-IN");
		maps.setName("SwatiKeshari house");
		maps.setWebsite("http://google.com");
		maps.setTypes(input);
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
		ResponseSpecification res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		
		//AddPlace
		String response = given().log().all().spec(req)
		.body(maps)
		.when().log().all().post("maps/api/place/add/json")
		.then().log().all().spec(res).assertThat().body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();		
		
		JsonPath jsp = new JsonPath(response);
		String pid = jsp.getString("place_id");
		String newAddress = "SwatiKeshari Home";
		//EditPlace
		given().log().all().spec(req)
		.body("{\r\n"
				+ "\"place_id\":\""+ pid +"\",\r\n"
				+ "\"address\":\""+ newAddress +"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().spec(res).assertThat().body("msg", equalTo("Address successfully updated"));
		
		String actualResponse = given().log().all().spec(req).queryParam("place_id",pid)
				.when().get("maps/api/place/get/json")
				.then().spec(res).extract().response().asString();
		
		JsonPath jsp1 = new JsonPath(actualResponse);
		System.out.println(jsp1.getString("address"));
		Assert.assertEquals(newAddress,jsp1.getString("address"));
	}

}
