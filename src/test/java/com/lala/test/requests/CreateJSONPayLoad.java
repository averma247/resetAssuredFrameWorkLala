package com.lala.test.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateJSONPayLoad {
	
	public static JSONObject createJSONPayloadNewOrder(){
		
		JSONObject stops= new JSONObject();
		JSONArray latlongList= new JSONArray();
		
		Map<String, Double> latLong= new LinkedHashMap<String, Double>(2);
		
		latLong.put("lat",22.344674);
		latLong.put("lng",114.124651);
		
		latlongList.add(latLong); 
		
		latLong= new LinkedHashMap<String, Double>(2);
		
		latLong.put("lat",22.375384);
		latLong.put("lng",114.182446);
		
		latlongList.add(latLong); 
		
		latLong= new LinkedHashMap<String, Double>(2);
		
		latLong.put("lat",22.385669);
		latLong.put("lng",114.186962);
		
		// adding map to list 
		latlongList.add(latLong); 
		
		// putting lat long details to list.
		
		stops.put("stops", latlongList);
		System.out.println("JSON FORMAT STRING");
		System.out.println(stops.toJSONString());
		
		return stops;
		
	}
	
	public static void main(String args[]){
		
		//JSONObject json= createJSONPayloadNewOrder();
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("http://localhost:51544/v1/orders/1");
		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("Order ID from Response " + jsonPathEvaluator.prettyPrint());
		
	}
	
}
