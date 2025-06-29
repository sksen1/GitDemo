package stepdefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;


public class StepDefination extends Utils {

    RequestSpecification res;
    ResponseSpecification resp;
    Response response;
    static String place_id;
    JsonPath js;
    
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload(String name,String language,String address) throws IOException {
    	
        res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
    }
    
     @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String apiName, String method) {
    	 //Constructor willbe called with value of resources which you pass.
    	
    	APIResources resourceAPI= APIResources.valueOf(apiName);
    	System.out.println(resourceAPI.getResources());
     //   String endpoint = getApiEndpoint(apiName);

        resp = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        
        if (method.equalsIgnoreCase("POST")) {
          response = res.when().post(resourceAPI.getResources()).then().spec(resp).extract().response();
      } 
        else if (method.equalsIgnoreCase("GET")) {
          response = res.when().get(resourceAPI.getResources()).then().spec(resp).extract().response();
      } else if (method.equalsIgnoreCase("PUT")) {
          response = res.when().put(resourceAPI.getResources()).then().spec(resp).extract().response();
      } else if (method.equalsIgnoreCase("DELETE")) {
          response = res.when().delete(resourceAPI.getResources()).then().spec(resp).extract().response();
      } else {
          throw new IllegalArgumentException("Unsupported HTTP method: " + method);
      }
        System.out.println("📥 Raw Response: " + response.asString());
        
    }
     
     //Alternative Aproch to call API //
     
//     @When("user calls {string} with {string} http request")
//     public void user_calls_with_http_request(String apiName, String method) {
//     	 //Constructor willbe called with value of resources which you pass.
//     	
//     
//         String endpoint = getApiEndpoint(apiName);
//
//         resp = new ResponseSpecBuilder()
//                 .expectStatusCode(200)
//                 .expectContentType(ContentType.JSON)
//                 .build();
//         
//         if (method.equalsIgnoreCase("POST")) {
//           response = res.when().post(endpoint).then().spec(resp).extract().response();
//       } 
//         else if (method.equalsIgnoreCase("GET")) {
//           response = res.when().get(endpoint).then().spec(resp).extract().response();
//       } else if (method.equalsIgnoreCase("PUT")) {
//           response = res.when().put(endpoint).then().spec(resp).extract().response();
//       } else if (method.equalsIgnoreCase("DELETE")) {
//          response = res.when().delete(endpoint).then().spec(resp).extract().response();
//      } else {
//           throw new IllegalArgumentException("Unsupported HTTP method: " + method);
//       }
//     }


    @Then("the API call is Succes with status code {int}")
    public void the_api_call_is_successful_with_status_code(Integer statusCode) {
//        assertEquals(statusCode.intValue(), response.getStatusCode());
//        System.out.println("Delete Response: " + response.asString());
    	assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
//        String responseString = response.asString();
//        System.out.println("🔍 Response Body Before Parsing: " + responseString);  // Add this line
        assertEquals( getJsonPath(response, keyValue),expectedValue	);
    }

    

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
    
    	//request spec
    	place_id =  getJsonPath(response,"place_id");
    	if (place_id == null) {
    	    throw new RuntimeException("Place ID not found in response");
    	}
        res = given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_with_http_request(resource,"GET");
        String  actualName=getJsonPath(response,"name");
        assertEquals(actualName,expectedName);

    }
    

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
    	res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
        
    }
    @Then("the API call got sucess with status code {int}")
    public void the_api_call_got_sucess_with_status_code(Integer int1) {
    //	assertEquals(response.getStatusCode(),200);
       
    }







    // Helper method to map API name to endpoint
    public String getApiEndpoint(String apiName) {
        switch (apiName) {
            case "AddPlaceAPI":
                return "/maps/api/place/add/json";
            case "GetPlaceAPI":
                return "/maps/api/place/get/json";
            case "DeletePlaceAPI":
                return "/maps/api/place/delete/json";
            default:
                throw new IllegalArgumentException("Unknown API name: " + apiName);
        }
    }
}
