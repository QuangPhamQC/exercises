package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import commons.BasePage;
import cucumberOptions.Hooks;
import interfaces.LoginUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStep extends BasePage {
	WebDriver driver;
	TestContext testContext;

	public LoginStep(TestContext context) {
		driver = Hooks.openAndQuitBrowser();
		testContext = context;
	}

	@Given("Go to Login page")
	public void goToLoginPage() {
		openPageURL(driver, "https://demo.nopcommerce.com/login?returnUrl=%2F");
	}

	@When("Click on {string} button in Login page")
	public void clickOnButtonInLoginPage(String string) {
		clickOnElement(driver, LoginUI.BUTTON_ON_LOGIN_PAGE, string);
		sleepInSecond(2);
	}

	@Then("Verify {string} error message in Email field in Login page")
	public void verifyErrorMessageInEmailField(String string) {
	}

	@When("Input {string} value in {string} textbox in Login page")
	public void inputValueInTextboxInLoginPage(String criteriaValue, String textboxName) {
		inputToElement(driver, LoginUI.TEXTBOX_FIELD, criteriaValue,textboxName);
	}

	@Then("Verify an error message in Login page")
	public void verifyAnErrorMessageInLoginPage() {
		String errorMessage = getElementText(driver, LoginUI.ERROR_MESSAGE);
		String replaceErrorMessage = errorMessage.replace('\n', ' ');
		Assert.assertEquals(replaceErrorMessage, "Login was unsuccessful. Please correct the errors and try again. No customer account found");
	}

	@Then("Verify user can login successful on page")
	public void verifyUserCanLoginSuccessfulOnPage() {
		Assert.assertEquals(getElementText(driver, LoginUI.LOG_OUT_BUTTON), "Log out");
	}
}
