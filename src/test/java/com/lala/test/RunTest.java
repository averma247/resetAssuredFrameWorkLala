package com.lala.test;

import static io.restassured.RestAssured.*;
import static com.lala.test.GlobalData.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lala.test.requests.CancelOrder;
import com.lala.test.requests.DriveToCompleteOrder;
import com.lala.test.requests.DriveToTakeOrder;
import com.lala.test.requests.FetchOrder;
import com.lala.test.requests.PlaceOrder;

import io.restassured.RestAssured;
import io.restassured.response.Response;


/**
 * Below execute the test cases to very APIs to process orders and 
 * require you to make sure it's functioning correctly with black box testing. 
 * 
 * Test Scenarios Covers.
 *  1. Place Order
	2. Fetch Order Details
	3. Driver to Take the Order
	4. Driver to Complete the Order
	5. Cancel Order 
 * 
 * */


public class RunTest {
	
	PlaceOrder placeorder=new PlaceOrder();
	FetchOrder fetchorder= new FetchOrder();
	DriveToTakeOrder drivetotakeorder= new DriveToTakeOrder();
	DriveToCompleteOrder drivetocomplete=new DriveToCompleteOrder();
	CancelOrder cancelorder=new CancelOrder();
	
	String OrderID;
		
		@Test(priority=1, enabled=false)
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
		
		@Test(priority=2, enabled=false)
		public void verifyFetchOrderWhenOrderExist(){
						
			try{
				
				System.out.println("Verfying Fetch Order Flow.");
				String OrderID="1";
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
		
		@Test(priority=3, enabled=false)
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
			
		}/*--END OF METHOD---*/
		
		@Test(priority=4, enabled=false)
		public void verifyDriveToTakeOrder(){
			
			try{
				
				System.out.println("Verfying Drive to take Order, Order is assiging state.");
				String OrderID="2";
				System.out.println("Order ID is: "+ OrderID);
				int actualStatusCode=drivetotakeorder.driveToTakeOrderRequest(OrderID);
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
		
		@Test(priority=5, enabled=true)
		public void verifyDriveToComplete(){
			
			try{
				
				System.out.println("Verifying Order state to Complete, Order is assiging state.");
				String OrderID="2";
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
		
		@Test(priority=6, enabled=false)
		public void verifyCancelOrder(){
		
			try{
				
				System.out.println("Verifying Order state to Complete, Order is assiging state.");
				String OrderID="3";
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
			}
			
	
		}/*--END OF METHOD---*/
	
	
}/*--END OF CLASS---*/


