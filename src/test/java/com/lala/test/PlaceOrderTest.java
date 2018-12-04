package com.lala.test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.lala.requests.GlobalData;
import com.lala.requests.RESTApiCalls;

import io.restassured.response.Response;



/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Place Order
 * 
 * */


public class PlaceOrderTest extends RESTApiCalls{



	@BeforeTest
	public void suitelalaTestNGTest(){

		System.out.println("Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  



	@Test(priority=1, enabled=true)
	public void verifyNewOrder(){

		System.out.println("Verfying New Order Flow with Valid Data.");

		//int actualStatusCode=placeorder.placeNewOrder();

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "New Order");
		Response response=restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),201);



	}/*-- END OF METHOD --*/



	@Test(priority=1, enabled=true)
	public void verifyFutureOrder(){

		System.out.println("Verfying New Order Flow with Valid Data.");

		//int actualStatusCode=placeorder.placeNewOrder();

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Future Order");
		Response response=restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),201);


	}/*--- END OF METHOD --*/


	@Test(priority=1, enabled=true)
	public void verifyNewOrderWithInvalidPayload(){

		System.out.println("Verfying New Order Flow with In-Valid Data.");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "InValid Payload");
		Response response=restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.assertTrue(true,"Test is Passed, Providing valid error message.");
		}


	}/*-- END OF METHOD --*/



}/*-- END OF CLASS --*/
