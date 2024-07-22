package ui.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import ui.utils.ReadPropertiesFile;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Hooks {
//    private static WebDriver driver;
//    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static WebDriver driver;

    @Before
    public void setUp() throws IOException {
        System.out.println("ENTERED INTO BEFORE BLOCK");
        String browser = System.getProperty("browser", "chrome");
        String environment = System.getProperty("env", "dev");
        String baseUrl = "";

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions  = new ChromeOptions();
                chromeOptions.addArguments("--start-maximised");
//                driver.set(new ChromeDriver(chromeOptions));
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions  = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximised");
//                driver.set(new FirefoxDriver(firefoxOptions));
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
//                driver.set(new EdgeDriver(edgeOptions));
                driver = new EdgeDriver(edgeOptions);
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
//                driver.set(new SafariDriver(safariOptions));
                driver = new SafariDriver(safariOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        switch (environment.toLowerCase()) {
            case "dev":
                baseUrl = ReadPropertiesFile.readProperty("dev-todo-base-url");
                System.out.println("HELLO WORLD!!!!");
                System.out.println(baseUrl);
                driver.get(baseUrl);
                break;
            case "test":
                baseUrl = ReadPropertiesFile.readProperty("test-todo-base-url");
                driver.get(baseUrl);
                break;
            default:
                throw new IllegalArgumentException("Unsupported environment: " + environment);
        }
    }

//    @After
//    public void tearDown() {
//        if (driver.get() != null) {
//            driver.get().quit();
//            driver.remove();
//        }
//    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
