package restapitesting;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutTesting {
	
	String putURL = "http://localhost:3000/posts/2";
//	HashMap<String,String> map =new HashMap();

	@Test
	public void put_01()
	{
		JSONObject json =new JSONObject();
		json.put("name","shivam45");
		json.put("company", "Sapient-Noida");
		
		given()
			.header("Content-Type", "application/json; charset=utf-8")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(json.toJSONString())
		.when()
			.put(putURL)
		.then()
			.statusCode(200);
//			.log()
//			.all();
		
	}

}
