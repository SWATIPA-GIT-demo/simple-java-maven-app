import files.MyUtilities;
import io.restassured.path.json.JsonPath;

public class JSONParsingTest {

	public static void main(String[] args) {
		
		String inputJSON = MyUtilities.returnJSONString();
		JsonPath jsp = MyUtilities.convertRawStringToJSON(inputJSON);
		
		//Print No of courses returned by API
		System.out.println("Print No of courses returned by API");
		System.out.println(jsp.getInt("courses.size()"));
		System.out.println("");
		
		//Print Purchase Amount
		System.out.println("Print Purchase Amount");
		System.out.println(jsp.getString("dashboard.purchaseAmount"));
		System.out.println("");
		
		//Print Title of the first course
		System.out.println("Print Title of the first course");
		System.out.println(jsp.get("courses[0].title").toString());
		System.out.println("");
		
		//Print All course titles and their respective Prices
		System.out.println("Print All course titles and their respective Prices");
		for(int i=0; i<jsp.getInt("courses.size()"); i++) {
			System.out.println(jsp.get("courses["+i+"].title").toString());
			System.out.println(jsp.getInt("courses["+i+"].price"));
			System.out.println("");
		}
		
		//Print no of copies sold by RPA Course
		System.out.println("Print no of copies sold by RPA Course");
		
		for(int i=0; i<jsp.getInt("courses.size()"); i++) {
			if(jsp.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
				System.out.println(jsp.getString("courses["+i+"].copies"));
				break;
			}
		}

	}

}
