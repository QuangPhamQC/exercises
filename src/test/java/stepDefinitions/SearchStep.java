package stepDefinitions;

import commons.BasePage;
import cucumberOptions.Hooks;
import interfaces.SearchUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class SearchStep extends BasePage{
    WebDriver driver;
    TestContext testContext;

    public SearchStep(TestContext context) {
        driver = Hooks.openAndQuitBrowser();
        testContext = context;
    }
    @Given("Go to Search page")
    public void goToSearchPage() {
        openPageURL(driver,"https://demo.nopcommerce.com/search");
    }

    @When("Click on Search button in Search page")
    public void clickOnButtonInSearchPage() {
        clickOnElement(driver, SearchUI.SEARCH_BUTTON);
    }

    @Then("Verify {string} error message when searching in Search page")
    public void verifyErrorMessageWhenSearchingWithValueInSearchPage(String expected) {
        Assert.assertEquals(expected, getElementText(driver, SearchUI.ERROR_WARNING_MESSAGE));
    }

    @When("Input {string} value in Search textbox in Search page")
    public void inputValueInTextboxInSearchPage(String string) {
        String defaultText = getElementTextByJS_4(driver, SearchUI.SEARCH_TEXTBOX);
        System.out.println(defaultText);
        if (defaultText == null){
            inputToElement(driver, SearchUI.SEARCH_TEXTBOX, string);
        } else {
            clearToElement(driver, SearchUI.SEARCH_TEXTBOX);
            inputToElement(driver, SearchUI.SEARCH_TEXTBOX, string);
        }
    }

    @Then("Verify {string} all data results displayed in Search page")
    public void verifyAllDataResultsDisplayedInSearchPage(String string) {
        int numberResult = getElementSize(driver, SearchUI.SEARCH_RESULT);
        if (numberResult == Integer.parseInt(string)){
            System.out.println("All products displayed on page.");
        } else {
            System.out.println("Please check the result again.");
        }
    }
}
