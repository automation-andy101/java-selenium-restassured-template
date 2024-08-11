package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class ToDoListPage extends BasePage {
    private final By addNewToDoInput = By.id("add-name");
    private final By addNewToDoDate = By.id("add-date");
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
    public void enterTextIntoAddNewToDoInputBox(String text, Duration timeout) throws IOException {
        enterText(addNewToDoInput, text, timeout);
    }

    /**
     * Open/close date selection element.
     *
     * @param timeout the timeout in seconds to wait for the add new todo date selection element to be visible
     */
    public void openOnDateSelector(Duration timeout) {
        clickElement(addNewToDoDate, timeout);
    }

    /**
     * Enter todays date
     *
     * @param timeout the timeout in seconds to wait for the add new todo input field element to be visible
     */
    public void selectTodaysDateFromDateSelector() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(today);
        enterText(addNewToDoDate, formattedDate, Duration.ofSeconds(5));
    }

    /**
     * Click Add button element.
     */
    public void clickAddNewTodoButton() {
        clickElementUsingJS(addNewToDobutton, Duration.ofSeconds(5));
    }

    /**
     * Search the todo list table for a specific todo name then click the delete button
     *
     * @param expectedNameText the todo name looking for
     */
    public void clickDeleteTodoButton(String expectedNameText) throws InterruptedException {
        WebElement table = getElementWhenVisible(toDosTable, Duration.ofSeconds(5));
        List<WebElement> elements = table.findElements(By.xpath("//tbody[@id='todos']//td[text()='" + expectedNameText + "'])"));

        int i = 1;
        for (WebElement element : elements) {
            System.out.println(element);
//            clickElement(By.xpath("(//tbody[@id='todos']//td[text()='" + expectedNameText + "'])[" + i + "]/following-sibling::*[3]//button"), Duration.ofSeconds(5));
            Thread.sleep(5000);
            i++;
        }


//        WebElement table = getElementWhenVisible(toDosTable, Duration.ofSeconds(5));
//        List<WebElement> rows = table.findElements(By.tagName("tr"));


//
//        int i = 1;
//        List<WebElement> cells;
//        for (WebElement row : rows) {
//
//            System.out.println("HELLO WORLD - " + i);
//            System.out.println("HELLO WORLD row - " + row);
//            cells = null;
//            cells = row.findElements(By.tagName("td"));
//
//            WebElement nameCell = cells.get(1);
//            if (nameCell.getText().contains(expectedNameText)) {
//                System.out.println("Found text - " + nameCell.getText() + " " + i);
//                System.out.println("XPath is - " + "(//tbody[@id='todos']//td[text()='" + expectedNameText + "'])[" + i + "]/following-sibling::*[3]//button");
////                           clickElement(By.xpath("(//tbody[@id='todos']//td[text()='TEST TODO'])[1]               /following-sibling::*[3]//button"), Duration.ofSeconds(5));
//                clickElement(By.xpath("(//tbody[@id='todos']//td[text()='" + expectedNameText + "'])[" + i + "]/following-sibling::*[3]//button"), Duration.ofSeconds(5));
//                Thread.sleep(5000);
//                i--;
//            }
//            i++;
//        }
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
        return getElementText(locatorForTableCell, Duration.ofSeconds(5));
    }

    /**
     * Search the todo list table for a specific todo name
     *
     * @param expectedNameText the todo name looking for
     * @return true if todo name was found, else false
     */
    public boolean searchTodoTableForName(String expectedNameText) {
        WebElement table = getElementWhenVisible(toDosTable, Duration.ofSeconds(5));
        List<WebElement> rows = table.findElements((By.tagName("tr")));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            WebElement nameCell = cells.get(1);
            if (nameCell.getText().contains(expectedNameText)) {
                return true;
            }
        }

        return false;
    }
}
