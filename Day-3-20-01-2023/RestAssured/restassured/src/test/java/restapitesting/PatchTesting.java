package restapitesting;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class PatchTesting {

	String patchURL = "http://localhost:3000/posts/2";
//	HashMap<String,String> map =new HashMap();

	@Test
	public void patch_01()
	{
		JSONObject json =new JSONObject();
		json.put("name","hope");
//		json.put("company", "Sapient");
		
		given()
			.header("Content-Type", "application/json; charset=utf-8")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(json.toJSONString())
		.when()
			.patch(patchURL)
		.then()
			.statusCode(200);

		
	}
	

}
