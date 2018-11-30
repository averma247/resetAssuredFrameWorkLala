package com.lala.requests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONPayLoadPraser {


	public JSONObject readJSONPayloadFromFile(String requestType)
	{
		//JSON parser object to read the JSON file.
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

				return payloadlist;            
			}

			else  if(requestType.contains("Complete"))
			{
				FileReader reader = new FileReader((System.getProperty("user.dir")+"/src/test/resources/completepayload.json"));


				//Read JSON file
				Object obj = jsonParser.parse(reader);

				JSONObject payloadlist = (JSONObject) obj;
				System.out.println(payloadlist);

				return payloadlist;            
			}  

			else  if(requestType.contains("Takeaway"))
			{
				FileReader reader = new FileReader((System.getProperty("user.dir")+"/src/test/resources/takeawaypayload.json"));


				//Read JSON file
				Object obj = jsonParser.parse(reader);

				JSONObject payloadlist = (JSONObject) obj;
				System.out.println(payloadlist);

				return payloadlist;            
			}  

			else if(requestType.contains("NewOrder")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/placeorderpayload.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				System.out.println("Payload data for stops");
				System.out.println(placeOrderJSON.get("stops"));

				JSONArray storelocation = (JSONArray) placeOrderJSON.get("stops");

				Iterator<?> iterator = storelocation.iterator();
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}
				return placeOrderJSON;

			}

			else if(requestType.contains("FutureOrder")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/futureorderpayload.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				Date date = new Date();  

				System.out.println(placeOrderJSON.get("stops"));
				System.out.println(placeOrderJSON.get("orderAt"));
				placeOrderJSON.put("orderAt", date.toInstant());
				System.out.println("Future order date and time: "+placeOrderJSON.get("orderAt"));
				JSONArray storelocation = (JSONArray) placeOrderJSON.get("stops");

				Iterator<?> iterator = storelocation.iterator();
				while (iterator.hasNext()) {
					System.out.println(iterator.next());
				}
				return placeOrderJSON;

			}


			else if(requestType.contains("InValidPayload")){

				Object obj = jsonParser.parse(new FileReader((System.getProperty("user.dir")+"/src/test/resources/invalidneworderpayload.json")));

				JSONObject placeOrderJSON = (JSONObject) obj;
				return placeOrderJSON;

			}


			else{

				System.out.println("Please provide the correct request type.");
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
}
