package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ToDoListPage extends BasePage {
    private final By addNewToDoInput = By.id("add-name");
    private final By addNewToDoCalenderExpander = By.id("add-date");
    private final By addNewToDobutton = By.id("add");
    private final By toDosTable = By.id("todos");

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
        enterText(addNewToDoInput, text, timeout);
    }

    /**
     * Open/close date selection element.
     *
     * @param timeout the timeout in seconds to wait for the add new todo input field element to be visible
     */
    public void openCloseAddNewTodoCalender(Duration timeout) {
        clickElement(addNewToDoCalenderExpander, timeout);
    }

    /**
     * Click Add button element.
     *
     * @param timeout the timeout in seconds to wait for the add new todo button element to be visible
     */
    public void clickAddNewTodoButton(Duration timeout) {
        clickElement(addNewToDobutton, timeout);
    }

    /**
     * Get column Name text for row n
     *
     * @param rowNum the row number
     * @return row n, Name column text
     */
    public String getNameTextForRow(int rowNum) {
        int nameColumnNum = 2;
        By locatorForTableCell = By.xpath("//tbody[@id='todos']/tr[" + rowNum + "]/td[" + nameColumnNum + "]");
        return getElementText(locatorForTableCell, Duration.ofSeconds(10));
    }
}
