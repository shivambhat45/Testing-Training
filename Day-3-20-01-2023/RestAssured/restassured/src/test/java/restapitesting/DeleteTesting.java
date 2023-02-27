package restapitesting;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
public class DeleteTesting {
	

	 String deleteURL = "https://reqres.in/api/users/2";
	    @Test
	    public void TCDelete() {

//	        given().
//	                header("Content-Type", "application/json; charset=utf-8").
//	                contentType(ContentType.JSON).
//	                accept(ContentType.JSON).
	        when().
	                delete(deleteURL).
	        then().
	                statusCode(204).
	                statusLine("HTTP/1.1 204 No Content").
	                log().
	                all();
	    }
}
