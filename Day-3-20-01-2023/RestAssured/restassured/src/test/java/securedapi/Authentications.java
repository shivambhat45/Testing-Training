package securedapi;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//https://toolsqa.com/rest-assured/basic-auth/

public class Authentications {

	@Test
	public void basicauth()
	{
//		 the basic authentication scheme uses 
//		 the username and password in base64 encoded format.
		given().auth()
		  .basic("postman", "password")
		  .when()
		  .get("https://postman-echo.com/basic-auth")
		  .then()
//		  .log()
//		  .all();
		  .assertThat()
		  .statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void premptiveauth()
	{
		given().auth()
		  .preemptive()
		  .basic("postman", "password")
		  .when()
		  .get("https://postman-echo.com/basic-auth")
		  .then()
		  .assertThat()
		  .statusCode(HttpStatus.SC_OK);
	}
	
	@Test
	public void digestauth()
	{
		given().auth()
		  .digest("postman", "password")
		  .when()
		  .get("https://postman-echo.com/basic-auth")
		  .then()
		  .assertThat()
		  .statusCode(HttpStatus.SC_OK);
	}
	
	public void formauth()
	{
//		given().auth()
//		  .form("user1", "user1Pass")
//		  .when()
//		  // ...
		
//		given().auth()
//		  .form(
//		    "user1",
//		    "user1Pass",
//		    new FormAuthConfig("/perform_login", "username", "password"))
//		  // ...

	}
	
	
//	https://github.com/CourseRepository/WebServiceTestingusingRestAssured
	
	public void oauth()
	{
//		OAuth 1.0
//		given().auth().oauth(consumerKey, consumerSecret, accessToken, tokenSecret).get("your end point URL")

//		OAuth 2.0
//		given().auth().oauth2("Access token").get("your end point URL")
	}
}
