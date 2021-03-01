 package testCase;

	import org.testng.Assert;
	import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
	import io.restassured.response.ResponseBody;

	import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;  

	public class Put_Or_Update_A_Product {
		
		SoftAssert softAssert = new SoftAssert();
		
		Read_A_Product_Calling_Method readAproduct = new Read_A_Product_Calling_Method();
		
		@Test
		public void Update_A_Product () {
			
			HashMap payload = new HashMap();
			payload.put("id", "1305");
			payload.put("name", "Harry Potter 7");
			payload.put("description", "fiction");
			payload.put("price", "99");
			payload.put("category_name", "Books");
			payload.put("category_id", "4");
			
			Response response = 
			given()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type","application/json; charset=UTF-8")
				.body(payload)
			.when()
				.put("/update.php")
			.then()
				.extract().response();
			
			String responseBody = response.getBody().asString();
			System.out.println("ResponseBody : " + responseBody);

			JsonPath js = new JsonPath(responseBody);
			String message= js.getString("message"); 
			softAssert.assertEquals(message, "Product was updated.", "Not as expected");
			
			softAssert.assertAll();
			
			readAproduct.read_A_Products_Calling("1305");
			
			
		}
	}
