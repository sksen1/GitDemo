package cucumber.options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/features",
    glue = {"stepdefination"},
    tags = "",
    plugin = {
        "pretty", // optional for readable console output
        "json:target/jsonReports/cucumber-report.json",
        "html:target/htmlReports" // generates HTML report here
    }
)
public class TestRunner {
    // This class will run Cucumber tests and generate HTML and JSON reports
}
