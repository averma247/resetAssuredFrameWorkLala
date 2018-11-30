package com.lala.requests;

import static com.lala.test.GlobalData.NewOrderID;
import static com.lala.test.GlobalData.prop;
import static io.restassured.RestAssured.get;

import java.util.HashMap;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RESTApiCalls extends CreateJSONPayLoad{

	public Response response=null;
	public JSONObject requestParams=null;
	public JsonPath jsonPathEvaluator = null;

	public Response sendRESTAPIRequest(HashMap<String,String> RequestData){

		RestAssured.baseURI=prop.getProperty("baseURL");


		if(RequestData.get("RequestType").contains("New Order")){
			RequestSpecification request = RestAssured.given();
			requestParams = readyJSONPayloadFromFile("NewOrder");
			request.body(requestParams.toJSONString());
			System.out.println("JSON payload: "+ requestParams.toJSONString() );
			response = request.post(prop.getProperty("placeOrderURL"));
			jsonPathEvaluator = response.jsonPath();

			if((jsonPathEvaluator.get("id"))!=null){
				NewOrderID=(jsonPathEvaluator.get("id")).toString();
				System.out.println("New Order ID is: "+NewOrderID);
			}
			else{

				System.out.println("New Order ID is null");
				return null;
			}
		}

		else if(RequestData.get("RequestType").contains("Future Order")){
			RequestSpecification request = RestAssured.given();
			requestParams = readyJSONPayloadFromFile("FutureOrder");
			request.body(requestParams.toJSONString());
			System.out.println("JSON payload: "+ requestParams.toJSONString() );
			response = request.post(prop.getProperty("placeOrderURL"));
			jsonPathEvaluator = response.jsonPath();
			System.out.println("Response Message: "+response.prettyPrint().toString());
			if((jsonPathEvaluator.get("id"))!=null){
				NewOrderID=(jsonPathEvaluator.get("id")).toString();
				System.out.println("New Order ID is: "+NewOrderID);
			}
			else{

				System.out.println("New Order ID is null");
				return null;
			}
		}

		else if(RequestData.get("RequestType").contains("Cancel Order")){
			RequestSpecification request = RestAssured.given();
			requestParams = readyJSONPayloadFromFile("Cancel");
			request.body(requestParams.toJSONString());
			response = request.put(prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID")+prop.getProperty("cancelOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Take Away")){
			RequestSpecification request = RestAssured.given();
			requestParams = readyJSONPayloadFromFile("Takeaway");
			request.body(requestParams.toJSONString());
			response = request.put(prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID")+prop.getProperty("takeawayOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Complete Order")){
			RequestSpecification request = RestAssured.given();
			requestParams = readyJSONPayloadFromFile("Complete");
			request.body(requestParams.toJSONString());
			response = request.put(prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID")+prop.getProperty("completeOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Fetch Order")){
			RequestSpecification request = RestAssured.given();
			/*requestParams = CreateJSONPayLoad.readyJSONPayloadFromFile("Complete");
			request.body(requestParams.toJSONString());*/
			response = request.get(prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("InValid Payload")){
			RequestSpecification request = RestAssured.given();
			requestParams = readyJSONPayloadFromFile("InValidPayload");
			request.body(requestParams.toJSONString());
			System.out.println("JSON payload: "+ requestParams.toJSONString() );
			response = request.post(prop.getProperty("placeOrderURL"));
			jsonPathEvaluator = response.jsonPath();
			System.out.println("Response Message: "+response.prettyPrint().toString());
			if((jsonPathEvaluator.get("id"))!=null){
				NewOrderID=(jsonPathEvaluator.get("id")).toString();
				System.out.println("New Order ID is: "+NewOrderID);
			}
			else{

				System.out.println("New Order ID is null");
				return null;
			}
		}



		else{

			System.out.println("Request Data is not correct.: "+ response.getStatusCode());
			return null;
		}

		return response;

	}/*--- END OF MSG*/



	public static boolean verifyResponseCode(Response response, int expectedStatusCode){

		System.out.println("Verifying status code came in response.");	
		if(response.getStatusCode()==expectedStatusCode){
			System.out.println("Status code is matched.");
			return true;
		}
		else{
			System.out.println("Status code is not matched.");
			return false;
		}

	}

	public static boolean verifyOrderIDInResponse(Response response, String ExpectedOrderID){
		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("Order ID from Response " + jsonPathEvaluator.get("id"));
		System.out.println("Expected Order ID: "+ ExpectedOrderID);
		System.out.println("Response Data " + jsonPathEvaluator.prettyPrint());

		if(ExpectedOrderID.equalsIgnoreCase((String) jsonPathEvaluator.get("id"))){

			System.out.println("Order ID in the response is same.");
			return true;

		}
		else{
			System.out.println("Order ID in the response is different: "+jsonPathEvaluator.get("id")+"from"+" Expected Order ID: "+ExpectedOrderID);
			return false;
		}
	}

}
