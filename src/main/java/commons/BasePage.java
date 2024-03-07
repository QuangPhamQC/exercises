package commons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaces.BasePageUI;
import io.github.sukgu.Shadow;
import utils.DownloadFile;

public class BasePage {
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private long trivialTimeout = GlobalConstants.TRIVIAL_TIMEOUT;
	protected DownloadFile downloadFile;
	protected String tempFolderPath = GlobalConstants.PROJECT_PATH + File.separator + "target" + File.separator;
	private FileWriter myWriter;

	public static BasePage getBasePage() {
		return new BasePage();
	}

	/*************************** Web Browser *******************************/
	public void openPageURL(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public Set<Cookie> getAllCookie(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setAllCookie(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void openNewTabChromeBrowser(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.open()");
	}

	/***** Alert *****/
	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void senkeyAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	/***** Window Tab *****/
	public String getCurrentWindowID(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public void switchToWinDownByID(WebDriver driver, String currentWindowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(currentWindowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWinDownByPageTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle().trim();
			if (actualPageTitle.equals(tabTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	/*************************** Web Element *******************************/
	private WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(getByXpath(xpathLocator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		return driver.findElements(getByXpath(getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)));
	}

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}

	private String getDynamicXpathLocator(String dynamicXpathLocator, String... dynamicValues) {
		return String.format(dynamicXpathLocator, (Object[]) dynamicValues);
	}

	/***** Click Function *****/
	public void clickOnElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}

	public void clickOnElement(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).click();
	}

	/** Clear Function **/
	public void clearToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).clear();

	}

	public void clearToElement(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).clear();

	}

	public void clearToElementByKeyboardAction(WebDriver driver, String xpathLocator) {
		clickOnElement(driver, xpathLocator);
		getWebElement(driver, xpathLocator).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getWebElement(driver, xpathLocator).sendKeys(Keys.chord(Keys.DELETE));

	}

	public void clearToElementByKeyboardAction(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		clickOnElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).sendKeys(Keys.chord(Keys.DELETE));
	}

	/** Input Function **/
	public void inputToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.sendKeys(textValue);
	}

	public void inputToElement(WebDriver driver, String dynamicXpathLocator, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		element.sendKeys(textValue);
	}

