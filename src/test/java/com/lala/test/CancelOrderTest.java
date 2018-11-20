package com.lala.test;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.lala.test.requests.CancelOrder;
import com.lala.test.requests.PlaceOrder;

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
	
	@BeforeSuite
	   public void suitelalaTestNGTest(){
		 
		   System.out.println("Reading Config file before executing test cases.");
		   GlobalData.readConfigFile();
	     }  
	
	
	@Test(priority=6, enabled=true)
	public void verifyCancelOrder(){
	
		try{
			
			System.out.println("Verifying Order state to Complete, Order is assiging state.");
			
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
			
			
			
		}catch(Exception e){

			System.out.println(e.getMessage()); 
			System.out.println("Test is failed");
			Assert.fail("Test is failed, Please check server connection.");
		}
		

	}/*--END OF METHOD---*/


}/*--END OF CLASS---*/
