import org.testng.Assert;
import org.testng.annotations.Test;

import files.MyUtilities;
import io.restassured.path.json.JsonPath;

public class SumValidationTest {
	
	@Test
	public void sumValidation() {
		JsonPath jsp = MyUtilities.convertRawStringToJSON(MyUtilities.returnJSONString());
		int sum = 0;
		
		for(int i=0; i<jsp.getInt("courses.size()"); i++) {
			int amount = jsp.getInt("courses["+i+"].price")*jsp.getInt("courses["+i+"].copies");
			sum += amount;
		}
		
		System.out.println("Calculated Amount = "+ sum);
		System.out.println("Actual Amount = "+ jsp.getInt("dashboard.purchaseAmount"));
		Assert.assertEquals(sum, jsp.getInt("dashboard.purchaseAmount"));
	}
	

}