	public void inputKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.sendKeys(key);
	}

	public void inputKeyToElement(WebDriver driver, String dynamicXpathLocator, Keys key, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		element.sendKeys(key);
	}

	/***** Get Element Size *****/
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	public int getElementSize(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).size();
	}

	/***** Get Element Text *****/
	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText().trim();
	}

	public String getElementText(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).getText().trim();
	}

	/***** Select Dropdown Value *****/
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String dynamicXpathLocator, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)));
		select.selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdownByValue(WebDriver driver, String xpathLocator, String value) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByValue(value);
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDefaultDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}

	public void selectItemInDropdown(WebDriver driver, String parentXpathLocator, String dynamicChildXpathLocatorList, String expectedTextItem) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(parentXpathLocator))).click();
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(dynamicChildXpathLocatorList)));
		for (WebElement item : allItems) {
			if (item.getText().trim().contains(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectItemInDropdown(WebDriver driver, String parentDynamicXpathLocator, String dynamicChildXpathLocatorList, String expectedTextItem, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicXpathLocator(parentDynamicXpathLocator, dynamicValues)))).click();
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(getDynamicXpathLocator(dynamicChildXpathLocatorList, dynamicValues))));
		for (WebElement item : allItems) {
			if (item.getText().trim().contains(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectEditableDropdown(WebDriver driver, String textBoxXpathLocator, String childXpathLocatorList, String expectedTextItem) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		WebElement element = getWebElement(driver, textBoxXpathLocator);
		element.clear();
		element.sendKeys(expectedTextItem);
		sleepInSecond(2);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpathLocatorList)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectEditableDropdown(WebDriver driver, String dynamicParentXpathLocator, String dynamicChildXpathLocatorList, String expectedTextItem, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		WebElement element = getWebElement(driver, getDynamicXpathLocator(dynamicParentXpathLocator, dynamicValues));
		element.clear();
		element.sendKeys(expectedTextItem);
		sleepInSecond(2);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(getDynamicXpathLocator(dynamicChildXpathLocatorList, dynamicValues))));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				if (item.isDisplayed()) {
					item.click();
				} else {
					JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

	public void selectValueInElementList(WebDriver driver, String childXpathLocator, String expectedTextItem) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childXpathLocator)));
		for (WebElement item : allItems) {
			if (item.getText().trim().contains(expectedTextItem)) {
				scrollToElementBottom(driver, item);
				item.click();
				break;
			}
		}
	}

	public void selectValueInElementList(WebDriver driver, String dynamicChildXpathLocatorList, String expectedTextItem, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(getDynamicXpathLocator(dynamicChildXpathLocatorList, dynamicValues))));
		for (WebElement item : allItems) {
			if (item.getText().trim().contains(expectedTextItem)) {
				scrollToElementBottom(driver, item);
				item.click();
				break;
			}
		}
	}

	/**** Get Attribute Value *****/
	public String getAttributeValue(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	public String getAttributeValue(WebDriver driver, String dynamicXpathLocator, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).getAttribute(attributeName);
	}

	public String getAttributeValueByJS(WebDriver driver, String xpathLocator, String attributeName) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String actualText = "";
		actualText = (String) jsExecutor.executeScript("return arguments[0].getAttribute(\"" + attributeName + "\");", getWebElement(driver, xpathLocator));
		if (actualText == "" || actualText == null) {
			System.out.println(actualText);
			return actualText;
		} else {
			return actualText.trim();
		}

	}

	public String getAttributeValueByJS(WebDriver driver, String dynamicXpathLocator, String attributeName, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String actualText = "";
		actualText = (String) jsExecutor.executeScript("return arguments[0].getAttribute(\"" + attributeName + "\");", getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)));
		if (actualText == "" || actualText == null) {
			return actualText;
		} else {
			return actualText.trim();
		}
	}

	/**** Check/Uncheck Radio/Checkbox *****/
	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	public boolean isElementSelected(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).isSelected();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	/**** Check Display/Undisplay Element *****/
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();

	}

	public boolean isElementDisplayed(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).isDisplayed();

	}

	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		overrideImplicitTimeout(driver, trivialTimeout);
		List<WebElement> elements = getListWebElement(driver, xpathLocator);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementExistedInList(WebDriver driver, String xpathLocator, String expectedValue) {
		waitForElementPresence(driver, xpathLocator);
		List<WebElement> listElement = getListWebElement(driver, xpathLocator);
		for (WebElement listItem : listElement) {
			String context = listItem.getText().trim();
			if (context.contains(expectedValue)) {
				return true;
			}
		}

		return false;
	}

	public boolean isElementExistedInList(WebDriver driver, String dynamicXpathLocator, String expectedValue, String... dynamicValues) {
		waitForElementPresence(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		List<WebElement> listElement = getListWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		for (WebElement listItem : listElement) {
			String context = listItem.getText().trim();
			if (context.contains(expectedValue)) {
				return true;
			}
		}

		return false;
	}

	protected boolean isElementExistedInAllListItem(WebDriver driver, String xpathLocator, String expectedTextItem) {
		boolean status = false;
		List<WebElement> allItems = getListWebElement(driver, xpathLocator);
		if (allItems.size() == 0) {
			return false;
		} else {
			for (WebElement item : allItems) {
				if (item.getText().trim().contains(expectedTextItem.trim())) {
					status = true;
				} else {
					status = false;
					break;
				}
			}
		}
		return status;
	}

	/**** Check Enable Element *****/
	public boolean isElementEnable(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	public boolean isElementEnable(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)).isEnabled();
	}

	/**** Frame/IFrame *****/
	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}

	public void switchOutIframe(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**** Mouse/Keyboard Action *****/
	public void clickMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.click(getWebElement(driver, xpathLocator)).perform();
	}

	public void clickMouseToElement(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.click(getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues))).perform();
	}

	public void moveAndClickMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).click().perform();
	}

	public void rightClickMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, xpathLocator)).perform();
	}

	public void rightClickMouseToElement(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues))).perform();
	}

	public void doubleClickMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, xpathLocator)).perform();
	}

	public void doubleClickMouseToElement(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues))).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).build().perform();
	}

	public void hoverMouseToElement(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues))).build().perform();
	}

	public void dragAndDropElement(WebDriver driver, String sourceXpathLocator, String destXpathLocator) {
		WebElement sourceElement = getWebElement(driver, sourceXpathLocator);
		WebElement desteElement = getWebElement(driver, destXpathLocator);

		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, desteElement).build().perform();
	}

	public void dragAndDropElementByClickAndHold(WebDriver driver, String sourceXpathLocator, String destXpathLocator) {
		WebElement sourceElement = getWebElement(driver, sourceXpathLocator);
		WebElement desteElement = getWebElement(driver, destXpathLocator);

		Actions action = new Actions(driver);
		action.clickAndHold(sourceElement).moveToElement(desteElement).release(desteElement).perform();
	}

	public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, xpathLocator), key).perform();

	}

	public void pressKey(WebDriver driver, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(key).build().perform();

	}

	public void pressKeyToElement(WebDriver driver, String dynamicXpathLocator, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)), key).perform();

	}

	/***************** Table *****************/
	public int getColumnIndexOfTable(WebDriver driver, String xpathLocator, String columneName) {
		int columnOrderNumber = 1;
		overrideImplicitTimeout(driver, trivialTimeout);
		if (getElementSize(driver, xpathLocator, columneName) > 0) {
			columnOrderNumber = getElementSize(driver, xpathLocator, columneName) + 1;
		}
		overrideImplicitTimeout(driver, longTimeout);
		return columnOrderNumber;
	}

	public int getRowIndexOfTables(WebDriver driver, String xpathLocator, String valueName) {
		int rowOrderNumber = 1;
		overrideImplicitTimeout(driver, trivialTimeout);
		if (getElementSize(driver, xpathLocator, valueName) > 0) {
			rowOrderNumber = getElementSize(driver, xpathLocator, valueName) + 1;
		}
		overrideImplicitTimeout(driver, longTimeout);
		return rowOrderNumber;
	}

	/**** Javascript *****/
	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void clickOnElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void clickOnElementByJS(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)));
	}

	protected void clickOnElementByJS(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToPixel(WebDriver driver, int pixel) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0, " + pixel + ")");
	}

	public void scrollToElementBottom(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, xpathLocator));
	}

	public void scrollToElementBottom(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)));
	}

	public void scrollToElementAbove(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
		sleepInSecond(2);
	}

	public void scrollToElementAbove(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)));
		sleepInSecond(2);
	}

	public void scrollToElementAbove(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollToElementBottom(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", element);
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String dynamicXpathLocator, String attributeRemove, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)));
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		return status;
	}

	public boolean isImageLoaded(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)));
		return status;
	}

	public boolean isImageLoaded(WebDriver driver, WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", element);
		return status;
	}

	public void updateElementTextByJS(WebDriver driver, String xpathLocator, String attribute, String text) {
		waitForElementVisible(driver, xpathLocator);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0]." + attribute + "='" + text + "';", getWebElement(driver, xpathLocator));
	}

	public String getElementTextByJS_1(WebDriver driver, String xpathLocator, String attribute) {
		waitForElementVisible(driver, xpathLocator);
		scrollToElementBottom(driver, xpathLocator);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String actualText;
		actualText = (String) jsExecutor.executeScript("return arguments[0]." + attribute + ";", getWebElement(driver, xpathLocator));
		return actualText.trim();
	}

	public String getElementTextByJS_1(WebDriver driver, String dynamicXpathLocator, String attribute, String... dynamicValues) {
		waitForElementVisible(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		scrollToElementBottom(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String actualText = (String) jsExecutor.executeScript("return arguments[0]." + attribute + ";", getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues)));
		return actualText.trim();
	}

	public String getElementTextByJS_2(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String script = "return $(document.evaluate(\"" + xpathLocator + "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).value";
		String actualText = (String) jsExecutor.executeScript(script);
		return actualText;
	}

	public String getElementTextByJS_3(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String script = "return $(document.evaluate(\"" + xpathLocator + "\",document, null, XPathResult.STRING_TYPE, null).stringValue";
		String actualText = (String) jsExecutor.executeScript(script);
		return actualText;
	}

	public String getElementTextByJS_4(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String script = "return $(document.evaluate(\"" + xpathLocator + "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue).val()";
		String actualText = (String) jsExecutor.executeScript(script);
		return actualText;
	}

	/**** Others *****/
	public void hightlightElement(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void hightlightElement(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpathLocator(dynamicXpathLocator, dynamicValues));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	protected void setDisplayElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].style.display = \"block\"", getWebElement(driver, xpathLocator));

	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public boolean ascendingSortedText(WebDriver driver, String xpathLocator) {
		List<String> actualResult = new ArrayList<String>();
		List<String> expectedResult = new ArrayList<String>();
		List<WebElement> elementList = null;
		elementList = getListWebElement(driver, xpathLocator);

		for (WebElement element : elementList) {
			actualResult.add(element.getText().trim());
			expectedResult.add(element.getText().trim());
		}

		Collections.sort(expectedResult, String.CASE_INSENSITIVE_ORDER);
		if (actualResult.equals(expectedResult)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean descendingSortedText(WebDriver driver, String xpathLocator) {
		List<String> actualResult = new ArrayList<String>();
		List<String> expectedResult = new ArrayList<String>();
		List<WebElement> elementList = null;
		elementList = getListWebElement(driver, xpathLocator);

		for (WebElement element : elementList) {
			actualResult.add(element.getText().trim());
			expectedResult.add(element.getText().trim());
		}

		Collections.sort(expectedResult, Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
		if (actualResult.equals(expectedResult)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean ascendingSortedNumber(WebDriver driver, String xpathLocator) {
		List<Double> actualResult = new ArrayList<Double>();
		List<Double> expectedResult = new ArrayList<Double>();
		List<WebElement> elementList = null;
		elementList = getListWebElement(driver, xpathLocator);

		for (WebElement element : elementList) {
			actualResult.add(Double.parseDouble(element.getText().trim().replace("₱", "").replace(",", "").replaceAll("\\s+", "")));
			expectedResult.add(Double.parseDouble(element.getText().trim().replace("₱", "").replace(",", "").replaceAll("\\s+", "")));
		}

		Collections.sort(expectedResult);
		if (actualResult.equals(expectedResult)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean descendingSortedNumber(WebDriver driver, String xpathLocator) {
		List<Double> actualResult = new ArrayList<Double>();
		List<Double> expectedResult = new ArrayList<Double>();
		List<WebElement> elementList = null;
		elementList = getListWebElement(driver, xpathLocator);

		for (WebElement element : elementList) {
			actualResult.add(Double.parseDouble(element.getText().trim().replace("₱", "").replace(",", "").replaceAll("\\s+", "")));
			expectedResult.add(Double.parseDouble(element.getText().trim().replace("₱", "").replace(",", "").replaceAll("\\s+", "")));
		}

		Collections.sort(expectedResult, Collections.reverseOrder());
		if (actualResult.equals(expectedResult)) {
			return true;
		} else {
			return false;
		}
	}

	/*************************** Wait Function *******************************/
	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}

		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public boolean isAjaxLoadingSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecuter = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecuter.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public void waitForElementPresence(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}

	public void waitForElementPresence(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(getDynamicXpathLocator(dynamicXpathLocator, dynamicValues))));
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}

	public void waitForElementVisible(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicXpathLocator(dynamicXpathLocator, dynamicValues))));
	}

	public void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForAllElementsVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}

	protected void waitForAllElementsVisible(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicXpathLocator(dynamicXpathLocator, dynamicValues))));
	}

	protected void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		overrideImplicitTimeout(driver, longTimeout);

	}

	protected void waitForElementInvisible(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicXpathLocator(dynamicXpathLocator, dynamicValues))));
		overrideImplicitTimeout(driver, longTimeout);

	}

	public void waitForAllElementsInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String dynamicXpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicXpathLocator(dynamicXpathLocator, dynamicValues))));
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*************************** Shadow Root Function *******************************/
	public WebElement getShadowRoot(WebDriver driver, String xpathLocator) {
		Shadow shadow = new Shadow(driver);
		return shadow.findElementByXPath(xpathLocator);
	}

	public String getShadowRootElementText(WebDriver driver, String xpathLocator) {
		sleepInSecond(1);
		return getShadowRoot(driver, xpathLocator).getText().trim();
	}

	public void clickOnShadowRootElement(WebDriver driver, String xpathLocator) {
		getShadowRoot(driver, xpathLocator).click();
	}

	/******************* Java Function ************************/
	/***** Read/Write File *****/
	public void writeAppendContentToFile(String value, String fileName) {
		try {
			File myObj = new File(tempFolderPath + fileName);
			FileWriter myWriter = new FileWriter(myObj, true);
			myWriter.write(value + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void writeContentToFile(String value, String fileName) {
		try {
			FileWriter myWriter = new FileWriter(tempFolderPath + fileName);
			myWriter.write(value);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeFileContent(String fileName) {
		try {
			myWriter = new FileWriter(tempFolderPath + fileName);
			myWriter.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Scanner readDataFromFile(String folderPath, String filename) {
		Scanner myReader = null;
		try {
			File myObj = new File(folderPath + filename);
			myReader = new Scanner(myObj, "UTF-8");

		} catch (Exception e) {
			e.getMessage();
		}
		return myReader;
	}

	public void deleteAllFileInTempFolder() {
		String pathFolderDownload = tempFolderPath;
		File file = new File(pathFolderDownload);
		File[] listOfFiles = file.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				new File(listOfFiles[i].toString()).delete();
			}
		}
	}

	/****************** Download File ************************/
	protected boolean isDownloadedFileSuccessfulyByFullFileName(String fileName) {
		boolean result = false;
		downloadFile = new DownloadFile();
		try {
			result = downloadFile.waitForDownloadFileFullnameCompleted(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		downloadFile.deleteFileFullName(fileName);
		return result;
	}

	protected boolean isDownloadedFileSuccessfulyByContainFileName(String fileName) {
		boolean result = false;
		downloadFile = new DownloadFile();
		try {
			result = downloadFile.waitForDownloadFileContainsNameCompleted(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		downloadFile.deleteFileContainName(fileName);
		return result;
	}

	protected void deleteAllFileInDownloadFolder() {
		try {
			downloadFile = new DownloadFile();
			String pathFolderDownload = downloadFile.getPathContainDownload();
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int countFilesInDirectory() {
		downloadFile = new DownloadFile();
		return downloadFile.countFilesInDirectory();

	}

	/** Compare Dictionary **/
	public int compareDictionary(Map<String, String> firstDictionary, Map<String, String> secondDictionary) {
		int chkcount = 0;
		for (Map.Entry<String, String> entry : secondDictionary.entrySet()) {
			String actualAnswer = firstDictionary.get(entry.getKey());
			String givenAnswer = entry.getValue();
			if (givenAnswer != null && actualAnswer != null && givenAnswer.equals(actualAnswer)) {
				chkcount = chkcount + 1;
			}
		}
		return chkcount;
	}

	public int findMaxInArray(List<Integer> array) {
		int max = array.get(0);
		for (Integer integer : array) {
			if (integer > max) {
				max = integer;
			}
		}
		return max;
	}

}
