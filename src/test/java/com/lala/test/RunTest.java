package com.lala.test;

import static io.restassured.RestAssured.*;
import static com.lala.test.GlobalData.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.lala.test.requests.CreateJSONPayLoad;


/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers.
 *  1. Place Order
	2. Fetch Order Details
	3. Driver to Take the Order
	4. Driver to Complete the Order
	5. Cancel Order 
 * 
 * */


public class RunTest {
	
	
	String OrderID;
		
		@Test(priority=1, enabled=true)
		public void verifyPlaceOrder(){
			
			RestAssured.baseURI="http://localhost:51544/v1/orders";
			
			RequestSpecification request = RestAssured.given();
			 
			 JSONObject requestParams = CreateJSONPayLoad.createJSONPayloadNewOrder();
			 request.body(requestParams.toJSONString());
			 Response response = request.post("");
			 
			 int statusCode = response.getStatusCode();
			 Assert.assertEquals(statusCode, 201);
//			 String successCode = response.jsonPath().get("SuccessCode");
//			 Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
			 
			 String msgBody= response.body().asString();
			 System.out.println("Message Body: "+msgBody);
			
						
		}/*-- END OF METHOD --*/
		
		@Test(priority=1, enabled=false)
		public void verifyFetchOrderWhenOrderExist(){
			
			OrderID="1";
			Response resp = get(baseURL+OrderID);
			
			int code=resp.getStatusCode();
			
			System.out.println("Status Code is: "+code);
			Assert.assertEquals(code, 200);
		}/*--END OF METHOD---*/
		
		@Test(priority=2, enabled=false)
		public void verifyFetchOrderWhenOrderDoesNotExist(){
			
			OrderID="10";
			Response resp = get(baseURL+OrderID);
			
			int code=resp.getStatusCode();
			
			System.out.println("Status Code is: "+code);
			Assert.assertEquals(code, 404);
		}/*--END OF METHOD---*/
		
		@Test(priority=-1, enabled=false)
		public void verifyDriveToTakeOrder(){
			
			OrderID="4";
			RestAssured.baseURI=baseURL+OrderID;
			
			JSONObject requestParams = new JSONObject();
			 requestParams.put("id", OrderID); // Cast
			 requestParams.put("status", "ONGOING");
			 requestParams.put("ongoingAt", "2018-09-01T14:53:26.000Z");
			
			 
			// request.body(requestParams.toJSONString());
			 Response response = put("/take");
			 
			 int statusCode = response.getStatusCode();
			 System.out.println("Status Code is: "+statusCode);
			 Assert.assertEquals(statusCode, 404);
			
			
			
	
		}/*--END OF METHOD---*/
		
		@Test(priority=-1, enabled=false)
		public void verifyDriveToComplete(){
			
			OrderID="4";
			RestAssured.baseURI=baseURL+OrderID;
			
			JSONObject requestParams = new JSONObject();
			 requestParams.put("id", OrderID); // Cast
			 requestParams.put("status", "COMPLETED");
			 requestParams.put("ongoingAt", "2018-09-01T14:53:26.000Z");
			
			 
			// request.body(requestParams.toJSONString());
			 Response response = put("/complete");
			 
			 int statusCode = response.getStatusCode();
			 System.out.println("Status Code is: "+statusCode);
			 Assert.assertEquals(statusCode, 200);
			
			 String msgBody= response.body().asString();
			 System.out.println("Message Body: "+msgBody);
			
			
			
	
		}/*--END OF METHOD---*/
		
		@Test(priority=-1, enabled=false)
		public void verifyCancelOrder(){
			
			OrderID="6";
			RestAssured.baseURI=baseURL+OrderID;
			
			JSONObject requestParams = new JSONObject();
			 requestParams.put("id", OrderID); // Cast
			 requestParams.put("status", "CANCELLED");
			 requestParams.put("ongoingAt", "2018-09-01T14:53:26.000Z");
			
			 
			// request.body(requestParams.toJSONString());
			 Response response = put("/cancel");
			 
			 int statusCode = response.getStatusCode();
			 System.out.println("Status Code is: "+statusCode);
			 Assert.assertEquals(statusCode, 200);
			
			 String msgBody= response.body().asString();
			 System.out.println("Message Body: "+msgBody);
			
			
			
	
		}/*--END OF METHOD---*/
		
		
		
		
		
		
	
}/*--END OF CLASS---*/


