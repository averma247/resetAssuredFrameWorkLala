package com.lala.test.requests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJSONPayLoad {
	
	public static JSONObject createJSONPayloadNewOrder(){
		
		JSONObject stops= new JSONObject();
		JSONArray latlongList= new JSONArray();
		
		Map latLong= new LinkedHashMap(2);
		
		latLong.put("lat",22.344674);
		latLong.put("lng",114.124651);
		
		latlongList.add(latLong); 
		
		latLong= new LinkedHashMap(2);
		
		latLong.put("lat",22.375384);
		latLong.put("lng",114.182446);
		
		latlongList.add(latLong); 
		
		latLong= new LinkedHashMap(2);
		
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
	
	
}
