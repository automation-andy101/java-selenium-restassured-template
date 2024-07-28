package api.stepdefinitions;

import api.models.response.ToDo;
import api.models.response.ToDoResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import utils.RestRequestHandler;
import static org.junit.Assert.*;
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
    }

    @Then("the response status code is {int}")
    public void responseStatusCodeIs200(int expectedStatusCode) {
        MatcherAssert.assertThat(toDoResponse.getRight(), equalTo(expectedStatusCode));
    }

    @And("the response contains a list of todos")
    public void responseContainsListTodos() throws IOException {
        // Get Todos List
        List<ToDo> todos = toDoResponse.getLeft();

        // Assert that the response is a list of Todo objects
        assertNotNull("The list of todos should not be null", todos);
        assertTrue("The list of todos should not be empty", todos.size() > 0);

        for (ToDo todo : todos) {
            assertNotNull("Todo object should not be null", todo);
            assertTrue("Each item in the list should be an instance of Todo", todo instanceof ToDo);
            assertNotNull("Todo ID should not be null", todo.getId());
            assertNotNull("Todo Name should not be null", todo.getName());
            assertNotNull("Todo Completed status should not be null", todo.getIsComplete());
            assertNotNull("Todo Date Due should not be null", todo.getDateDue());
        }
    }
}
