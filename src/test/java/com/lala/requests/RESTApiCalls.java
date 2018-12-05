package com.lala.requests;

import static com.lala.requests.GlobalData.LOGGER;
import static com.lala.requests.GlobalData.NewOrderID;
import static com.lala.requests.GlobalData.prop;
import java.util.HashMap;
import java.util.logging.Level;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.lala.test.utils.CommonUtils;

public class RESTApiCalls{

	public Response response=null;
	public JSONObject requestParams=null;
	public JsonPath jsonPathEvaluator=null;
	public JSONPayLoadParser jsonPayLoadParser=new JSONPayLoadParser();
	public CommonUtils cmutils= new CommonUtils();
	public RestAPICallInitiator restAPICallInitiator= new RestAPICallInitiator();

	public Response restAPIRequestInitiator(HashMap<String,String> RequestData){

		RestAssured.baseURI=prop.getProperty("baseURL");


		if(RequestData.get("RequestType").contains("New Order")){
			
			response = cmutils.getJSONPayLoadNewOrder();//gets the JSON Payload from file
			jsonPathEvaluator = response.jsonPath();
			if((jsonPathEvaluator.get("id"))!=null){
				NewOrderID=(jsonPathEvaluator.get("id")).toString();
				LOGGER.log(Level.INFO, "New Order ID is: "+NewOrderID);
			}
			else{
				
				LOGGER.log(Level.INFO, "New Order ID is null");
				return null;
			}
		}

		else if(RequestData.get("RequestType").contains("Future Order")){
			response = cmutils.getJSONPayLoadFutureOrder();//gets the JSON Payload from file
			jsonPathEvaluator = response.jsonPath();
			if((jsonPathEvaluator.get("id"))!=null){
				NewOrderID=(jsonPathEvaluator.get("id")).toString();
				LOGGER.log(Level.INFO, "New Order ID is: "+NewOrderID);
			}
			else{

				LOGGER.log(Level.INFO,"New Order ID is null");
				return null;
			}
		}

		else if(RequestData.get("RequestType").contains("Cancel Order")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("Cancel");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPUTRequest(request,prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID")+prop.getProperty("cancelOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Take Away")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("Takeaway");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPUTRequest(request,prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID")+prop.getProperty("takeawayOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Complete Order")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("Complete");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPUTRequest(request,prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID")+prop.getProperty("completeOrderURL"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("Fetch Order")){
			RequestSpecification request = RestAssured.given();
			response= restAPICallInitiator.sendGETRequest(request,prop.getProperty("placeOrderURL")+"/"+RequestData.get("OrderID"));
			return response;
		}

		else if(RequestData.get("RequestType").contains("InValid Payload")){
			RequestSpecification request = RestAssured.given();
			requestParams = jsonPayLoadParser.readJSONPayloadFromFile("InValidPayload");
			request.body(requestParams.toJSONString());
			response= restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
			jsonPathEvaluator = response.jsonPath();
			if((jsonPathEvaluator.get("id"))!=null){
				NewOrderID=(jsonPathEvaluator.get("id")).toString();
			}
			else{
				LOGGER.log(Level.INFO,"New Order ID is null");
				return null;
			}
		}



		else{

			LOGGER.log(Level.INFO,"Request Data is not correct.: "+ response.getStatusCode());
			return null;
		}

		return response;

	}/*--- END OF MSG*/


	public void sendPOSTAPIRequest(){
		
		RestAssured.baseURI=prop.getProperty("baseURL");
		RequestSpecification request = RestAssured.given();
		response = request.post(prop.getProperty("placeOrderURL"));
		
	}
	public boolean verifyResponseCode(Response response, int expectedStatusCode){

	
		LOGGER.log(Level.INFO,"Verifying status code came in response.");
		if(response.getStatusCode()==expectedStatusCode){
			LOGGER.log(Level.INFO,"Status code is matched.");
			return true;
		}
		else{
			LOGGER.log(Level.INFO,"Status code is not matched.");
			return false;
		}

	}

	public boolean verifyOrderIDInResponse(Response response, String ExpectedOrderID){
		JsonPath jsonPathEvaluator = response.jsonPath();
		LOGGER.log(Level.INFO,"Order ID from Response " + jsonPathEvaluator.get("id"));
		LOGGER.log(Level.INFO,"Expected Order ID: "+ ExpectedOrderID);
		LOGGER.log(Level.INFO,"Response Data " + jsonPathEvaluator.prettyPrint());

		if(ExpectedOrderID.equalsIgnoreCase((String) jsonPathEvaluator.get("id"))){

			
			LOGGER.log(Level.INFO,"Order ID in the response is same.");
			return true;

		}
		else{
			
			LOGGER.log(Level.INFO,"Order ID in the response is different: "+jsonPathEvaluator.get("id")+"from"+" Expected Order ID: "+ExpectedOrderID);
			return false;
		}
	}
	
	public boolean verifyMessageInResponse(Response response, String expectedmsg){
		JsonPath jsonPathEvaluator = response.jsonPath();
		LOGGER.log(Level.INFO,"Message from Response " + jsonPathEvaluator.get("message"));
		LOGGER.log(Level.INFO,"Expected Message: "+ expectedmsg);
		LOGGER.log(Level.INFO,"Response Data " + jsonPathEvaluator.prettyPrint());

		if(expectedmsg.equalsIgnoreCase((String) jsonPathEvaluator.get("message"))){

			
			LOGGER.log(Level.INFO,"Order ID in the response is same.");
			return true;

		}
		else{
			
			LOGGER.log(Level.INFO,"Expected Message in the response is different: "+jsonPathEvaluator.get("message")+"from"+" Expected Message: "+expectedmsg);
			return false;
		}
	}
	

}
