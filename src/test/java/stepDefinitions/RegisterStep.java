package stepDefinitions;

import commons.BasePage;
import cucumberOptions.Hooks;
import interfaces.LoginUI;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import interfaces.RegisterUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterStep extends BasePage {
	WebDriver driver;
	TestContext testContext;

	public RegisterStep(TestContext context) {
		driver = Hooks.openAndQuitBrowser();
		testContext = context;
	}

	@When("Click on {string} button in Register page")
	public void clickOnButtonInRegisterPage(String string) {
		clickOnElement(driver, LoginUI.BUTTON_ON_LOGIN_PAGE, string);
		sleepInSecond(2);
	}

	@Then("Verify {string} error message in First name field in Register page")
	public void verifyErrorMessageInFirstNameFieldInRegisterPage(String string) {}

	@Then("Verify {string} error message in Last name field in Register page")
	public void verifyErrorMessageInLastNameFieldInRegisterPage(String string) {}

	@Then("Verify {string} error message in Email field in Register page")
	public void verifyErrorMessageInEmailFieldInRegisterPage(String string) {}

	@Then("Verify {string} error message in Password field in Register page")
	public void verifyErrorMessageInPasswordFieldInRegisterPage(String string) {}

	@Then("Verify {string} error message in Confirm password field in Register page")
	public void verifyErrorMessageInConfirmPasswordFieldInRegisterPage(String string) {}

	@Given("Go to Register page")
	public void goToRegisterPage() {
		openPageURL(driver, "https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@When("Input {string} value in {string} textbox in Register page")
	public void inputValueInTextboxInRegisterPage(String criteriaValue, String textboxName) {
		inputToElement(driver, LoginUI.TEXTBOX_FIELD, criteriaValue,textboxName);
	}

	@When("Click on {string} value in Register page")
	public void clickOnValueInRegisterPage(String string) {
		clickOnElement(driver, RegisterUI.GENDER_RADIO_BUTTON, string);
	}

	@When("Select {string} value in {string} dropdown list in Register page")
	public void selectValueInDayDropdownListInRegisterPage(String value, String string) {
		selectItemInDropdown(driver, RegisterUI.PARENT_DROPDOWN_LIST, RegisterUI.CHILD_DROPDOWN_LIST, value, string);
	}

	@When("Check the {string} checkbox in Register page")
	public void checkTheCheckboxInRegisterPage(String string) {
		checkToDefaultCheckboxRadio(driver, RegisterUI.CHECKBOX_ITEM, string);
	}

	@Then("Verify {string} successfully message in Register page")
	public void verifySuccessfullyMessageInRegisterPage(String string) {
		Assert.assertEquals(getElementText(driver, RegisterUI.SUCCESSFULLY_MESSAGE), string);
	}

	@Then("Verify {string} error message for exist email case in Register page")
	public void verifyErrorMessageForExistEmailCaseInRegisterPage(String string) {
		Assert.assertEquals(getElementText(driver, RegisterUI.ERROR_MESSAGE_EXIST_EMAIL), string);
	}

	@Then("Verify {string} error message for incorrect password in Register page")
	public void verifyErrorMessageForIncorrectPasswordInRegisterPage(String string) {
		if (string.contains("confirmation password")) {
			Assert.assertEquals(getElementText(driver, RegisterUI.ERROR_MESSAGE_CONFIRM_PASSWORD), string);
		} else {
			String errorMessage = getElementText(driver, RegisterUI.ERROR_MESSAGE_PASSWORD);
			String replaceErrorMessage = errorMessage.replace("\n", " ");
			Assert.assertEquals(replaceErrorMessage, string);
		}
	}

}
