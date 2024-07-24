package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;


public class RestRequestHandler {
    private final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    String environment = System.getProperty("env", "dev");
    String baseUrl = Utils.getBaseUrl(environment);
    private Response response;

    public RestRequestHandler() throws IOException {
    }

    private Pair<Response, Integer> getRequest(String url) {
        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("CanAccess", "true")
                .get(url)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        return Pair.of(response, statusCode);
    }
}
