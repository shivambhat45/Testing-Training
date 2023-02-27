package restapitesting;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import java.util.HashMap;


public class PostTesting {
	
	String postURL = "http://localhost:3000/posts";
	HashMap<String,String> map =new HashMap();
	@Test
	public void post_01()
	{
//		JSONObject json =new JSONObject();
//		json.put("name","shivam");
//		json.put("company", "Sapient");

		map.put("name","shivam");
		map.put("company", "Sapient");
//		System.out.println(json.toJSONString());
		
        given().
        header("Content-Type", "application/json; charset=utf-8").
//        contentType(ContentType.JSON).
        accept(ContentType.JSON).
  
//        body(json.toJSONString()).
        body(map).
        when().
        	post(postURL).
        then().
	        statusCode(201).
	        log().
	        all();
		
	}

}
