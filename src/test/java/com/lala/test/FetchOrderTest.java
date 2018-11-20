package com.lala.test;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.lala.test.requests.FetchOrder;
import com.lala.test.requests.PlaceOrder;


/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers for Fetch Order
 * 
 * */


public class FetchOrderTest {
	
	PlaceOrder placeorder=new PlaceOrder();
	FetchOrder fetchorder= new FetchOrder();
	
	@BeforeSuite
	   public void suitelalaTestNGTest(){
		 
		   System.out.println("Reading Config file before executing test cases.");
		   GlobalData.readConfigFile();
	     }  
	
	
	@Test(priority=1, enabled=true)
	public void verifyFetchOrderWhenOrderExist(){
					
		try{
			
			System.out.println("Verfying Fetch Order Flow.");
			
			int orderPlacedStatus=placeorder.placeNewOrder();
			if(orderPlacedStatus==0){
				System.out.println("Error while placing order, Try placing order manually.");
				System.out.println("Test is failed.");
				Assert.fail("Test is failed, Try placing order manually.");				
			}
			
			String OrderID=GlobalData.NewOrderID;
			System.out.println("Order ID is: "+ OrderID);
			int actualStatusCode=FetchOrder.fetchOrderRequest(OrderID);
			if(actualStatusCode!=0){
				fetchorder.verifyStatusCode(actualStatusCode, 200);					
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
	
	@Test(priority=3, enabled=true)
	public void verifyFetchOrderWhenOrderDoesNotExist(){
		
		try{
			
			System.out.println("Verfying Fetch Order Flow for non existing order.");
			String OrderID="-1";
			System.out.println("Order ID is: "+ OrderID);
			int actualStatusCode=FetchOrder.fetchOrderRequest(OrderID);
			if(actualStatusCode!=0){
				fetchorder.verifyStatusCode(actualStatusCode, 404);					
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
	
	}/*----------- END OF METHOD --------------------*/
	
}
