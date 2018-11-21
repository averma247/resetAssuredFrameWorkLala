package com.lala.test.requests;

import static com.lala.test.GlobalData.prop;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.put;

import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DriveToCompleteOrder {

	public int driveToCompleteRequest(String OrderID){
		RestAssured.baseURI=prop.getProperty("baseURL");

		RequestSpecification request = RestAssured.given();

		JSONObject requestParams=CreateJSONPayLoad.readyJSONPayloadFromFile("Complete");

		if(requestParams==null){
			System.out.println("readyJSONPayloadFromFile returned null value");
			Assert.fail("readyJSONPayloadFromFile returned null value");
		}

		requestParams.put("id", OrderID); 


		/*JSONObject requestParams = new JSONObject();
		 requestParams.put("id", OrderID); // Cast
		 requestParams.put("status", "COMPLETED");
		 requestParams.put("ongoingAt", "2018-09-01T14:53:26.000Z");*/


		request.body(requestParams.toJSONString());
		Response response = put("/v1/orders/"+OrderID+"/complete");
		System.out.println("Status Code is: "+response.getStatusCode());

		String msgBody= response.body().asString();
		System.out.println("Message Body: "+msgBody);

		return response.getStatusCode();


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
			System.out.println("Test is failed, status code not matched please check your Drive to complete request payload");
			Assert.fail("Test is failed, status code not matched please check your Drive to complete request payload");

		}

	}

}
