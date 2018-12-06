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
 * Test Scenarios Covers for Fetch Order
 * 
 * */

//Fetch Order details Newly created Order
public class FetchOrderTest {

	private PlaceOrderTest placeorder=new PlaceOrderTest();
	private RESTApiCalls restapicalls= new RESTApiCalls();

	@BeforeTest
	public void suitelalaTestNGTest(){


		LOGGER.log(Level.INFO, "Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  

	//Verifying Fetch order for New Order

	@Test(priority=1, enabled=true)
	public void verifyFetchOrderForNewOrder(){


		LOGGER.log(Level.INFO, "Fetch Order details Newly created Order");
		placeorder.verifyNewOrder();

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Fetch Order");
		RequestData.put("OrderID", NewOrderID);
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.INFO, "Test is failed, Error while placing order, Please check manually.");
			Assert.fail("Test is failed, Error while placing order, Please check manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);
		LOGGER.log(Level.INFO, "Test Case is Passed");


	}/*--END OF METHOD---*/

	//Verifying Fetch order for already previously placed order

	@Test(priority=1, enabled=true)
	public void verifyFetchOrderWhenOrderExist(){


		LOGGER.log(Level.INFO, "Verifying Fetch order for already previously placed order");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Fetch Order");
		RequestData.put("OrderID", prop.getProperty("existingOrderID"));
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.INFO, "Test is failed, Error while placing order, Please check manually.");
			Assert.fail("Test is failed, Error while placing order, Please check manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),200);
		LOGGER.log(Level.INFO, "-------- Test Case is Passed ----------");



	}/*--END OF METHOD---*/

	//Fetch order details when order doesn't exist

	@Test(priority=1, enabled=true)
	public void verifyFetchOrderWhenOrderDoesNotExist(){


		LOGGER.log(Level.INFO, "Fetch order details when order doesn't exist");
		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "Fetch Order");
		RequestData.put("OrderID", "-1");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE, "Test is failed, Error while placing order, Please check manually.");
			Assert.fail("Test is failed, Error while placing order, Please check manually.");
		}


		LOGGER.log(Level.INFO, "Verifying status code.");
		Assert.assertEquals(response.getStatusCode(),404);
		LOGGER.log(Level.INFO, "------Test Case completed------");


	}/*----------- END OF METHOD --------------------*/



}
