package com.lala.test;

import static com.lala.test.GlobalData.prop;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.lala.requests.RESTApiCalls;
import io.restassured.response.Response;
import static com.lala.test.GlobalData.NewOrderID;


/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Fetch Order
 * 
 * */


public class FetchOrderTest extends RESTApiCalls {

	private PlaceOrderTest placeorder=new PlaceOrderTest();

	@BeforeTest
	public void suitelalaTestNGTest(){

		System.out.println("Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  


	@Test(priority=1, enabled=false)
	public void verifyFetchOrderForNewOrder(){

		System.out.println("Verifying Fetch order for New Order");
		placeorder.verifyNewOrder();
		
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Fetch Order");
		RequestData.put("OrderID", NewOrderID);
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 200));
		System.out.println("Test Case is Passed");


	}/*--END OF METHOD---*/
	
	
	@Test(priority=1, enabled=false)
	public void verifyFetchOrderWhenOrderExist(){

		System.out.println("Verifying Fetch order for already existing order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Fetch Order");
		RequestData.put("OrderID", prop.getProperty("existingOrderID"));
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 200));
		System.out.println("Test Case is Passed");


	}/*--END OF METHOD---*/
	
	
	@Test(priority=1, enabled=true)
	public void verifyFetchOrderWhenOrderDoesNotExist(){

		System.out.println("Verifying Fetch order functionality for non existing order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Fetch Order");
		RequestData.put("OrderID", "-1");
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 404));
		System.out.println("Test Case completed");
		
		
	}/*----------- END OF METHOD --------------------*/
	
	

}
