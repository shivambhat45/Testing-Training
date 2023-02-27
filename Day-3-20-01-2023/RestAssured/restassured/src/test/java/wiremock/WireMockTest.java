package wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;

public class WireMockTest {
	
    WireMockServer wireMockServer;
    private static int portNumber = 4040;

	@BeforeTest
    public void setup () {
        wireMockServer = new WireMockServer(wireMockConfig().port(portNumber));
        wireMockServer.start();
        RestAssured.baseURI = "http://127.0.0.1";
        RestAssured.port = portNumber;
        setupStub();
    }
	
    @AfterTest
    public void teardown () {
        wireMockServer.stop();
    }
    
    public void setupStub() {
        configureFor("127.0.0.1", portNumber);

        wireMockServer.stubFor(get(urlEqualTo("/an/endpoint"))
                .willReturn(aResponse().withHeader("Content-Type", "text/plain")
                        .withStatus(200)
                        .withBodyFile("json/glossary.json")));
    }
    
    @Test
    public void testStatusCodePositive() {
        Response response = given().
                when().
                get("/an/endpoint").
                then().
                extract().response();

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
    }

    @Test
    public void testStatusCodeNegative() {
        Response response = given().
                when().
                get("/another/endpoint").
                then().
                extract().response();

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void testResponseContents() {
        Response response =  given().when().get("/an/endpoint");
        System.out.println(response.getBody().asPrettyString());
        String title = response.jsonPath().getString("glossary.title");

        System.out.println(title);
        Assert.assertEquals("example glossary", title);
    }

}
