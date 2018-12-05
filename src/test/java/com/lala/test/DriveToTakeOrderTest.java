package com.lala.test;

import static com.lala.requests.GlobalData.LOGGER;
import static com.lala.requests.GlobalData.prop;

import java.util.HashMap;
import java.util.logging.Level;

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
 * Test Scenarios Covers for Drive to Take Order
 * 
 * */



public class DriveToTakeOrderTest{

	private PlaceOrderTest placeorder=new PlaceOrderTest();
	private RESTApiCalls restapicalls= new RESTApiCalls();

	@BeforeTest
	public void suitelalaTestNGTest(){

		
		LOGGER.log(Level.INFO, "Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  


	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeOrder(){

		
		LOGGER.log(Level.INFO, "Verifying Take away new order");
		placeorder.verifyNewOrder();

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID", GlobalData.NewOrderID);
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		
		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);


	}/*--END OF METHOD---*/


	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeExistingOrder(){

		
		LOGGER.log(Level.INFO, "Verifying Take away Existing order");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID",prop.getProperty("existingOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		
		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);
		LOGGER.log(Level.INFO, "---------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/


	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeAlreadyCancelledOrder(){

		
		LOGGER.log(Level.INFO, "Verifying Take away already cancelled OrderID ");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID",prop.getProperty("cancelledOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.INFO, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		
		LOGGER.log(Level.INFO, "Verifying status code");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"Order status is not ASSIGNING"), true);
		
		LOGGER.log(Level.INFO, "----------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/


	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeAlreadyCompletedOrder(){

		
		LOGGER.log(Level.INFO, "Verifying Take away operationon on Completed OrderID ");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID",prop.getProperty("completedOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

	
		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"Order status is not ASSIGNING"), true);
		
		LOGGER.log(Level.INFO, "----------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/

	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeAlreadyOnGoingOrder(){

		
		LOGGER.log(Level.INFO, "----------- Test Case is Completed -----------");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID",prop.getProperty("ongoingOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		
		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"Order status is not ASSIGNING"), true);
		
		LOGGER.log(Level.INFO, "----------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/

	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeOnOrderDoesNotExist(){

		
		LOGGER.log(Level.INFO, "Verifying Take away on Order doesnot exist ");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID","-1");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		
		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),404);
		
		LOGGER.log(Level.INFO, "----------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/



}
