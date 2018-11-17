package getRequest;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class GetData {
	
	@Test
	public void testResponseCode(){
		
		Response resp = get("http://localhost:51544/v1/orders/1");
		
		int code=resp.getStatusCode();
		
		System.out.println("Status Code is: "+code);
		Assert.assertEquals(code, 201);
	}
}
