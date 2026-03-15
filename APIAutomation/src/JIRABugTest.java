import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import java.io.File;

import static io.restassured.RestAssured.*;

import files.MyUtilities;

public class JIRABugTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://swatipattnaik18.atlassian.net";
		
		//Create Bug
//		String response = given().header("Content-Type","application/json")
//				.header("Authorization","Basic c3dhdGlwYXR0bmFpazE4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBSandwekhiVmlYU0tBLVZUVTM4STlQWXZTNGVKdVBQVFZuUF9pYWVCckxFcDlMdVl6LUZsTXYxSThqcG9Ca2xHOGRJNGxfekRNamQzeF9MTFJkaTd0RTB3b1VKdUNXREVreHF3R1NtOVJvTGdHeW1kT2ZXdjNvMEFVazRCN3pfa2VtdjhQbF9lY1Y2dExNTkx0MjlTbHRYTS1zUnNkNERVUnNWNEt6VU5MbUU9OEVBQzBFMEI=")
//				.body("{\r\n"
//						+ "  \"fields\": {\r\n"
//						+ "    \"project\": {\r\n"
//						+ "      \"key\": \"SCRUM1\"\r\n"
//						+ "    },\r\n"
//						+ "    \"summary\": \"Dropdowns are not working---Automation\",\r\n"
//						+ "    \"description\": {\r\n"
//						+ "      \"type\": \"doc\",\r\n"
//						+ "      \"version\": 1,\r\n"
//						+ "      \"content\": [\r\n"
//						+ "        {\r\n"
//						+ "          \"type\": \"paragraph\",\r\n"
//						+ "          \"content\": [\r\n"
//						+ "            {\r\n"
//						+ "              \"text\": \"Creating of an issue using the REST API.\",\r\n"
//						+ "              \"type\": \"text\"\r\n"
//						+ "            }\r\n"
//						+ "          ]\r\n"
//						+ "        }\r\n"
//						+ "      ]\r\n"
//						+ "    },\r\n"
//						+ "    \"issuetype\": {\r\n"
//						+ "      \"name\": \"Bug\"\r\n"
//						+ "    },\r\n"
//						+ "    \"priority\": {\r\n"
//						+ "      \"name\": \"High\"\r\n"
//						+ "    }\r\n"
//						+ "  }\r\n"
//						+ "}")
//				.when().post("/rest/api/3/issue")
//				.then().log().all().assertThat().statusCode(201).extract().response().asString();
//		JsonPath jsp = MyUtilities.convertRawStringToJSON(response);
//		String projId = jsp.getString("id");
//		
//		//Add Attachment
//		
//		String responseAttach = given().pathParam("key", projId)
//				.header("Authorization","Basic c3dhdGlwYXR0bmFpazE4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBSandwekhiVmlYU0tBLVZUVTM4STlQWXZTNGVKdVBQVFZuUF9pYWVCckxFcDlMdVl6LUZsTXYxSThqcG9Ca2xHOGRJNGxfekRNamQzeF9MTFJkaTd0RTB3b1VKdUNXREVreHF3R1NtOVJvTGdHeW1kT2ZXdjNvMEFVazRCN3pfa2VtdjhQbF9lY1Y2dExNTkx0MjlTbHRYTS1zUnNkNERVUnNWNEt6VU5MbUU9OEVBQzBFMEI=")
//				.header("X-Atlassian-Token","no-check")
//				.multiPart(new File("C:\\Users\\kesha\\Downloads\\Git1.jpg"))
//				.when().post("/rest/api/3/issue/{key}/attachments")
//				.then().log().all().assertThat().statusCode(200).extract().response().asString();
//		JsonPath jsp1 = MyUtilities.convertRawStringToJSON(responseAttach);
//		System.out.println(jsp1.getString("filename"));
//				
		//GetBug
		
		String responseGet = given().pathParam("key", "10042")
				.header("Authorization","Basic c3dhdGlwYXR0bmFpazE4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBSandwekhiVmlYU0tBLVZUVTM4STlQWXZTNGVKdVBQVFZuUF9pYWVCckxFcDlMdVl6LUZsTXYxSThqcG9Ca2xHOGRJNGxfekRNamQzeF9MTFJkaTd0RTB3b1VKdUNXREVreHF3R1NtOVJvTGdHeW1kT2ZXdjNvMEFVazRCN3pfa2VtdjhQbF9lY1Y2dExNTkx0MjlTbHRYTS1zUnNkNERVUnNWNEt6VU5MbUU9OEVBQzBFMEI=")
				.when().get("/rest/api/3/issue/{key}")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath jsp2 = MyUtilities.convertRawStringToJSON(responseGet);
		System.out.println(jsp2.getString("id"));
		System.out.println(jsp2.getString("fields.attachment[0].filename"));
		
		//Delete Bug
		//GetBug
		
				String responseDel = given().pathParam("key", "10042")
						.header("Authorization","Basic c3dhdGlwYXR0bmFpazE4QGdtYWlsLmNvbTpBVEFUVDN4RmZHRjBSandwekhiVmlYU0tBLVZUVTM4STlQWXZTNGVKdVBQVFZuUF9pYWVCckxFcDlMdVl6LUZsTXYxSThqcG9Ca2xHOGRJNGxfekRNamQzeF9MTFJkaTd0RTB3b1VKdUNXREVreHF3R1NtOVJvTGdHeW1kT2ZXdjNvMEFVazRCN3pfa2VtdjhQbF9lY1Y2dExNTkx0MjlTbHRYTS1zUnNkNERVUnNWNEt6VU5MbUU9OEVBQzBFMEI=")
						.when().delete("/rest/api/3/issue/{key}")
						.then().log().all().assertThat().statusCode(204).extract().response().asString();
				
	}

}
