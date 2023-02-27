package restAssuredExercise;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Exercise {

    @BeforeTest
    public void before() {
        baseURI = "https://reqres.in/";
    }
	
//	1.How many “id” s are present in the response body
	@Test
    public void test_01()
	{
        Response response=given().get("/api/unknown").
        then().
        extract().
        response();
        
        List<Object> arr= response.jsonPath().getList("data.id");
        System.out.println(arr.size());
        
        // O/P = 6
	}
	
//	2.Get the name attribute  where the corresponding “color” is matching to #BF1932
	@Test
	public void test_02()
	{
        Response response=given().get("/api/unknown").
        then().
        extract().
        response();
        
        System.out.println(response.jsonPath().getString("data.find{it.color=='#BF1932'}.name"));
      
        // O/P = true red
	}

// 3. Print only the pantone_value ’s present in the response body
	@Test
	public void test_03()
	{
        Response response=given().get("/api/unknown").
        then().
        extract().
        response();
        
        System.out.println(response.jsonPath().getString("data.pantone_value"));
        
        // O/P = [15-4020, 17-2031, 19-1664, 14-4811, 17-1456, 15-5217]
	}
	
}
