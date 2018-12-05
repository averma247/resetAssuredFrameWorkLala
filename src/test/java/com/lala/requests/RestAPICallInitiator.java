package com.lala.requests;

import static com.lala.requests.GlobalData.prop;

import org.json.simple.JSONObject;

import com.lala.test.utils.CommonUtils;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAPICallInitiator {
	
	public Response response=null;
	public JSONObject requestParams=null;
	public JsonPath jsonPathEvaluator=null;
	public JSONPayLoadParser jsonPayLoadParser=new JSONPayLoadParser();
	public CommonUtils cmutils= new CommonUtils();

	public Response sendPOSTRequest(RequestSpecification request,String URL){
		//request = RestAssured.given();
		RestAssured.baseURI=prop.getProperty("baseURL");
		response = request.post(URL);
		return response;
	}
	
	public Response sendPUTRequest(RequestSpecification request,String URL){
		RestAssured.baseURI=prop.getProperty("baseURL");
		response = request.put(URL);
		return response;
	}
	
	public Response sendGETRequest(RequestSpecification request,String URL){
		RestAssured.baseURI=prop.getProperty("baseURL");
		response = request.get(URL);
		return response;
	}
}
