package cucumberOptions;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"@target/failedtestcases.txt"}, 
	strict = false,
	monochrome = true,
	glue = { "stepDefinitions", "cucumberOptions" }, 
	plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", 
		"pretty", 
		"json:target/cucumber.json", "html:test-output",
		"rerun:target/failedtestcases.txt"}, 
	tags = { "@Smoketest" },
	dryRun = false
	)

public class FailedTestCases {

}