package com.lala.test;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.lala.test.requests.DriveToTakeOrder;
import com.lala.test.requests.FetchOrder;
import com.lala.test.requests.PlaceOrder;

/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Drive to Take Order
 * 
 * */



public class DriveToTakeOrderTest {

	PlaceOrder placeorder=new PlaceOrder();
	FetchOrder fetchorder= new FetchOrder();
	DriveToTakeOrder drivetotakeorder= new DriveToTakeOrder();

	@BeforeTest
	public void suitelalaTestNGTest(){

		System.out.println("Reading Config file before executing test cases.");
		GlobalData.readConfigFile();
	}  


	@Test(priority=1, enabled=true)
	public void verifyDriveToTakeOrder(){

		try{

			System.out.println("Verfying Drive to take Order, Order is assiging state.");

			int orderPlacedStatus=placeorder.placeNewOrder();
			if(orderPlacedStatus==0){
				System.out.println("Error while placing order, Try placing order manually.");
				System.out.println("Test is failed.");
				Assert.fail("Test is failed, Try placing order manually.");				
			}

			String OrderID=GlobalData.NewOrderID;
			System.out.println("Order ID is: "+ OrderID);
			int actualStatusCode=drivetotakeorder.driveToTakeOrderRequest(OrderID);
			if(actualStatusCode!=0){
				drivetotakeorder.verifyStatusCode(actualStatusCode, 200);					
			}
			else{

				System.out.println("Error while changing the order status, please check server connection.");
				System.out.println("Test is failed.");
				Assert.fail("Error while fetching the order details, please check server connection.");
			}

			System.out.println("Test is Passed.");



		}catch(Exception e){

			System.out.println(e.getMessage()); 
			System.out.println("Test is failed");
		}

	}/*--END OF METHOD---*/


}
