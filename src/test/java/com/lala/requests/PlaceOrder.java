package com.lala.requests;

import org.json.simple.JSONObject;
import static com.lala.test.GlobalData.*;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlaceOrder {

	Response response;

	public int placeNewOrder(){
		try { 

			RestAssured.baseURI=prop.getProperty("baseURL");

			//		
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = CreateJSONPayLoad.readyJSONPayloadFromFile("NewOrder");
			request.body(requestParams.toJSONString());
			response = request.post(prop.getProperty("placeOrderURL"));

			int statusCode = response.getStatusCode();
			System.out.println("Status Code is: "+statusCode);
			String msgBody= response.body().asString();
			System.out.println("Message Body: "+msgBody);

			JsonPath jsonPathEvaluator = response.jsonPath();
			System.out.println("Order ID from Response " + jsonPathEvaluator.get("id"));
			NewOrderID=(jsonPathEvaluator.get("id")).toString();
			System.out.println("Response Data " + jsonPathEvaluator.prettyPrint());
			return statusCode;

		}
		catch(Exception e){

			System.out.println(e.getMessage()); 
			System.out.println("Test is failed while sending the JSON payload.");
			return 0;

		}

	}


	public int placeFutureOrder(){
		try { 

			RestAssured.baseURI=prop.getProperty("baseURL");

			//		
			RequestSpecification request = RestAssured.given();

			JSONObject requestParams = CreateJSONPayLoad.readyJSONPayloadFromFile("FutureOrder");
			request.body(requestParams.toJSONString());
			response = request.post(prop.getProperty("placeOrderURL"));

			int statusCode = response.getStatusCode();
			System.out.println("Status Code is: "+statusCode);
			String msgBody= response.body().asString();
			System.out.println("Message Body: "+msgBody);

			JsonPath jsonPathEvaluator = response.jsonPath();
			System.out.println("Order ID from Response " + jsonPathEvaluator.get("id"));
			NewOrderID=(jsonPathEvaluator.get("id")).toString();
			System.out.println("Response Data " + jsonPathEvaluator.prettyPrint());
			return statusCode;

		}
		catch(Exception e){

			System.out.println(e.getMessage()); 
			System.out.println("Test is failed while sending the JSON payload.");
			return 0;

		}

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
			System.out.println("Test is failed, status code not matched, please check your JSON payload data.");
			Assert.fail("Test is failed, status code not matched, please check your JSON payload data.");

		}

	}

}