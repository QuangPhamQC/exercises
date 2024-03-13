package stepDefinitions;

import commons.BasePage;
import cucumberOptions.Hooks;
import interfaces.LoginUI;
import interfaces.MyAccountUI;
import interfaces.RegisterUI;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountStep extends BasePage {
	WebDriver driver;
	TestContext testContext;

	public MyAccountStep(TestContext context) {
		driver = Hooks.openAndQuitBrowser();
		testContext = context;
	}

	@When("Click on My Account link")
	public void clickOnMyAccountLink() {
		clickOnElement(driver, MyAccountUI.MY_ACCOUNT_LINK);
	}

	@When("Click on {string} link")
	public void clickOnLink(String string) {
		if (string.contains(" ")){
			String textLocator = string.toLowerCase().replace(' ', '-');
			clickOnElement(driver, MyAccountUI.LIST_ITEM_LINK, textLocator);
		} else {
			String textLowCase = string.toLowerCase();
			clickOnElement(driver, MyAccountUI.LIST_ITEM_LINK, textLowCase);
		}
	}

	@Then("Verify {string} gender radio button is checked in My Account page")
	public void verifyGenderRadioButtonIsCheckedInMyAccountPage(String string) {
		checkToDefaultCheckboxRadio(driver, MyAccountUI.GENDER_RADIO_BUTTON, string.substring(0,1));
	}

	@Then("Verify {string} value in {string} textbox in My Account page")
	public void verifyValueInTextboxInMyAccountPage(String string, String textName) {
		Assert.assertEquals(string, getAttributeValue(driver, LoginUI.TEXTBOX_FIELD, "value", textName));
	}

	@Then("Verify {string} value in {string} dropdown list in My Account page")
	public void verifyValueInDropdownListInMyAccountPage(String string, String var) {
		String xpathDropDownList = "//select[contains(@name, '" + var + "']";
		Assert.assertEquals(string,getSelectedItemDefaultDropdown(driver,xpathDropDownList));
	}

	@Then("Verify the {string} checkbox in My Account page")
	public void verifyTheCheckboxInMyAccountPage(String string) {
		checkToDefaultCheckboxRadio(driver, RegisterUI.CHECKBOX_ITEM, string);
	}

	@When("Select another {string} value in {string} dropdown list in My Account page")
	public void selectAnotherValueInDropdownListInMyAccountPage(String value, String string) {
		selectItemInDropdown(driver, RegisterUI.PARENT_DROPDOWN_LIST, RegisterUI.CHILD_DROPDOWN_LIST, value, string);
	}

	@When("Edit {string} value in {string} textbox in My Account page")
	public void editValueInTextboxInMyAccountPage(String criteriaValue, String textboxName) {
		clearToElement(driver, LoginUI.TEXTBOX_FIELD, textboxName);
		inputToElement(driver, LoginUI.TEXTBOX_FIELD, criteriaValue,textboxName);
	}

	@When("Click on {string} button in My Account page")
	public void clickOnSaveButtonInMyAccountPage(String string) {
		clickOnElement(driver, MyAccountUI.BUTTON_LINK, string);
	}

	@Then("Verify the {string} successful message showed in My Account page")
	public void verifyTheSuccessfulMessageShowedInMyAccountPage(String string) {
		Assert.assertEquals(string, getElementText(driver, MyAccountUI.SUCCESSFUL_MESSAGE));
	}

	@When("Input {string} value in {string} textbox in My Account page")
	public void inputValueInTextboxInMyAccountPage(String criteriaValue, String textboxName) {
		inputToElement(driver, LoginUI.TEXTBOX_FIELD, criteriaValue,textboxName);
	}

	@When("Select {string} value in {string} dropdown list in My Account page")
	public void selectValueInDropdownListInMyAccountPage(String value, String text) {
		selectItemInDropdown(driver, MyAccountUI.PARENT_DROPDOWN_LIST, MyAccountUI.CHILD_DROPDOWN_LIST, value, text);
		sleepInSecond(2);
	}

	@Then("Verify {string} and {string} values in {string} field in My Account page")
	public void verifyAndValuesInFieldInMyAccountPage(String firstName, String lastName, String fieldName) {
		String expectedResult = firstName + " " + lastName;
		String fieldLocator = fieldName.toLowerCase();
		Assert.assertEquals(expectedResult, getElementText(driver, MyAccountUI.ADDRESSES_INFO_LABEL, fieldLocator));
	}

	@Then("Verify {string} value in {string} field in My Account page")
	public void verifyValueInFieldInMyAccountPage(String string, String fieldName) {
		String fieldLocator = fieldName.toLowerCase();
		if (fieldLocator.equals("email") || fieldLocator.equals("phone") || fieldLocator.equals("fax") ){
			String expectedValue = getElementText(driver, MyAccountUI.ADDRESSES_TITLE_LABEL, fieldLocator) + getElementText(driver, MyAccountUI.ADDRESSES_INFO_LABEL, fieldLocator);
			if (expectedValue.contains(string)){
				System.out.println("True");
			}
		} else {
			Assert.assertEquals(string, getElementText(driver, MyAccountUI.ADDRESSES_INFO_LABEL, fieldLocator));
		}
	}

	@Then("Verify {string}, {string} and {string} values in {string} field in My Account page")
	public void verifyAndValuesInFieldInMyAccountPage(String city, String state, String zip, String fieldName) {
		String expectedValue = city + ", " + state + ", " + zip;
		String fieldLocator = fieldName.toLowerCase();
		Assert.assertEquals(expectedValue, getElementText(driver, MyAccountUI.ADDRESSES_INFO_LABEL, fieldLocator));
	}

	@When("Click on {string} button to close the successful message in My Account page")
	public void clickOnButtonToCloseTheSuccessfulMessageInMyAccountPage(String string) {
		clickOnElement(driver, MyAccountUI.CLOSE_SUCCESSFUL_MESSAGE);
		sleepInSecond(2);
	}

	@When("Click on {string} link in My Account page")
	public void clickOnLinkInMyAccountPage(String string) {
		clickOnElement(driver, MyAccountUI.LOG_IN_OUT_LINK, string.toLowerCase().replace(" ", ""));
	}

	@When("Click on {string} header tab in My Account page")
	public void clickOnHeaderTabInMyAccountPage(String string) {
		clickOnElement(driver, MyAccountUI.HEADER_TAB_LINK, string);
	}

	@When("Click on {string} a link in My Account page")
	public void clickOnALinkInMyAccountPage(String string) {
		clickOnElement(driver, MyAccountUI.PRODUCT_LINK, string);
	}
	@When("Click on {string} a link in specific product in My Account page")
	public void clickOnALinkInSpecificProductInMyAccountPage(String string) {
		clickOnElement(driver, MyAccountUI.ADD_REVIEW_LINK, string);
	}

	@When("Input {string} text in {string} textbox in My Account page")
	public void inputTextInTextboxInMyAccountPage(String value, String textboxName) {
		if (textboxName.equals("Review title")){
			inputToElement(driver, MyAccountUI.REVIEW_TITLE_TEXTBOX, value, textboxName.toLowerCase().replace(' ', '-'));
		} else if (textboxName.equals("Review text")) {
			inputToElement(driver, MyAccountUI.REVIEW_TEXT_TEXTBOX, value, textboxName.toLowerCase().replace(' ', '-'));
		}
	}

	@When("Select {string} a rating point from One to Five of Rating field in My Account page")
	public void selectARatingPointFromOneToFiveOfRatingFieldInMyAccountPage(String string) {
		clickOnElement(driver, MyAccountUI.RATING_RADIO_BUTTON, string);
	}

	@Then("Verify {string} a successfull message displayed in My Account page")
	public void verifyASuccessfullMessageDisplayedInMyAccountPage(String string) {
		Assert.assertEquals(string, getElementText(driver, MyAccountUI.SUCCESSFULL_MESSAGE_REVIEW).replace('\n', ' '));
	}
}
