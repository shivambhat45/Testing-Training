package wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;

public class WireMockSingleEndPoint {

    WireMockServer wireMockServer;
    private static int portNumber = 5055;

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

        stubFor(get(urlEqualTo("/some/thing"))
                .withHeader("Accept", matching("text/plain"))
                .willReturn(aResponse().
                        withStatus(503).
                        withHeader("Content-Type", "text/html").
                        withBody("Service Not Available"))
        );

        stubFor(get(urlEqualTo("/some/thing"))
                .withHeader("Accept", matching("application/json"))
                .willReturn(aResponse().
                        withStatus(200).
                        withHeader("Content-Type", "application/json")
                        .withBody("{\"serviceStatus\": \"running\"}")
                        .withFixedDelay(2500))
        );
    }

    @Test
    public void serviceUnavailable503() {

        Response response = given()
                .header(new Header("Accept", "text/plain"))
                .when()
                .get("/some/thing");
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_SERVICE_UNAVAILABLE);
    }

    @Test
    public void serviceAvailable200() {

        Response response = given()
                .header(new Header("Accept", "application/json"))
                .when()
                .get("/some/thing");

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.jsonPath().getString("serviceStatus"), "running");
    }

    @Test
    public void serviceNotFound404() {

        Response response = given()
                .header(new Header("Accept", "application/json"))
                .when()
                .get("/some/thing/is/wrong");

        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
    }

}
