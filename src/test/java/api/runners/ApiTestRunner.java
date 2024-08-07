package api.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/api"},
        glue = {"api.stepdefinitions", "api.hooks"},
//        plugin = {"pretty", "html:target/cucumber-reports"}
        plugin = {"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)
public class ApiTestRunner {
}