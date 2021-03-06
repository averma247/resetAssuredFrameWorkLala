package com.lala.test;

import static com.lala.requests.GlobalData.LOGGER;
import static com.lala.requests.GlobalData.NewOrderID;
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
 * Test Scenarios Covers for Cancel Order Test
 * 
 * */


public class CancelOrderTest {

	private PlaceOrderTest placeorder=new PlaceOrderTest();
	private RESTApiCalls restapicalls= new RESTApiCalls();


	//Reading Config file before executing test cases.
	@BeforeTest
	public void suitelalaTestNGTest(){


		LOGGER.log(Level.INFO, "Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  

	//Verifying Canceling newly place order
	@Test(priority=1, enabled=true)
	public void verifyCancelNewOrder(){



		LOGGER.log(Level.INFO, "Verifying Canceling newly place order");

		placeorder.verifyNewOrder();
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID",NewOrderID);
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
			LOGGER.log(Level.INFO, "Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);

		LOGGER.log(Level.INFO, "---------- Test Case is Passed -----------------");


	}/*--END OF METHOD---*/

	//Verifying Canceling previously placed order.
	@Test(priority=1, enabled=true)
	public void verifyCancelExistingOrder(){



		LOGGER.log(Level.INFO, "Verifying Canceling previously placed order.");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", prop.getProperty("existingOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
			LOGGER.log(Level.INFO, "Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);

		LOGGER.log(Level.INFO, "Test Case is Passed.");


	}/*--END OF METHOD---*/

	//Verifying Canceling order that doesn't exist.
	@Test(priority=1, enabled=true)
	public void verifyCancelNonExistingOrder(){



		LOGGER.log(Level.INFO, "Verifying Cancelling and Existing Order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", "-1");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
			LOGGER.log(Level.INFO, "Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),404);
		LOGGER.log(Level.INFO, "---------- Test Case is Passed -------------");

	}/*--END OF METHOD---*/

	//Verifying Canceling On Going order.
	@Test(priority=1, enabled=true)
	public void verifyCancelOrderForOnGoingOrder(){



		LOGGER.log(Level.INFO, "Verifying Cancelling and OnGoing Order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", prop.getProperty("ongoingOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.INFO, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);
		LOGGER.log(Level.INFO, "---------- Test Case is Passed -------------");


	}/*--END OF METHOD---*/

	//Verifying Canceling order is already cancelled.
	@Test(priority=1, enabled=true)
	public void verifyCancelOrderForAlreadyCancelledOrder(){



		LOGGER.log(Level.INFO, "Verifying Cancelling and Existing Order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", prop.getProperty("cancelledOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");

		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);

		LOGGER.log(Level.INFO, "---------- Test Case is Passed -------------");

	}/*--END OF METHOD---*/

	//Verifying Canceling order is already completed.
	@Test(priority=1, enabled=true)
	public void verifyCancelOrderForCompletedOrder(){



		LOGGER.log(Level.INFO, "Verifying Cancelling of completed Order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", prop.getProperty("completedOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"Order status is COMPLETED already"), true);

		LOGGER.log(Level.INFO, "---------- Test Case is Passed -------------");

	}/*--END OF METHOD---*/



}/*--END OF CLASS---*/
