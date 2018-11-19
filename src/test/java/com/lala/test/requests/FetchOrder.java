package com.lala.test.requests;

import static com.lala.test.GlobalData.prop;
import static io.restassured.RestAssured.get;

import org.testng.Assert;

import com.lala.test.GlobalData;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FetchOrder {

	public static int fetchOrderRequest(String OrderID){
		System.out.println("Sending Request to fetch the order detials.");
		RestAssured.baseURI=prop.getProperty("baseURL");
		Response resp = get("/v1/orders/"+OrderID);		
		return resp.getStatusCode();
		
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
			System.out.println("Test is failed, status code not matched please check you GET request payload");
			Assert.fail("Test is failed, status code not matched please check you GET request payload");
			
		}
		
	}
	
	
}
