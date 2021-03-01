package testCase;

	import org.testng.Assert;
	import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
	import io.restassured.response.ResponseBody;

	import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;  

	public class Read_A_Product {
		
		SoftAssert softAssert = new SoftAssert();
		
		@Test
		public void read_A_Products () {
			
			Response response = 
			given()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type","application/json; charset=UTF-8")
				.queryParam("id", "1305")
			.when()
				.get("/read_one.php")
			.then()
				.extract().response();
			
			String responseBody = response.getBody().asString();
			System.out.println("ResponseBody : " + responseBody);
			
			//Parsing reposonsBody to Json:
			JsonPath js = new JsonPath(responseBody);
				js.prettyPrint();
				System.out.println(js);
			String productId= js.getString("id"); 
			String productName= js.getString("name"); 
			String productDescription= js.getString("description"); 
			
			Assert.assertEquals(productId, "1305");
			Assert.assertEquals(productName, "Harry Potter 7");
			Assert.assertEquals(productDescription, "fiction");

			System.out.println(productId);
			
			
			int statusCode = response.getStatusCode();
			System.out.println("This is the statuscode: " + statusCode);
			Assert.assertEquals(statusCode, 200);
			
			softAssert.assertEquals(statusCode, 201);
	
			response.getBody().prettyPrint();
			
			
			long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
			System.out.println("This is the response time: "+  responseTime);
						
			if (responseTime <=1000) {
				System.out.println("Response time within the range");
			}else {
				System.out.println("Not acceptable!!");
			}
			
		
		}
		

	}
	
	


