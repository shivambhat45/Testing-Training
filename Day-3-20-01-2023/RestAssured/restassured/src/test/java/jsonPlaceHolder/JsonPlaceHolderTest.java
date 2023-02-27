package jsonPlaceHolder;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class JsonPlaceHolderTest {
	
		// Passing Json as a string

		private static String postRequestBody = "{\n" + "  \"title\": \"foo\",\n" + "  \"body\": \"bar\",\n"
				+ "  \"userId\": \"1\" \n}";

		private static String putRequestBody = "{\n" + "  \"title\": \"foo\",\n" + "  \"body\": \"baz\",\n"
				+ "  \"userId\": \"1\",\n" + "  \"id\": \"1\" \n}";

		private static String patchRequestBody = "{\n" + "  \"title\": \"bax\" \n}";
		
		@BeforeTest
		public void setup() {
			baseURI = "https://jsonplaceholder.typicode.com";
		}

		@Test
		public void getRequest() {
			Response response = given().contentType(ContentType.JSON).when().get("/posts").then().extract().response();

			System.out.println(response.jsonPath());

			Assert.assertEquals(200, response.statusCode());
			Assert.assertEquals("qui est esse", response.jsonPath().getString("title[1]"));
			Assert.assertEquals("qui est esse", response.jsonPath().get("title[1]"));
		}

		@Test
		public void getRequestWithQueryParam() {
			Response response = given().contentType(ContentType.JSON).param("postId", "2").when().get("/comments")
					.then().log().all().extract().response();

			System.out.println(response.prettyPrint());

//        System.out.println(response.jsonPath().getString("postId"));

			Assert.assertEquals(200, response.statusCode());
			Assert.assertEquals("Meghan_Littel@rene.us", response.jsonPath().getString("email[3]"));

		}

		@Test
		public void postRequest() {
			Response response = given().header("Content-type", "application/json").and().body(postRequestBody).when()
					.post("/posts").then().log().all().extract().response();

			Assert.assertEquals(201, response.statusCode());
			Assert.assertEquals("foo", response.jsonPath().getString("title"));
			Assert.assertEquals("bar", response.jsonPath().getString("body"));
			Assert.assertEquals("1", response.jsonPath().getString("userId"));
			Assert.assertEquals("101", response.jsonPath().getString("id"));
		}

		@Test
		public void putRequest() {
			Response response = given().header("Content-type", "application/json").and().body(putRequestBody).when()
					.put("/posts/1").then().log().all().extract().response();

			Assert.assertEquals(200, response.statusCode());
			Assert.assertEquals("foo", response.jsonPath().getString("title"));
			Assert.assertEquals("baz", response.jsonPath().getString("body"));
			Assert.assertEquals("1", response.jsonPath().getString("userId"));
			Assert.assertEquals("1", response.jsonPath().getString("id"));
		}

		@Test
		public void patchRequest() {
			Response response = given().header("Content-type", "application/json").and().body(patchRequestBody).when()
					.patch("/posts/1").then().extract().response();

			Assert.assertEquals(200, response.statusCode());
			Assert.assertEquals("bax", response.jsonPath().getString("title"));
			Assert.assertEquals("1", response.jsonPath().getString("userId"));
			Assert.assertEquals("1", response.jsonPath().getString("id"));
		}

		@Test
		public void deleteRequest() {
			Response response = given().header("Content-type", "application/json").when().delete("/posts/1").then()
					.extract().response();

			Assert.assertEquals(200, response.statusCode());
		}

	}


