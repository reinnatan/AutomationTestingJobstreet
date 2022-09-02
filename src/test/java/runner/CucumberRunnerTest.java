package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.Test;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "json:target/cucumber-html-reports/cucumber.json","rerun:target/failed_scenarios.txt"
        },
        tags = "", features = "src/test/resources/features/BrowserJavaDeveloperJobsFind.feature",
        glue = "definitions", monochrome = true)
public class CucumberRunnerTest extends AbstractTestNGCucumberTests {
}
