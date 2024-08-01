package ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;

/**
 * Base class for all page objects, providing common functionalities.
 */
public class BasePage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    /**
     * Constructor to initialize WebDriver and WebDriverWait.
     *
     * @param driver the WebDriver instance to use for interacting with the browser
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }

    /**
     * Navigates the browser to the specified URL.
     * <p>
     * This method will log the URL being navigated to and then use the WebDriver's
     * {@code get} method to load the URL.
     * </p>
     *
     * @param url the URL to navigate to
     * @throws IllegalArgumentException if the URL is null or empty
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Waits for the specified element to be visible within the given timeout and returns it.
     *
     * @param locator the By locator of the element to be visible
     * @param timeout the timeout in seconds to wait for the element to be visible
     * @return the WebElement that is visible
     */
    public WebElement getElementWhenVisible(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for the specified element to be clickable within the given timeout and returns it.
     *
     * @param locator the By locator of the element to be clickable
     * @param timeout the timeout in seconds to wait for the element to be clickable
     * @return the WebElement that is clickable
     */
    public WebElement getElementWhenClickable(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * checks if a web element is displayed on the page within a given timeout.
     *
     * @param locator the locator used to find the web element
     * @param timeout the maximum time to wait for the element to become displayed
     * @throws NoSuchElementException if the element cannot be found
     * @throws TimeoutException if the element is not displayed within the specified timeout
     * @see org.openqa.selenium.By
     * @see java.time.Duration
     */
    public boolean isElementDisplayed(By locator, Duration timeout) {
        try {
            WebElement element = getElementWhenVisible(locator, timeout);
            return element.isDisplayed();
        } catch (Exception e) {
            logger.error("Element with locator " + locator.toString() + " is not displayed.");
            return false;
        }
    }

    /**
     * Enter text into a web element located by the specified locator within a given timeout.
     *
     * @param locator the locator used to find the web element
     * @param text the text a user wants to enter into the web element
     * @param timeout the maximum time to wait for the element to become clickable
     * @throws NoSuchElementException if the element cannot be found
     * @throws TimeoutException if the element is not clickable within the specified timeout
     * @see org.openqa.selenium.By
     * @see java.time.Duration
     */
    public void enterText(By locator, String text, Duration timeout) {
        WebElement element = getElementWhenVisible(locator, timeout);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Waits for the element to be visible and returns its text.
     *
     * @param locator the By locator of the element
     * @param timeout the maximum time to wait for the element to become clickable
     * @return the text of the element
     */
    public String getElementText(By locator, Duration timeout) {
        WebElement element = getElementWhenVisible(locator, timeout);
        return element.getText();
    }

    /**
     * Waits for the element to be visible then click it
     *
     * @param locator the By locator of the element
     * @param timeout the maximum time to wait for the element to become clickable
     */
    public void clickElement(By locator, Duration timeout) {
        WebElement element = getElementWhenClickable(locator, timeout);
    }

    /**
     * Waits for the element to be clickable, then click on it, then press ENTER key on it
     *
     * @param locator the By locator of the element
     * @param timeout the maximum time to wait for the element to become clickable
     */
    public void pressEnterOnElement(By locator, Duration timeout) {
        WebElement element = getElementWhenVisible(locator, timeout);
        Actions actions = new Actions(driver);
        actions.click(element).sendKeys(Keys.ENTER).perform();
    }

    /**
     * Get text from a web element located by the specified locator within a given timeout.
     *
     * @param locator the locator used to find the web element
     * @param timeout the maximum time to wait for the element to become clickable
     * @return the text of the element
     * @throws NoSuchElementException if the element cannot be found
     * @throws TimeoutException if the element is not clickable within the specified timeout
     * @see org.openqa.selenium.By
     * @see java.time.Duration
     */
    public String getTextUsingJS(By locator, Duration timeout) {
        WebElement element = getElementWhenVisible(locator, timeout);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].textContent;", element);
    }

    /**
     * Enter text into a web element located by the specified locator within a given timeout.
     *
     * @param locator the locator used to find the web element
     * @param text the text a user wants to enter into the web element
     * @param timeout the maximum time to wait for the element to become clickable
     * @throws NoSuchElementException if the element cannot be found
     * @throws TimeoutException if the element is not clickable within the specified timeout
     * @see org.openqa.selenium.By
     * @see java.time.Duration
     */
    public void enterTextUsingJS(By locator, String text, Duration timeout) {
        WebElement element = getElementWhenVisible(locator, timeout);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + text + "';", element);
    }

}
