package ui.stepdefinitions;

import api.models.response.ToDo;
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

    @When("I enter {string} into the add new todo input element")
    public void addNewTodoInputElement(String text) throws IOException {
        todoNamesToDelete.add(text);
        toDoListPage.enterTextIntoAddNewToDoInputBox(text, Duration.ofSeconds(10));
    }

    @When("select the current date")
    public void selectCurrentDate() throws IOException {
        toDoListPage.selectTodaysDateFromDateSelector(Duration.ofSeconds(5));
    }

    @And("click the Add button")
    public void clickAddButton() {
        toDoListPage.clickAddNewTodoButton(Duration.ofSeconds(5));
    }

    @Then("new todo {string} appears in the todo list")
    public void assertTableTodoTableContainsNewEntry(String expectedText) {
        Assert.assertTrue("Expected todo with name " + expectedText + " to be present, but was not", toDoListPage.searchTodoTableForName(expectedText));
    }
}
