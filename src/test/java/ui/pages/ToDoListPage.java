package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ToDoListPage extends BasePage {
    private final By newToDoInput = By.id("add-name");

    /**
     * Constructor
     *
     * @param driver the WebDriver instance to use for interacting with the browser
     */
    public ToDoListPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Enters text into the add new todo input field.
     *
     * @param text the username to enter
     * @param timeout the timeout in seconds to wait for the add new todo input field element to be visible
     */
    public void enterTextIntoAddNewToDoInputBox(String text, Duration timeout) {
        enterText(newToDoInput, text, timeout);
    }

    /**
     * Enters text into the add new todo input field.
     *
     * @param timeout the timeout in seconds to wait for the add new todo input field element to be visible
     */
    public void openAddNewTodoCalender(Duration timeout) {

    }

}
