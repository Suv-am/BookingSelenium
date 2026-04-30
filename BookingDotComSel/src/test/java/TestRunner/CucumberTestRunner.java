package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/Features",glue="StepDefinition",plugin={"pretty","html:target/htmlReport.html"})
public class CucumberTestRunner extends AbstractTestNGCucumberTests {

}
