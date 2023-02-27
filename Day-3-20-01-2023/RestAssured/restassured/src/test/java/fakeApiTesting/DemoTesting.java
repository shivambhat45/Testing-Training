package fakeApiTesting;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.baseURI;

public class DemoTesting {

    private static int idReturned;
    
    @BeforeTest
    public void before() {
        baseURI = "http://localhost:3000/";
    }
	
    @Test(priority =0)
    public void test_get01() {

        given().
                get("/posts").
                then().
                statusCode(200).
                log().all();
    }
    
    @Test (priority = 1)
    public void test_get02() {

        given().
                param("name","hope").
        when().
                get("/posts").
        then().
                statusCode(200).
                log().all();
    }
    
    @Test (priority = 2)
    public void test_post01() {

        JSONObject json = new JSONObject();
        json.put("firstName","Sixth");
        json.put("secondName","Six");
        json.put("comment","Hi!!!");

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(json.toJSONString())
                .when()
                .post("/comments")
                .then()
                .extract().response();

        idReturned = response.jsonPath().get("id");

    }

    @Test(priority = 3)
    public void test_put01() {

        JSONObject json = new JSONObject();

        json.put("firstName","First");
        json.put("secondName","One");
        json.put("comment","Bye---See---You");

        given().
                header("Content-Type", "application/json; charset=utf-8").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(json.toJSONString()).
                when().
                put("/comments/2").
                then().statusCode(200).
                log().all();
    }

    @Test(priority = 4)
    public void test_patch01() {

        JSONObject json = new JSONObject();
        json.put("secondName","Zero");

        given().
                header("Content-Type", "application/json; charset=utf-8").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(json.toJSONString()).
                when().
                patch("/comments/2").
                then().statusCode(200).
                log().all();
    }

    @Test(priority = 5)
    public void test_delete01() {
        System.out.println(idReturned);
        given().
                header("Content-Type", "application/json; charset=utf-8").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                delete("/comments/" + idReturned).
                then().statusCode(200).
                log().all();
    }

}
