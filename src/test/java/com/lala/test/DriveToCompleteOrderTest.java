package com.lala.test;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.lala.test.requests.DriveToCompleteOrder;
import com.lala.test.requests.DriveToTakeOrder;
import com.lala.test.requests.PlaceOrder;

/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Drive To Complete Order Test
 * 
 * */


public class DriveToCompleteOrderTest {
	
	PlaceOrder placeorder=new PlaceOrder();
	DriveToTakeOrder drivetotakeorder= new DriveToTakeOrder();
	DriveToCompleteOrder drivetocomplete=new DriveToCompleteOrder();
	
	@BeforeTest
	   public void suitelalaTestNGTest(){
		 
		   System.out.println("Reading Config file before executing test cases.");
		   GlobalData.readConfigFile();
	     }  
	
	
	@Test(priority=5, enabled=true)
	public void verifyDriveToComplete(){
		
		try{
			
			System.out.println("Verifying Order state to Complete, Order is assiging state.");
			
			int orderPlacedStatus=placeorder.placeNewOrder();
			if(orderPlacedStatus==0){
				System.out.println("Error while placing order, Try placing order manually.");
				System.out.println("Test is failed.");
				Assert.fail("Test is failed, Try placing order manually.");				
			}
			
			String OrderID=GlobalData.NewOrderID;
			
			int orderTakeAwayStatus=drivetotakeorder.driveToTakeOrderRequest(OrderID);
			if(orderTakeAwayStatus==0){
				System.out.println("Error while placing order, Try doing manually.");
				System.out.println("Test is failed.");
				Assert.fail("Test is failed, Try doing manually.");						
			}
			
			System.out.println("Order ID is: "+ OrderID);
			int actualStatusCode=drivetocomplete.driveToCompleteRequest(OrderID);
			if(actualStatusCode!=0){
				drivetotakeorder.verifyStatusCode(actualStatusCode, 200);					
			}
			else{
				
				System.out.println("Error while fetching the order details, please check server connection.");
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
