package stepDefinitions;

import commons.BasePage;
import cucumberOptions.Hooks;
import interfaces.LoginUI;
import interfaces.RegisterUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class MyAccountStep extends BasePage {
	WebDriver driver;
	TestContext testContext;

	public MyAccountStep(TestContext context) {
		driver = Hooks.openAndQuitBrowser();
		testContext = context;
	}



}
