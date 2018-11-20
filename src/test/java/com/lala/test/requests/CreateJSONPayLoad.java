package com.lala.test.requests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	
	
	
	
	public static JSONObject readyJSONPayloadFromFile(String requestType)
    {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try
        {
            if(requestType.contains("Cancel"))
        	{
            	FileReader reader = new FileReader((System.getProperty("user.dir")+"/src/test/resources/cancelpayload.json"));
        	
        	
        	//Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject payloadlist = (JSONObject) obj;
            System.out.println(payloadlist);
             
            //Iterate over employee array
            //payloadlist.forEach( emp -> parseEmployeeObject((Object)emp ));
            parseEmployeeObject(payloadlist);
            
            return payloadlist;            
          }
            
            else  if(requestType.contains("Complete"))
        	{
            	FileReader reader = new FileReader((System.getProperty("user.dir")+"/src/test/resources/cancelpayload.json"));
        	
        	
        	//Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject payloadlist = (JSONObject) obj;
            System.out.println(payloadlist);
             
            //Iterate over employee array
            //payloadlist.forEach( emp -> parseEmployeeObject((Object)emp ));
           //parseEmployeeObject(payloadlist);
            
            return payloadlist;            
          }  
           
            else  if(requestType.contains("Takeaway"))
        	{
            	FileReader reader = new FileReader((System.getProperty("user.dir")+"/src/test/resources/takeawayload.json"));
        	
        	
        	//Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject payloadlist = (JSONObject) obj;
            System.out.println(payloadlist);
             
            //Iterate over employee array
            //payloadlist.forEach( emp -> parseEmployeeObject((Object)emp ));
           //parseEmployeeObject(payloadlist);
            
            return payloadlist;            
          }  
            
            else if(requestType.contains("NewOrder")){
            	
            	Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/placeorderpayload.json")));

            	JSONObject placeOrderJSON = (JSONObject) obj;

        	    System.out.println(placeOrderJSON.get("stops"));

        	    JSONArray storelocation = (JSONArray) placeOrderJSON.get("stops");

        	    Iterator iterator = storelocation.iterator();
        	    while (iterator.hasNext()) {
        	    	System.out.println(iterator.next());
        	    }
        	    return placeOrderJSON;
            	
            }
            
            else if(requestType.contains("FutureOrder")){
            	
            	Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/futureorderpayload.json")));

            	JSONObject placeOrderJSON = (JSONObject) obj;

        	    System.out.println(placeOrderJSON.get("stops"));
        	    System.out.println(placeOrderJSON.get("orderAt"));

        	    JSONArray storelocation = (JSONArray) placeOrderJSON.get("stops");

        	    Iterator iterator = storelocation.iterator();
        	    while (iterator.hasNext()) {
        	    	System.out.println(iterator.next());
        	    }
        	    return placeOrderJSON;
            	
            }
            
           
           else{
            	
            	System.out.println("Please provide the correct request tyep.");
            	return null;
            }
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
       
        return null;
	
    }
	
	 private static void parseEmployeeObject(JSONObject payloadlistdata)
	    {
	        //Get employee object within list
	        Long OrderID = (Long) payloadlistdata.get("id");
	        System.out.println(OrderID);
	         
	        //Get employee first name
	        String firstName = (String) payloadlistdata.get("status");   
	        System.out.println(firstName);
	         
	        //Get employee last name
	        String lastName = (String) payloadlistdata.get("cancelledAt"); 
	        System.out.println(lastName);
	        
	    }
	 
	public static JSONObject getPlaceOrderPayloadFromFile() throws FileNotFoundException, IOException, ParseException{
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/futureorderpayload.json")));

	    JSONObject placeOrderJSON = (JSONObject) obj;

	    System.out.println(placeOrderJSON.get("stops"));
	    System.out.println(placeOrderJSON.get("orderAt"));

	    JSONArray storelocation = (JSONArray) placeOrderJSON.get("stops");

	    Iterator iterator = storelocation.iterator();
	    while (iterator.hasNext()) {
	    	System.out.println(iterator.next());
	    }
	    return placeOrderJSON;
	}
	
	
	public static void main (String args[]) throws FileNotFoundException, IOException, ParseException{
		
		JSONObject placeOrderJSON=getPlaceOrderPayloadFromFile();
		System.out.println("Place Order JSON payload: "+ placeOrderJSON.toJSONString());
		
	}
}
