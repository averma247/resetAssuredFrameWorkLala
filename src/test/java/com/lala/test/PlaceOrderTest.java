package com.lala.test;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.lala.test.requests.PlaceOrder;



/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Place Order
 * 
 * */


public class PlaceOrderTest {
	
	PlaceOrder placeorder=new PlaceOrder();
	
	String OrderID;
	
	
	   @BeforeTest
	   public void suitelalaTestNGTest(){
		 
		   System.out.println("Reading Config file before executing test cases.");
		   GlobalData.readConfigFile();
	     }  
		
		@Test(priority=1, enabled=true)
		public void verifyNewOrder(){
			
			System.out.println("Verfying New Order Flow with Valid Data.");
			try{
				
				int actualStatusCode=placeorder.placeNewOrder();
				if(actualStatusCode!=0){
					placeorder.verifyStatusCode(actualStatusCode, 201);					
				}
				else{
					
					System.out.println("Error while placing order, please check server connection.");
					System.out.println("Test is failed.");
					Assert.fail("Test is failed, Error while placing order, please check server connection.");
				}
				
				System.out.println("Test is passed.");
			}
			
			catch(Exception e){
				
				System.out.println(e.getMessage()); 
				System.out.println("Test is failed");
			}
						
		}/*-- END OF METHOD --*/
		
		
		@Test(priority=1, enabled=true)
		public void verifyFutureOrder(){
			
			System.out.println("Verfying Future Order Flow with Valid Data.");
			try{
				
				int actualStatusCode=placeorder.placeFutureOrder();
				if(actualStatusCode!=0){
					placeorder.verifyStatusCode(actualStatusCode, 201);					
				}
				else{
					
					System.out.println("Error while placing order, please check server connection.");
					System.out.println("Test is failed.");
					Assert.fail("Test is failed, Error while placing order, please check server connection.");
				}
				
				System.out.println("Test is passed.");
			}
			
			catch(Exception e){
				
				System.out.println(e.getMessage()); 
				System.out.println("Test is failed");
				Assert.fail("Test is failed, Error while placing order, please check server connection.");
			}
						
		}/*-- END OF METHOD --*/

		
		
		
		
}
