package ui.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
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
import ui.stepdefinitions.AddNewToDoStepDefinitions;
import utils.ReadPropertiesFile;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class Hooks {
    private static WebDriver driver;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent;
    private static ExtentTest extentTest;

    @BeforeAll
    public static void beforeAll() {
        extent = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\test-output\\testReport.html");
        extent.attachReporter(sparkReporter);

        sparkReporter.config(
                ExtentSparkReporterConfig.builder()
                        .theme(Theme.DARK)
                        .documentTitle("Selenium UI Test Report")
                        .offlineMode(true)
                        .build()
        );
    }

    @Before
    public void setUp() throws IOException {
        System.out.println("ENTERED INTO BEFORE BLOCK");
        String testType = System.getProperty("testType", "api");

        String browser = System.getProperty("browser", "chrome");
        String environment = System.getProperty("env", "dev");
        String baseUrl = "";

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximised");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximised");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                SafariOptions safariOptions = new SafariOptions();
                driver = new SafariDriver(safariOptions);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        switch (environment.toLowerCase()) {
            case "dev":
                baseUrl = ReadPropertiesFile.readProperty("dev-todo-base-url");
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


    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                Utils.takeScreenshotUsingAShort(driver, scenario.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Quit WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    @After(value = "@DeleteCreatedTodoAfterTest")
    public void afterScenarioDeleteTodo() throws IOException {
        List<String> todos = AddNewToDoStepDefinitions.todoNamesToDelete;

        for (String todo : todos) {
            Utils.deleteTodoWithName(todo);
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
