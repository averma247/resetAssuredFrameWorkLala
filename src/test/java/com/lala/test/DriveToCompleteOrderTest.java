package com.lala.test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.lala.requests.RESTApiCalls;
import static com.lala.test.GlobalData.prop;

import io.restassured.response.Response;

/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Drive To Complete Order Test
 * 
 * */


public class DriveToCompleteOrderTest extends RESTApiCalls{

	private DriveToTakeOrderTest drivetotakeorder= new DriveToTakeOrderTest();

	@BeforeTest
	public void suitelalaTestNGTest(){

		System.out.println("Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  


	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForNewOrder(){

		System.out.println("Placing New Order and Changing order status to Ongoing");
		drivetotakeorder.verifyDriveToTakeOrder();


		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", GlobalData.NewOrderID);
		Response response=restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);

	}/*--END OF METHOD---*/


	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForNonExistingOrder(){

		System.out.println("Verifying flow for order doesnot exist.");			

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", "-1");
		Response response=restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),404);


	}/*--END OF METHOD---*/


	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForOnderOnAssignedState(){

		System.out.println("Verifying flow for order on Assigned state.");			

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", prop.getProperty("assignedOrderID"));
		Response response=restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(verifyMessageInResponse(response,"Order status is not ONGOING"), true);
		


	}/*--END OF METHOD---*/


	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForCompletedOrder(){

		System.out.println("Verifying flow for order on Assigned state.");			

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", prop.getProperty("completedOrderID"));
		Response response=restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(verifyMessageInResponse(response,"Order status is not ONGOING"), true);


	}/*--END OF METHOD---*/



	@Test(priority=5, enabled=true)
	public void verifyDriveToCompleteForCancelOrder(){

		System.out.println("Verifying flow for order on Cancelled state.");			

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Complete Order");
		RequestData.put("OrderID", prop.getProperty("cancelledOrderID"));
		Response response=restAPIRequestInitiator(RequestData);

		if(response==null){
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");
		}

		System.out.println("Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),422);
		Assert.assertEquals(verifyMessageInResponse(response,"Order status is not ONGOING"), true);


	}/*--END OF METHOD---*/


}
