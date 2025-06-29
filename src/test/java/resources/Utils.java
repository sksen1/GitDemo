package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		System.out.println("A+B+C");
		System.out.println("E+F+G");
		
		if(req==null)
		{
		  PrintStream log1 = new PrintStream(new FileOutputStream("logging.txt"));
		  
	//  RestAssured.baseURI="https://rahulshettyacademy.com";
		  req = new RequestSpecBuilder()
		            .setBaseUri(getGlobalValue("baseUrl"))
		            .addQueryParam("key", "qaclick123")
		            .addFilter(RequestLoggingFilter.logRequestTo(log1))
		            .addFilter(ResponseLoggingFilter.logResponseTo(log1))
		            .setContentType(ContentType.JSON)
		            .build();
		  return req;
		  
		}
		return req;
	}
	
	public static  String getGlobalValue(String key) throws IOException
	{
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream("C:\\UBI_Practice\\APIFramework\\src\\test\\java\\resources\\global.properties");
			prop.load(fis);
			return prop.getProperty(key);
			
	}
	
	
//	public String getJsonPath(Response response, String key) {
//	    String resp = response.asString();
//	    System.out.println("Response body: " + resp); // Debug print
//
//	    if (resp == null || resp.trim().isEmpty()) {
//	        throw new IllegalArgumentException("Response body is null or empty. Cannot parse JSON.");
//	    }
//
//	    JsonPath js = new JsonPath(resp);
//	    return js.getString(key);
//	}
	
	public String getJsonPath(Response response, String key) {
	    if (response == null) {
	        throw new IllegalArgumentException("❌ Response object is null. API might not have been called properly.");
	    }

	    String resp = response.asString();
	    System.out.println("🔍 Response body received:\n" + resp); // Clear debug print

	    if (resp == null || resp.trim().isEmpty()) {
	        throw new IllegalArgumentException("❌ Response body is null or empty. Cannot parse JSON.");
	    }

	    JsonPath js = new JsonPath(resp);

	    // Optional: Validate that the key exists before returning
	    if (js.get(key) == null) {
	        throw new IllegalArgumentException("❌ Key '" + key + "' not found in response.");
	    }

	    return js.getString(key);
	}



}
