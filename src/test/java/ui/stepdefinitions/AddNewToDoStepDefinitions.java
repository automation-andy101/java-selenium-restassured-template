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
import java.util.List;

public class AddNewToDoStepDefinitions {
    private WebDriver driver = Hooks.getDriver();
    ToDoListPage toDoListPage = new ToDoListPage(driver);
    public Pair<List<ToDo>, Integer> toDoResponse;
    public Pair<List<ToDo>, Integer> toDoResponse2;
    public Pair<Response, Integer> deleteToDoResponse;

    @Given("the todo list is empty")
    public void the_todo_list_is_empty() throws IOException {
        RestRequestHandler restRequestHandler = new RestRequestHandler();

        // Get list of all ToDos
        toDoResponse = restRequestHandler.getTodos();
        System.out.println("BOOB!!!!!!");
        System.out.println(toDoResponse.getLeft().get(1));
        System.out.println(toDoResponse.getLeft());
        List<ToDo> toDos = toDoResponse.getLeft();

        // Loop through
        for (ToDo toDo : toDos) {
            deleteToDoResponse = restRequestHandler.deleteToDo(toDo.getId());
            System.out.println("BOOB222222222!!!!!!");
            System.out.println(deleteToDoResponse.getRight());
        }

        toDoResponse2 = restRequestHandler.getTodos();
        System.out.println("BOOB3!!!!!!");
        System.out.println(toDoResponse2.getLeft().get(1));
        System.out.println(toDoResponse2.getLeft());

    }

    @When("I enter {string} into the add new todo input element")
    public void addNewTodoInputElement(String text) throws InterruptedException {
        Thread.sleep(20000);
        toDoListPage.enterTextIntoAddNewToDoInputBox(text, Duration.ofSeconds(10));
    }

    @When("select the current date")
    public void selectCurrentDate() {
        toDoListPage.selectTodaysDateFromDateSelector(Duration.ofSeconds(10));
    }

    @And("click the Add button")
    public void clickAddButton() throws InterruptedException {
        toDoListPage.clickAddNewTodoButton(Duration.ofSeconds(10));
    }

    @Then("table row {int} Name column contains {string}")
    public void assertTableRowNameCellText(int rowNum, String expectedText) throws InterruptedException {
        String actualText = toDoListPage.getNameTextForRow(rowNum);
        Assert.assertEquals("Expected Name text at Row " + rowNum + " to be " + expectedText + " but got " + actualText, actualText, expectedText);
        Thread.sleep(20000);
    }
}
