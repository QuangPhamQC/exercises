package cucumberOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import commons.GlobalConstants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExcelUtil;

public class Hooks {
	private static WebDriver driver;
	private static Scenario globalScenario;
	static ExcelUtil excelUtils;
	public static Properties prop;
	static String excelFilePathResult = GlobalConstants.PROJECT_PATH + GlobalConstants.REGRESSION_TESTSUITE_RESULT;
	static DesiredCapabilities caps = null;

	@Before(order = 2)
	public synchronized static WebDriver openAndQuitBrowser() {
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/environment/" + GlobalConstants.RUN_TESTING_ENVIRONMENT + ".properties");
				prop.load(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String browser = GlobalConstants.WEB_BROWSER;
		if (driver == null) {
			try {
				browser = browser.toLowerCase().trim();
				switch (browser) {
					case "chrome":
						//WebDriverManager.chromedriver().setup();
						System.setProperty("webdriver.chrome.driver", GlobalConstants.PROJECT_PATH + "/libraries/browser/chromedriver.exe");

						ChromeOptions cOptions = new ChromeOptions();
						cOptions.addArguments("--ignore-certificate-errors");
						cOptions.addArguments("--ignore-ssl-errors=yes");
						cOptions.addArguments("--disable-notifications");
						cOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

						Map<String, Object> prefs = new HashMap<String, Object>();
						prefs.put("credentials_enable_service", false);
						prefs.put("profile.password_manager_enabled", false);

						cOptions.setExperimentalOption("prefs", prefs);

						driver = new ChromeDriver(cOptions);
						break;

					case "edge":
						WebDriverManager.edgedriver().setup();
						driver = new EdgeDriver();

					case "hchrome":
						WebDriverManager.chromedriver().browserVersion("78.0.3904.70").setup();
						ChromeOptions chromeOptions = new ChromeOptions();
						chromeOptions.addArguments("headless");
						chromeOptions.addArguments("window-size=1920x1080");
						driver = new ChromeDriver(chromeOptions);
						break;
					case "firefox":
//					WebDriverManager.firefoxdriver().setup();
//					FirefoxOptions fOptions = new FirefoxOptions();
//					fOptions.setLogLevel(FirefoxDriverLogLevel.TRACE);
//					System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
//					System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
						System.setProperty("webdriver.gecko.driver", GlobalConstants.PROJECT_PATH + "/libraries/browserLibrary/geckodriver.exe");
						driver = new FirefoxDriver();
						break;
					case "hfirefox":
						WebDriverManager.firefoxdriver().setup();
						System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
						System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
						FirefoxOptions firefoxOptions = new FirefoxOptions();
						firefoxOptions.setHeadless(true);
						driver = new FirefoxDriver(firefoxOptions);
						break;
					case "chrome_remote":
						caps = DesiredCapabilities.chrome();
						caps.setBrowserName("chrome");
						caps.setPlatform(Platform.WINDOWS);
						caps.setCapability("chrome.arguments", "-screenwidth 1366 -screenheight 768");
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--ignore-certificate-errors");
						options.addArguments("--ignore-ssl-errors=yes");
						options.addArguments("--disable-notifications");
						options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

//					Map<String, Object> prefs = new HashMap<String, Object>();
//					prefs.put("credentials_enable_service", false);
//					prefs.put("profile.password_manager_enabled", false);

//					cOptions.setExperimentalOption("prefs", prefs);

						options.merge(caps);

						try {
							driver = new RemoteWebDriver(new URL("http://10.20.36.122:4444/wd/hub"), caps);
							((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						break;
					case "firefox_remote":
						caps = DesiredCapabilities.chrome();
						caps.setBrowserName("firefox");
						caps.setPlatform(Platform.WINDOWS);

						FirefoxOptions foptions = new FirefoxOptions();
						foptions.merge(caps);
						try {
							driver = new RemoteWebDriver(new URL("http://10.20.36.122:4444/wd/hub"), caps);
							((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						break;
					default:
						WebDriverManager.chromedriver().setup();
						ChromeOptions defaultChromeOptions = new ChromeOptions();
						defaultChromeOptions.addArguments("--ignore-certificate-errors");
						defaultChromeOptions.addArguments("--ignore-ssl-errors=yes");
						driver = new ChromeDriver(defaultChromeOptions);
						break;
				}
			} finally {
				Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
			}
			driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
			if (browser.equals("chrome_remote")) {
				driver.manage().window().setSize(new Dimension(1382, 768));
			} else {
				driver.manage().window().maximize();
			}
		}
		return driver;
	}

	public static void close() {
		try {
			if (driver != null) {
				openAndQuitBrowser().quit();
				driver = null;
			}
		} catch (UnreachableBrowserException e) {
			System.out.println("Can not close the browser");
		}
	}

	private static class BrowserCleanup implements Runnable {
		@Override
		public void run() {
			close();
		}
	}

	public static Scenario getGlobalScenario() {
		return globalScenario;
	}

	@Before(order = 1)
	public static void setGlobalScenario(Scenario scenario) {
		Hooks.globalScenario = scenario;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	@After(order = 1)
	public void tearDown() {
		takeScreenShot();
	}

	@After(value = "@Quit_Browser", order = 0)
	public void afterFeature() {
		close();
	}

	public static void setResultInExcelFile() {
		if (excelUtils == null) {
			excelUtils = new ExcelUtil();
		}

		ArrayList<String> caseIdList = new ArrayList<>();
		String feature = null;
		for (String tag : getGlobalScenario().getSourceTagNames()) {
			if (tag.contains("TC")) {
				String[] res = tag.split("(\\(.*?)");
				caseIdList.add(res[1].substring(0, res[1].length() - 1));
			} else if (tag.contains("SheetName")) {
				String[] res = tag.split("(\\(.*?)");
				feature = res[1].substring(0, res[1].length() - 1);
			}
		}

		if (feature != null) {
			try {
				excelUtils.setExcelFile(excelFilePathResult, feature);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (caseIdList.size() > 0) {
			for (String caseId : caseIdList) {
				for (int i = 8; i <= excelUtils.getRowCountInSheet(); i++) {
					if (getGlobalScenario().isFailed() && excelUtils.getCellData(i, 1).equals(caseId)) {
						try {
							excelUtils.setCellValue(i, 11, "Fail", excelFilePathResult);
						} catch (IOException e) {
							e.printStackTrace();
						}

					} else if (!getGlobalScenario().isFailed() && excelUtils.getCellData(i, 1).equals(caseId)) {
						try {
							excelUtils.setCellValue(i, 11, "Pass", excelFilePathResult);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}

				}
			}
		}
	}

	public static void takeScreenShot() {
		try {
			String screenshotName = getGlobalScenario().getName().replaceAll(" ", "_");
			getGlobalScenario().log("Add image into report successfully");
			TakesScreenshot ts = (TakesScreenshot) getDriver();
			byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
			getGlobalScenario().attach(screenshot, "image/png", screenshotName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
