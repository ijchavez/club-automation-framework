package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"tests"},
        features = "src/test/resources/features",
        tags = {"@Logins"},
        plugin = {"pretty", "html:test-output","html:target/site/cucumber-pretty","json:test-output/cucumber.json","json:target/cucumber.json"})

public class RunTests {
}
