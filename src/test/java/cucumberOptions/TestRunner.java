package cucumberOptions;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features/04_Search.feature"},
	strict = false,
	monochrome = true,
	glue = { "stepDefinitions", "cucumberOptions" }, 
	plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"pretty", 
		"json:target/cucumber.json", "html:target/cucumber-report-default",
		"rerun:target/failedtestcases.txt"}, 
	snippets = SnippetType.CAMELCASE,
	tags = {"@Search"},
	dryRun = false
	)

public class TestRunner {
    
}