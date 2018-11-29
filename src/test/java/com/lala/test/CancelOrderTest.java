package com.lala.test;

import static com.lala.test.GlobalData.prop;
import static com.lala.test.GlobalData.NewOrderID;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.lala.requests.RESTApiCalls;

import io.restassured.response.Response;

/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Cancel Order Test
 * 
 * */


public class CancelOrderTest extends RESTApiCalls {

	private PlaceOrderTest placeorder=new PlaceOrderTest();

	@BeforeClass
	public void suitelalaTestNGTest(){

		System.out.println("Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  


	@Test(priority=1, enabled=true)
	public void verifyCancelNewOrder(){


		System.out.println("Verifying Cancelling new Order, Placing a new order");
		
		placeorder.verifyNewOrder();
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID",NewOrderID);
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 200));
		System.out.println("Test Case is Passed");
		
		
	}/*--END OF METHOD---*/
	
	@Test(priority=1, enabled=true)
	public void verifyCancelExistingOrder(){


		System.out.println("Verifying Cancelling and Existing Order, Placing a new order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", prop.getProperty("existingOrderID"));
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 200));
		System.out.println("Test Case is Passed");


	}/*--END OF METHOD---*/
	
	
	@Test(priority=1, enabled=true)
	public void verifyCancelNonExistingOrder(){


		System.out.println("Verifying Cancelling and Existing Order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", "-1");
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 404));
		System.out.println("---------- Test Case is Passed -------------");

	}/*--END OF METHOD---*/
	
	
	@Test(priority=1, enabled=true)
	public void verifyCancelOrderForOnGoingOrder(){


		System.out.println("Verifying Cancelling and OnGoing Order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", prop.getProperty("ongoingOrderID"));
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 200));
		System.out.println("---------- Test Case is Passed -------------");

	}/*--END OF METHOD---*/
	
	
	@Test(priority=1, enabled=true)
	public void verifyCancelOrderForAlreadyCancelledOrder(){


		System.out.println("Verifying Cancelling and Existing Order, Placing a new order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", prop.getProperty("cancelledOrderID"));
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 200));
		System.out.println("---------- Test Case is Passed -------------");

	}/*--END OF METHOD---*/
	
	
	@Test(priority=1, enabled=true)
	public void verifyCancelOrderForCompletedOrder(){


		System.out.println("Verifying Cancelling of completed Order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", prop.getProperty("completedOrderID"));
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 422));
		System.out.println("---------- Test Case is Passed -------------");

	}/*--END OF METHOD---*/
	


}/*--END OF CLASS---*/
