package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

	RequestSpecification req;
	
	public RequestSpecification createRequestSpecAddPlace() throws IOException {
		PrintStream file = new PrintStream(new FileOutputStream("Log.txt"));
		req = new RequestSpecBuilder().setBaseUri(readFromPropFile("BaseUrl"))
				.addFilter(RequestLoggingFilter.logRequestTo(file))
				.addFilter(ResponseLoggingFilter.logResponseTo(file))
				.setContentType(ContentType.JSON)
				.addQueryParam("key", "qaclick123")
				.build();
		
		return req;
	}
	
	
	public static String readFromPropFile(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream input = new FileInputStream("C:\\SwatizWorkplace\\E2EFrameworkProject\\src\\test\\java\\resources\\global.properties");
		prop.load(input);
		return prop.getProperty(key);
	}
}
