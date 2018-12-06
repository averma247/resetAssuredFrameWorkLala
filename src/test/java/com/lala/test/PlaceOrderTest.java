package com.lala.test;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.lala.requests.GlobalData;
import static com.lala.requests.GlobalData.LOGGER;
import com.lala.requests.RESTApiCalls;

import io.restassured.response.Response;



/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Place Order
 * 
 * */


public class PlaceOrderTest {
	
	RESTApiCalls restapicalls= new RESTApiCalls();
	

	@BeforeTest
	public void suitelalaTestNGTest(){
		
		LOGGER.log(Level.INFO, "Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  

//Verfying New Order Flow with Valid Data

	@Test(priority=1, enabled=false)
	public void verifyNewOrder(){
		
		LOGGER.log(Level.INFO, "Verfying New Order Flow with Valid Data.");

		//int actualStatusCode=placeorder.placeNewOrder();

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "New Order");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),201);



	}/*-- END OF METHOD --*/

//Verfying New Order Flow with Valid Data and time stamp

	@Test(priority=1, enabled=false)
	public void verifyFutureOrder(){

		LOGGER.log(Level.INFO, "Verfying New Order Flow with Valid Data and time stamp.");

		//int actualStatusCode=placeorder.placeNewOrder();

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Future Order");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE,"Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		LOGGER.log(Level.INFO,"Verifying status code");
		Assert.assertEquals(response.getStatusCode(),201);


	}/*--- END OF METHOD --*/

//Verfying New Order Flow with In-Valid request
	@Test(priority=1, enabled=true)
	public void verifyNewOrderWithInvalidPayload(){

		LOGGER.log(Level.INFO,"Verfying New Order Flow with In-Valid Request.");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "InValid Payload");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.INFO,"Test is Passed, Providing valid error message.");
			Assert.assertTrue(true,"Test is Passed, Providing valid error message.");
			
		}
		
		LOGGER.log(Level.INFO,"Verifying status code");
		Assert.assertEquals(response.getStatusCode(),400);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"error in field(s): stops"), true);
		

	}/*-- END OF METHOD --*/



}/*-- END OF CLASS --*/
