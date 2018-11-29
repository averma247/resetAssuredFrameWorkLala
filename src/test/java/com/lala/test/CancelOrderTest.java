package com.lala.test;

import static com.lala.test.GlobalData.prop;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.lala.requests.CancelOrder;
import com.lala.requests.PlaceOrder;
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

	PlaceOrder placeorder=new PlaceOrder();
	CancelOrder cancelorder=new CancelOrder();

	@BeforeClass
	public void suitelalaTestNGTest(){

		System.out.println("Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  


	@Test(priority=1, enabled=true)
	public void verifyCancelNewOrder(){


		System.out.println("Verifying Cancelling new Order, Placing a new order");
		
		int orderPlacedStatus=placeorder.placeNewOrder();
		if(orderPlacedStatus==0){
			System.out.println("Error while placing order, Try placing order manually.");
			System.out.println("Test is failed.");
			Assert.fail("Test is failed, Try placing order manually.");				
		}

		String OrderID=GlobalData.NewOrderID;
		System.out.println("Order ID is: "+ OrderID);
		int actualStatusCode=cancelorder.cancelOrderRequest(OrderID);
		if(actualStatusCode!=0){
			cancelorder.verifyStatusCode(actualStatusCode, 200);					
		}
		else{

			System.out.println("Error while fetching the order details, please check server connection.");
			System.out.println("Test is failed.");
			Assert.fail("Error while fetching the order details, please check server connection.");
		}

		System.out.println("Test is Passed.");

	}/*--END OF METHOD---*/
	
	@Test(priority=1, enabled=true)
	public void verifyCancelExistingOrder(){


		System.out.println("Verifying Cancelling and Existing Order, Placing a new order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Cancel Order");
		RequestData.put("OrderID", prop.getProperty("existingOrderID"));
		Response response=RESTApiCalls.sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 200));
		System.out.println("Test Case is Passed");


	}/*--END OF METHOD---*/
	
	


}/*--END OF CLASS---*/
