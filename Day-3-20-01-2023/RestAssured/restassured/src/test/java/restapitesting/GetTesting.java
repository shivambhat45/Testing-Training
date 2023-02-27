package restapitesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class GetTesting {

	String getUrl= "https://reqres.in/api/users?page=2";
	
	@Test
	public void get_method01()
	{
		Response response=RestAssured.get(getUrl);
		
		System.out.println(response.getBody().asString());
		System.out.println(response.getBody().asPrettyString());
		System.out.println(response.getStatusCode());
		System.out.println(response.asPrettyString());
		System.out.println(response.getHeader("content-type"));
		System.out.println(response.getStatusLine());
		
		Assert.assertEquals(response.statusCode(), 200);
	}
	
	@Test
	public void get_method02()
	{
		RestAssured.get(getUrl)
		.then().statusCode(200)
		.body("data[2].first_name",equalTo("Tobias"));
		
		
//		.body("data[$(@.id==10)].first_name",equalTo("Byron"));
		// Not Possible as 
		// Rest Assured uses Groovy's GPath notation and is 
		//not to be confused with Jayway's JsonPath syntax
		
	}
	
}
