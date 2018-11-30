package com.lala.test;

import static com.lala.test.GlobalData.prop;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.lala.requests.RESTApiCalls;
import com.lala.test.GlobalData;

import io.restassured.response.Response;

/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Drive to Take Order
 * 
 * */



public class DriveToTakeOrderTest extends RESTApiCalls{

	private PlaceOrderTest placeorder=new PlaceOrderTest();

	@BeforeMethod
	public void suitelalaTestNGTest(){

		System.out.println("Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  


	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeOrder(){

		System.out.println("Verifying Take away new order ");
		placeorder.verifyNewOrder();

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID", GlobalData.NewOrderID);
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 200));


	}/*--END OF METHOD---*/


	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeExistingOrder(){

		System.out.println("Verifying Take away Existing order ");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID",prop.getProperty("existingOrderID"));
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 200));
		System.out.println("----------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/


	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeAlreadyCancelledOrder(){

		System.out.println("Verifying Take away already cancelled OrderID ");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID",prop.getProperty("cancelledOrderID"));
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 422));
		System.out.println("----------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/


	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeAlreadyCompletedOrder(){

		System.out.println("Verifying Take away operationon on Completed OrderID ");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID",prop.getProperty("completedOrderID"));
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 422));
		System.out.println("----------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/

	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeAlreadyOnGoingOrder(){

		System.out.println("Verifying Take away already ongoing status OrderID ");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID",prop.getProperty("ongoingOrderID"));
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 422));
		System.out.println("----------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/

	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeOnOrderDoesNotExist(){

		System.out.println("Verifying Take away on Order doesnot exist ");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Take Away");
		RequestData.put("OrderID","-1");
		Response response=sendRESTAPIRequest(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertTrue(RESTApiCalls.verifyResponseCode(response, 404));
		System.out.println("----------- Test Case is Completed -----------");

	}/*--END OF METHOD---*/



}
