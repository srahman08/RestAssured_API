package testCase;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class Read_A_Product_Calling_Method {

	
	public void read_A_Products_Calling (String valueId) {
		
		Response response = 
		given()
			.baseUri("https://techfios.com/api-prod/api/product")
			.header("Content-Type","application/json; charset=UTF-8")
			.queryParam("id", valueId)
		.when()
			.get("/read_one.php")
		.then()
			.extract().response();
		
		String responseBody = response.getBody().asString();
		System.out.println("ResponseBody : " + responseBody);
		response.getBody().prettyPrint();
}
}