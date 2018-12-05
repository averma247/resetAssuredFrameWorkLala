package com.lala.test.utils;

import static com.lala.requests.GlobalData.LOGGER;
import static com.lala.requests.GlobalData.prop;

import java.util.logging.Level;

import org.json.simple.JSONObject;

import com.lala.requests.JSONPayLoadParser;
import com.lala.requests.RestAPICallInitiator;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CommonUtils {
	Response response=null;
	JSONObject requestParams=null;
	JSONPayLoadParser jsonPayLoadParser=new JSONPayLoadParser();
	

	public Response getJSONPayLoadNewOrder() {
		RestAPICallInitiator restAPICallInitiator= new RestAPICallInitiator();
		RequestSpecification request = RestAssured.given();
		requestParams = jsonPayLoadParser.readJSONPayloadFromFile("NewOrder");
		request.body(requestParams.toJSONString());
		LOGGER.log(Level.INFO, "JSON payload: "+ requestParams.toJSONString());
		response=restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		return response;

	}

	public Response getJSONPayLoadFutureOrder() {
		RestAPICallInitiator restAPICallInitiator= new RestAPICallInitiator();
		RequestSpecification request = RestAssured.given();
		requestParams = jsonPayLoadParser.readJSONPayloadFromFile("FutureOrder");
		request.body(requestParams.toJSONString());
		LOGGER.log(Level.INFO, "JSON payload: "+ requestParams.toJSONString());
		response=restAPICallInitiator.sendPOSTRequest(request,prop.getProperty("placeOrderURL"));
		return response;

	}

}
