package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//cucumber can provide anything what testNG provides
//but to run cucumber, it should rely on testNG or JUnit
@CucumberOptions(features = "src/test/java/cucumber", glue = "AutomationTestingPractice.stepDefinitions", monochrome = true, plugin = {
		"html:target/cucumber.html" }, tags= "@Regression")
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
