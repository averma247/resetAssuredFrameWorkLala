package com.lala.test;

import static com.lala.requests.GlobalData.LOGGER;
import static com.lala.requests.GlobalData.prop;

import java.util.HashMap;
import java.util.logging.Level;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.lala.requests.GlobalData;
import com.lala.requests.RESTApiCalls;

import io.restassured.response.Response;

/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Drive To Complete Order Test
 * 
 * */


public class DriveToCompleteOrderTest {

	private DriveToTakeOrderTest drivetotakeorder= new DriveToTakeOrderTest();
	private RESTApiCalls restapicalls= new RESTApiCalls();

	@BeforeTest
	public void suitelalaTestNGTest(){


		LOGGER.log(Level.INFO, "Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  

	//Placing New Order and Changing order status to Ongoing
	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForNewOrder(){


		LOGGER.log(Level.INFO, "Placing New Order and Changing order status to Ongoing");
		drivetotakeorder.verifyDriveToTakeOrder();


		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", GlobalData.NewOrderID);
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);

	}/*--END OF METHOD---*/

	//Verifying flow for order doesn't exist
	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForNonExistingOrder(){


		LOGGER.log(Level.INFO, "Verifying flow for order doesnot exist.");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", "-1");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),404);


	}/*--END OF METHOD---*/

	//Verifying flow for order on Assigned state.
	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForOnderOnAssignedState(){


		LOGGER.log(Level.INFO, "Verifying flow for order on Assigned state.");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", prop.getProperty("assignedOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"Order status is not ONGOING"), true);
		LOGGER.log(Level.INFO, "----- Test case Passed -------------");



	}/*--END OF METHOD---*/

	//Verifying flow for order on Assigned state
	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForCompletedOrder(){


		LOGGER.log(Level.INFO, "Verifying flow for order on Assigned state.");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", prop.getProperty("completedOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){

			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"Order status is not ONGOING"), true);


	}/*--END OF METHOD---*/


	//Verifying flow for order on Cancelled state
	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForCancelOrder(){


		LOGGER.log(Level.INFO, "Verifying flow for order on Cancelled state.");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", prop.getProperty("cancelledOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){

			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"Order status is not ONGOING"), true);


	}/*--END OF METHOD---*/


}
