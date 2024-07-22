package ui.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
//        features = "src/test/resources/features/ui",
        features = {"classpath:features/ui"},
//        glue = "src/test/java/ui/stepdefinitions",
        glue = {"ui.stepdefinitions", "ui.hooks"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TestRunner {
}