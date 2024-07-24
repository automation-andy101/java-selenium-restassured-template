package ui.stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import ui.hooks.Hooks;
import ui.pages.ToDoListPage;

import java.time.Duration;

public class AddNewToDoStepDefinitions {
    private WebDriver driver = Hooks.getDriver();
    ToDoListPage toDoListPage = new ToDoListPage(driver);

//    @Given("the todo list is empty")
//    public void the_todo_list_is_empty() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }

    @When("I enter {string} into the add new todo input element")
    public void addNewTodoInputElement(String text) {
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
