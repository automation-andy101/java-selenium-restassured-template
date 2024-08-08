package ui.stepdefinitions;

import api.models.requests.ToDoRequest;
import api.models.response.ToDo;
import api.models.response.ToDoResponse;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ui.hooks.Hooks;
import ui.pages.ToDoListPage;
import utils.RestRequestHandler;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static api.testdata.ToDoTestData.createToDo;
import static utils.Utils.getDateTimeNow;

public class AddNewToDoStepDefinitions {
    private WebDriver driver = Hooks.getDriver();
    ToDoListPage toDoListPage = new ToDoListPage(driver);
    public Pair<List<ToDo>, Integer> toDoResponse;
    public Pair<List<ToDo>, Integer> toDoResponse2;
    public Pair<Response, Integer> deleteToDoResponse;
    public static List<String> todoNamesToDelete = new ArrayList<>();

    @Given("the todo list is empty")
    public void todoListIsEmpty() throws IOException {
        RestRequestHandler restRequestHandler = new RestRequestHandler();

        // Get list of all ToDos
        toDoResponse = restRequestHandler.getTodos();
        List<ToDo> toDos = toDoResponse.getLeft();

        // Loop through
        for (ToDo toDo : toDos) {
            deleteToDoResponse = restRequestHandler.deleteToDo(toDo.getId());
        }
    }

    @Given("a new todo has been created with the following data")
    public void createANewTodo(DataTable dataTable) throws IOException {
        String name = dataTable.cell(1, 0);
        boolean isComplete = Boolean.parseBoolean(dataTable.cell(1, 1));
        String dueDate = dataTable.cell(1, 2);

        if (Objects.equals(dueDate, "DATETIMENOW")) {
            dueDate = getDateTimeNow("yyyy-MM-dd'T'HH:mm:ss");
        }

        ToDoRequest toDoRequest = createToDo(name, isComplete, dueDate);

        RestRequestHandler restRequestHandler = new RestRequestHandler();
        Pair<ToDoResponse, Integer> createNewToDoResponse = restRequestHandler.createNewTodo(toDoRequest);
    }

    @When("I enter {string} into the add new todo input element")
    public void addNewTodoInputElement(String text) throws IOException {
        todoNamesToDelete.add(text);
        toDoListPage.enterTextIntoAddNewToDoInputBox(text, Duration.ofSeconds(10));
    }

    @When("select the current date")
    public void selectCurrentDate() throws IOException {
        toDoListPage.selectTodaysDateFromDateSelector();
    }

    @When("I click the Delete button for todo with name {string}")
    public void clickDeleteButtonForTodoWithName(String text) throws IOException {
        toDoListPage.clickDeleteTodoButton(text);
    }

    @And("click the Add button")
    public void clickAddButton() {
        toDoListPage.clickAddNewTodoButton();
    }

    @Then("new todo {string} appears in the todo list")
    public void assertTableTodoTableContainsNewEntry(String expectedText) {
        Assert.assertTrue("Expected todo with name " + expectedText + " to be present, but was not", toDoListPage.searchTodoTableForName(expectedText));
    }
}
