package dataDriven;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
public class dataDrivenExample extends dataForTests {

    @Test(dataProvider = "dataProviderInput1",priority = 0)
    public void test_post01(String name, String company, int id) {
        baseURI = "http://localhost:3000/";

        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("company",company);
        json.put("id",id);
        System.out.println(json.toJSONString());

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(json.toJSONString()).
                when().
                post("/posts").
                then().
                statusCode(201).
                log().
                all();
    }

    @Test(dataProvider = "dataProviderInput2",priority = 1)
    public void test_post02(String name, String company, int id) {
        baseURI = "http://localhost:3000/";

        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("company",company);
        json.put("id",id);

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(json.toJSONString()).
                when().
                post("/posts").
                then().
                statusCode(201).
                log().
                all();
    }

    @Test(dataProvider = "dataProviderDelete",priority = 2)
    public void test_delete01(int id) {
        baseURI = "http://localhost:3000/";

        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                delete("/posts/" + id).
                then().
                statusCode(200).
                log().
                all();
    }
}
