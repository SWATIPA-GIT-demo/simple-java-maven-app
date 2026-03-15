import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.MyUtilities;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DynamicJSONRealExamples {
	
	@Test(dataProvider = "AddBookData")
	public void dynamicJSONValidation(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().log().all().header("Content-Type","application/json")
		.body(MyUtilities.returnJSONStringAddBook(isbn, aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jsp = MyUtilities.convertRawStringToJSON(response);
		System.out.println(jsp.getString("ID"));
		String ID = jsp.getString("ID");
		String delResponse = given().log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+ID+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "")
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jsp1 = MyUtilities.convertRawStringToJSON(delResponse);
		System.out.println(jsp1.getString("msg"));
		
	}
	
	@DataProvider(name = "AddBookData")
	public Object[][] addBookData(){
		return new Object[][] {{"bcde","227"},{"defg","229"},{"fghi","231"}};
	}

	@Test
	public void staticJSONValidation() throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		System.out.println(Files.readAllBytes(Paths.get("C:\\Users\\kesha\\Downloads\\staticData.json")));
		System.out.println("*************************************************************************************");
		String s = new String(Files.readAllBytes(Paths.get("C:\\Users\\kesha\\Downloads\\staticData.json")));
		System.out.println(s);
		String response = given().log().all().header("Content-Type","application/json")
		.body(s)
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		JsonPath jsp = MyUtilities.convertRawStringToJSON(response);
		System.out.println(jsp.getString("ID"));
		String ID = jsp.getString("ID");
		String delResponse = given().log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+ID+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "")
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath jsp1 = MyUtilities.convertRawStringToJSON(delResponse);
		System.out.println(jsp1.getString("msg"));
		
	}
}
