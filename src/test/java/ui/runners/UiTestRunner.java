package ui.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"classpath:features/ui"},
        glue = {"ui.stepdefinitions", "ui.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class UiTestRunner {
}