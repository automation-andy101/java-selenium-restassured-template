package api.stepdefinitions;

import api.models.response.ToDo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.hamcrest.MatcherAssert;
import utils.RestRequestHandler;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

public class ToDoApiStepDefinitions {
    public Pair<List<ToDo>, Integer> toDoResponse;
    public Pair<Response, Integer> deleteToDoResponse;

    @When("I send a request to GET all todos")
    public void sendRequestToGetAllTodos() throws IOException {
        RestRequestHandler restRequestHandler = new RestRequestHandler();

        // Get list of all ToDos
        toDoResponse = restRequestHandler.getTodos();
//        List<ToDo> toDos = toDoResponse.getLeft();
    }

    @Then("the response status code is 200")
    public void responseStatusCodeIs200(int expectedStatusCode) throws IOException {
        RestRequestHandler restRequestHandler = new RestRequestHandler();

        MatcherAssert.assertThat(toDoResponse.getRight(), equalTo(expectedStatusCode));
    }
}
