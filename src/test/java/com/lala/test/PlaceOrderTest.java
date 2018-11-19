package com.lala.test;

import org.testng.Assert;
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
		
		@Test(priority=1, enabled=true)
		public void verifyPlaceOrder(){
			
			System.out.println("Verfying New Order Flow with Valid Data.");
			try{
				
				int actualStatusCode=placeorder.placeOrder();
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

}