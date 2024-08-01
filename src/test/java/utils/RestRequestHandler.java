package utils;

import api.models.requests.ToDoRequest;
import api.models.response.ToDo;
import api.models.response.ToDoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.List;


public class RestRequestHandler {
    private final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    String environment = System.getProperty("env", "dev");
    String baseUrl = Utils.getBaseUrl(environment);
    private Response response;

    public RestRequestHandler() throws IOException {
    }

    private Pair<Response, Integer> restAssuredGetRequest(String url) {
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

    private Pair<Response, Integer> restAssuredPostRequest(String url, Object requestBody) {
        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("CanAccess", "true")
                .body(requestBody)
                .post(url)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        return Pair.of(response, statusCode);
    }

    private Pair<Response, Integer> restAssuredPutRequest(String url, Object requestBody) {
        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("CanAccess", "true")
                .body(requestBody)
                .put(url)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        return Pair.of(response, statusCode);
    }

    private Pair<Response, Integer> restAssuredDeleteRequest(String url) {
        Response response = RestAssured.given()
                .log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .header("Accept-Language", "en-US,en;q=0.9")
                .header("CanAccess", "true")
                .delete(url)
                .then()
                .extract().response();

        int statusCode = response.getStatusCode();

        return Pair.of(response, statusCode);
    }

    public Pair<List<ToDo>, Integer> getTodos() throws IOException {
        String url = baseUrl + ReadPropertiesFile.readProperty("get-todos-endpoint");

        Pair<Response, Integer> responsePair = restAssuredGetRequest(url);
        Response response = responsePair.getLeft();
        int statusCode = responsePair.getRight();

        List<ToDo> toDosResponse = mapper.readValue(response.getBody().asString(), new TypeReference<List<ToDo>>(){});
        return  Pair.of(toDosResponse, statusCode);
    }

    public Pair<ToDoResponse, Integer> getTodoById(int id) throws IOException {
        String url = baseUrl + ReadPropertiesFile.readProperty("get-todo-endpoint");
        url = url.replace("ID", Integer.toString(id));

        Pair<Response, Integer> responsePair = restAssuredGetRequest(url);
        Response response = responsePair.getLeft();
        int statusCode = responsePair.getRight();

        ToDoResponse toDoResponse = mapper.readValue(response.getBody().asString(), ToDoResponse.class);
        return  Pair.of(toDoResponse, statusCode);
    }

    public Pair<ToDoResponse, Integer> createNewTodo(ToDoRequest todo) throws IOException {
        String url = baseUrl + ReadPropertiesFile.readProperty("create-todo-endpoint");

        Pair<Response, Integer> responsePair = restAssuredPostRequest(url, todo);
        Response response = responsePair.getLeft();
        int statusCode = responsePair.getRight();

        ToDoResponse toDoResponse = mapper.readValue(response.getBody().asString(), ToDoResponse.class);
        return  Pair.of(toDoResponse, statusCode);
    }

    public Pair<Response, Integer> updateTodo(ToDoRequest todo, int id) throws IOException {
        String url = baseUrl + ReadPropertiesFile.readProperty("update-todo-endpoint");
        url = url.replace("ID", Integer.toString(id));

        Pair<Response, Integer> responsePair = restAssuredPutRequest(url, todo);
        Response response = responsePair.getLeft();
        int statusCode = responsePair.getRight();

        return  Pair.of(response, statusCode);
    }

    public Pair<Response, Integer> deleteToDo(int id) throws IOException {
        String url = baseUrl + ReadPropertiesFile.readProperty("delete-todo-endpoint");
        url = url.replace("ID", Integer.toString(id));

        Pair<Response, Integer> responsePair = restAssuredDeleteRequest(url);
        Response response = responsePair.getLeft();
        int statusCode = responsePair.getRight();

        return  Pair.of(response, statusCode);
    }
}
