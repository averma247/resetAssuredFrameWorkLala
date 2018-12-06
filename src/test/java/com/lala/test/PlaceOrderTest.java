package com.lala.test;

import java.util.HashMap;
import java.util.logging.Level;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.lala.requests.GlobalData;
import static com.lala.requests.GlobalData.LOGGER;
import com.lala.requests.RESTApiCalls;
import static com.lala.requests.GlobalData.prop;

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

	//Verifying New Order Flow with Valid Data

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

	//Verifying New Order Flow with Valid Data and time stamp

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

	//Verifying New Order Flow with In-Valid request
	@Test(priority=1, enabled=false)
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

	//Verifying New Order Flow with In-Valid Origin.
	@Test(priority=1, enabled=false)
	public void verifyNewOrderWithInvalidOrigin(){

		LOGGER.log(Level.INFO,"Verifying New Order Flow with In-Valid Origin.");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "InValid Origin");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE,"Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");

		}

		LOGGER.log(Level.INFO,"Verifying status code");
		Assert.assertEquals(response.getStatusCode(),503);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"Service Unavailable"), true);


	}/*-- END OF METHOD --*/

	//Verifying trip cost when is scheduled not between 9PM to 5AM
	@Test(priority=1, enabled=false)
	public void verifyTripCostNotIn9to5(){

		LOGGER.log(Level.INFO,"Verifying trip cost when is scheduled not between 9PM to 5AM");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "tripcostpayloadnotin9to5");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE,"Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");

		}
		LOGGER.log(Level.INFO,"Verifying amount value");

		String amount = restapicalls.getFareCost(response.getBody().asString(), "amount");
		Assert.assertEquals(prop.getProperty("fareamountnotin9to5"), amount);


	}/*-- END OF METHOD --*/



	//Verifying trip cost when is scheduled between 9PM to 5AM
	@Test(priority=1, enabled=false)
	public void verifyTripCostIn9to5(){

		LOGGER.log(Level.INFO,"Verfying trip cost when is schedulled between 9PM to 5AM");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "tripcostpayloadin9to5");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE,"Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");

		}
		LOGGER.log(Level.INFO,"Verifying amount value");

		String amount = restapicalls.getFareCost(response.getBody().asString(), "amount");
		Assert.assertEquals(prop.getProperty("fareamountin9to5"), amount);


	}/*-- END OF METHOD --*/


	//Verifying Order placed using back date or time
	@Test(priority=1, enabled=false)
	public void verifyPastDateOrderRequest(){

		LOGGER.log(Level.INFO,"Verifying Order placed using back date or time");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "backdateorderpaylod");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE,"Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");

		}

		LOGGER.log(Level.INFO,"Verifying status code");
		Assert.assertEquals(response.getStatusCode(),400);
		Assert.assertEquals(restapicalls.verifyMessageInResponse(response,"field orderAt is behind the present time"), true);


	}/*-- END OF METHOD --*/


	//Verifying Distance count in response

	@Test(priority=1, enabled=true)
	public void verifyingDistanceCount(){

		LOGGER.log(Level.INFO,"Verifying Distance count in response");

		HashMap<String, String> RequestData= new HashMap<String, String>() ;
		RequestData.put("RequestType", "drivingdistancecountpayload");
		Response response=restapicalls.restAPIRequestInitiator(RequestData);

		if(response==null){
			LOGGER.log(Level.SEVERE,"Test is failed, Error while placing order, Please check by placing order manually.");
			Assert.fail("Test is failed, Error while placing order, Please check by placing order manually.");

		}
		int count=restapicalls.getDistanceValueCount(response.getBody().asString(), "drivingDistancesInMeters");
		LOGGER.log(Level.INFO,"Verifying distance count");
		Assert.assertEquals(String.valueOf(count), prop.getProperty("drivingdistancecount"));

	}/*-- END OF METHOD --*/



}/*-- END OF CLASS --*/
