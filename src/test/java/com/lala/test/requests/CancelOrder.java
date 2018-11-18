package com.lala.test.requests;

import static com.lala.test.GlobalData.baseURL;
import static io.restassured.RestAssured.put;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CancelOrder {

	
	public int cancelOrderRequest(String OrderID){

		RestAssured.baseURI=baseURL+OrderID;
		
		JSONObject requestParams = new JSONObject();
		 requestParams.put("id", OrderID); // Cast
		 requestParams.put("status", "CANCELLED");
		 requestParams.put("ongoingAt", "2018-09-01T14:53:26.000Z");
		
		 
		// request.body(requestParams.toJSONString());
		 Response response = put("/cancel");
		 
		 int statusCode = response.getStatusCode();
		 System.out.println("Status Code is: "+statusCode);
			
		 String msgBody= response.body().asString();
		 System.out.println("Message Body: "+msgBody);
		 
		 return statusCode;
		
		
	}
	
	
	public void verifyStatusCode(int actualStatusCode, int expectedStatusCode ){
		
		try{
			System.out.println("Verifying response status code.");
		
		System.out.println("Actual status code: "+actualStatusCode);
		System.out.println("Expected response status code: "+expectedStatusCode);
		
		
		Assert.assertEquals(actualStatusCode, expectedStatusCode);
		 System.out.println("Actual and expected response status code is matched");
		 
//		 String successCode = response.jsonPath().get("SuccessCode");
//		 Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
		}
		catch(AssertionError e){
			
			System.out.println(e.getMessage()); 
			System.out.println("Test is failed, status code not matched please check you Cancel request payload");
			Assert.fail("Test is failed, status code not matched please check you Cancel request payload");
			
		}
		
	}
	
	
}
