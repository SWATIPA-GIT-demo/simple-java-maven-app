import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.CourseDetails;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import files.MyUtilities;

public class OAuthAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Auth Server
		String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope", "trust")
				.when().post("/oauthapi/oauth2/resourceOwner/token")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jsp = MyUtilities.convertRawStringToJSON(response);
		String token = jsp.getString("access_token");
		System.out.println("Bearer token = "+token);
		
		//Get Course details
		
		CourseDetails detailsResponse = given().queryParam("access_token", token)
				.when().get("/oauthapi/getCourseDetails")
				.then().log().all().assertThat().statusCode(401).extract().response().as(CourseDetails.class);
		
		
		System.out.println(detailsResponse.getInstructor());
		System.out.println(detailsResponse.getUrl());
		System.out.println(detailsResponse.getCourses().getWebAutomation().get(0).getCourseTitle());
		System.out.println(detailsResponse.getCourses().getWebAutomation().get(0).getPrice());
		
		String expected = "Rest Assured Automation using Java";
		String actual = "";
		int size = detailsResponse.getCourses().getApi().size();
		
		for(int index=0; index<size; index++) {
			actual = detailsResponse.getCourses().getApi().get(index).getCourseTitle();
			if(actual.equalsIgnoreCase(expected)) {
				System.out.println(detailsResponse.getCourses().getApi().get(index).getPrice());
				break;
			}
		}
		
		size = detailsResponse.getCourses().getWebAutomation().size();
		String[] expectedArr = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		List<String> actualArr = new ArrayList<String>();
		for(int index=0; index<size; index++) {
			actualArr.add(detailsResponse.getCourses().getWebAutomation().get(index).getCourseTitle());
		}
		for(int index=0; index<size; index++) {
			System.out.println(actualArr.get(index));;
		}
		Assert.assertTrue(actualArr.equals(Arrays.asList(expectedArr)));
		System.out.println(actualArr.equals(Arrays.asList(expectedArr)));
	}

}
