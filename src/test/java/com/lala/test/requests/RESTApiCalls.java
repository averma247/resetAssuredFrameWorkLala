package com.lala.test.requests;

import static com.lala.test.GlobalData.NewOrderID;
import static com.lala.test.GlobalData.prop;
import static io.restassured.RestAssured.get;

import java.util.HashMap;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RESTApiCalls {

	public static Response response=null;
	public static RequestSpecification request = RestAssured.given();
	public static JSONObject requestParams=null;
	public static JsonPath jsonPathEvaluator = null;

	public static Response sendRESTAPIRequest(HashMap<String,String> RequestData){

		RestAssured.baseURI=prop.getProperty("baseURL");


		if(RequestData.get("RequestType").contains("New Order")){
			requestParams = CreateJSONPayLoad.readyJSONPayloadFromFile("NewOrder");
			request.body(requestParams.toJSONString());
			response = request.post(prop.getProperty("placeOrderURL"));
			jsonPathEvaluator = response.jsonPath();
			NewOrderID=(jsonPathEvaluator.get("id")).toString();
			if(NewOrderID!=null){

				System.out.println("New Order ID is: "+NewOrderID);
			}
			else{

				System.out.println("New Order ID is null");
				return null;
			}
		}

		else if(RequestData.get("RequestType").contains("Cancel Order")){
			requestParams = CreateJSONPayLoad.readyJSONPayloadFromFile("Cancel");
			request.body(requestParams.toJSONString());
			response = request.put(prop.getProperty("placeOrderURL")+RequestData.get("OrderID")+prop.getProperty("cancelOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Take Away")){
			requestParams = CreateJSONPayLoad.readyJSONPayloadFromFile("Takeaway");
			request.body(requestParams.toJSONString());
			response = request.put(prop.getProperty("placeOrderURL")+RequestData.get("OrderID")+prop.getProperty("takeawayOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Complete Order")){
			requestParams = CreateJSONPayLoad.readyJSONPayloadFromFile("Complete");
			request.body(requestParams.toJSONString());
			response = request.put(prop.getProperty("placeOrderURL")+RequestData.get("OrderID")+prop.getProperty("completeOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Fetch Order")){
			requestParams = CreateJSONPayLoad.readyJSONPayloadFromFile("Complete");
			request.body(requestParams.toJSONString());
			response = request.get(prop.getProperty("placeOrderURL")+RequestData.get("OrderID"));
			return response;
		}
		else{

			System.out.println("Request Data is not correct.");
			return null;
		}
		/*int statusCode = response.getStatusCode();
		System.out.println("Status Code is: "+statusCode);
		String msgBody= response.body().asString();
		System.out.println("Message Body: "+msgBody);

		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("Order ID from Response " + jsonPathEvaluator.get("id"));
		NewOrderID=(jsonPathEvaluator.get("id")).toString();
		System.out.println("Response Data " + jsonPathEvaluator.prettyPrint());
		return false;*/
		return null;

	}/*--- END OF MSG*/



	
	


}
