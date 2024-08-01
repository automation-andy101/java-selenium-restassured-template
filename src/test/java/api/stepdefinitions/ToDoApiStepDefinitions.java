package api.stepdefinitions;

import api.models.requests.ToDoRequest;
import api.models.response.ToDo;
import api.models.response.ToDoResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.hamcrest.MatcherAssert;
import utils.RestRequestHandler;

import static api.testdata.ToDoTestData.createToDo;
import static api.testdata.ToDoTestData.updateToDo;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static utils.Utils.getDateTimeNow;

public class ToDoApiStepDefinitions {
    public Pair<List<ToDo>, Integer> toDosResponse;
    public Pair<ToDoResponse, Integer> toDoResponse;
    public Pair<ToDoResponse, Integer> createNewToDoResponse;
    public Pair<Response, Integer> updateToDoResponse;
    public Pair<Response, Integer> deleteToDoResponse;
    private int todoId;

    @When("I send a GET request to retrieve all todos")
    public void sendRequestToGetAllTodos() throws IOException {
        RestRequestHandler restRequestHandler = new RestRequestHandler();

        // Get list of all ToDos
        toDosResponse = restRequestHandler.getTodos();
    }

    @Then("the response status code for getting all Todos is {int}")
    public void responseStatusCodeForGettingAllTodosIs200(int expectedStatusCode) {
        MatcherAssert.assertThat(toDosResponse.getRight(), equalTo(expectedStatusCode));
    }

    @And("the response contains a list of todos")
    public void responseContainsListTodos() throws IOException {
        // Get Todos List
        List<ToDo> todos = toDosResponse.getLeft();

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

    @And("I extract the ID of the first todo in the list")
    public void extractIdOfFirstTodoInList() throws IOException {
        RestRequestHandler restRequestHandler = new RestRequestHandler();

        // Get list of all ToDos
        toDosResponse = restRequestHandler.getTodos();
        // Get ID of first Todos
        todoId = toDosResponse.getLeft().get(1).getId();
    }

    @When("I send a GET request for the todo with the extracted ID")
    public void sendRequestToGetASingleTodoById() throws IOException {
        RestRequestHandler restRequestHandler = new RestRequestHandler();

        // Get a single Todo
        toDoResponse = restRequestHandler.getTodoById(todoId);
        // Get ID of first Todos
        todoId = toDoResponse.getLeft().getId();
    }

    @Then("the response status code for getting a single Todo is {int}")
    public void responseStatusCodeForGettingASingleTodoIs200(int expectedStatusCode) {
        MatcherAssert.assertThat(toDoResponse.getRight(), equalTo(expectedStatusCode));
    }

    @Then("the response status code for creating a new Todo is {int}")
    public void responseStatusCodeForCreatingANewTodoIs201(int expectedStatusCode) {
        MatcherAssert.assertThat(createNewToDoResponse.getRight(), equalTo(expectedStatusCode));
    }

    @Then("the response status code for updating a Todo is {int}")
    public void responseStatusCodeForUpdatingATodoIs204(int expectedStatusCode) {
        MatcherAssert.assertThat(updateToDoResponse.getRight(), equalTo(expectedStatusCode));
    }

    @Then("the response status code for deleting a Todo is {int}")
    public void responseStatusCodeForDeletingATodoIs204(int expectedStatusCode) {
        MatcherAssert.assertThat(deleteToDoResponse.getRight(), equalTo(expectedStatusCode));
    }

    @And("the response contains the details of the todo with the requested ID")
    public void assertResponseContainsExpectedToDo() throws IOException {
        assertTrue("Response should be an instance of a Todo object", toDoResponse.getLeft() instanceof ToDoResponse);
        assertNotNull("Todo ID should not be null", toDoResponse.getLeft().getId());
        assertNotNull("Todo Name should not be null", toDoResponse.getLeft().getName());
        assertNotNull("Todo Completed status should not be null", toDoResponse.getLeft().getIsComplete());
        assertNotNull("Todo Date Due should not be null", toDoResponse.getLeft().getDateDue());
    }

    @When("I send a POST request to create a new todo with the following data")
    public void sendPostRequestToCreateNewTodo(DataTable dataTable) throws IOException {
        String name = dataTable.cell(1, 0);
        boolean isComplete = Boolean.parseBoolean(dataTable.cell(1, 1));
        String dueDate = dataTable.cell(1, 2);

        if (Objects.equals(dueDate, "DATETIMENOW")) {
            dueDate = getDateTimeNow("yyyy-MM-dd'T'HH:mm:ss");
        }

        ToDoRequest toDoRequest = createToDo(name, isComplete, dueDate);

        RestRequestHandler restRequestHandler = new RestRequestHandler();
        createNewToDoResponse = restRequestHandler.createNewTodo(toDoRequest);
    }

    @And("the response should contain the created todo with name {string} and isComplete set to {string}")
    public void assertResponseBodyContainsNewlyCreatedTodo(String expectedName, String expectedIsComplete) throws IOException {
        String todoNameErrorMessage = String.format("Expected property value to be '%s' but was '%s'", expectedName, createNewToDoResponse.getLeft().getName());
        String todoIsCompleteErrorMessage = String.format("Expected property value to be '%s' but was '%s'", expectedIsComplete, createNewToDoResponse.getLeft().getIsComplete());

        assertTrue("Response should be an instance of a Todo object", createNewToDoResponse.getLeft() instanceof ToDoResponse);
        assertNotNull("Todo ID should not be null", createNewToDoResponse.getLeft().getId());
        assertEquals(todoNameErrorMessage, expectedName, createNewToDoResponse.getLeft().getName());
        assertEquals(todoIsCompleteErrorMessage, expectedIsComplete, createNewToDoResponse.getLeft().getIsComplete());
        assertNotNull("Todo Date Due should not be null", createNewToDoResponse.getLeft().getDateDue());
    }

    @When("I send a PUT request to update the todo with the following data")
    public void sendPutRequestToUpdateATodo(DataTable dataTable) throws IOException {
        String name = dataTable.cell(1, 0);
        boolean isComplete = Boolean.parseBoolean(dataTable.cell(1, 1));
        String dueDate = toDosResponse.getLeft().get(1).getDateDue();

        ToDoRequest toDoRequest = updateToDo(name, isComplete, dueDate);

        RestRequestHandler restRequestHandler = new RestRequestHandler();
        updateToDoResponse = restRequestHandler.updateTodo(toDoRequest, todoId);
    }

    @When("I send a DELETE request to remove the todo")
    public void sendDeleteRequestToRemoveATodo() throws IOException {
        RestRequestHandler restRequestHandler = new RestRequestHandler();
        deleteToDoResponse = restRequestHandler.deleteToDo(todoId);
    }
}
