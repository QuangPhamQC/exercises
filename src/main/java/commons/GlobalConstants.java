package commons;

import java.io.File;

public class GlobalConstants {
	/** Dynamic Value **/
	/** BUILD_PROJECT_ENVIRONMENT: BROWSERSTACK or LOCAL **/
	

	/** TESTING_ENVIRONMENT: DEV or TEST **/
	public static final String RUN_TESTING_ENVIRONMENT = "DEV";
//	public static final String RUN_TESTING_ENVIRONMENT = System.getProperty("ENVIRONMENT");

	/** Web Browser: CHROME or FIREFOX **/
	public static final String WEB_BROWSER = "Chrome";
//	public static final String WEB_BROWSER = System.getProperty("BROWSER");

	/** Static Value **/
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");

	public static final String LOGIN_PAGE_URL = "";
	public static final String REGISTER_PAGE_URL = "";
	
	public static final String YOPMMAIL_URL = "https://yopmail.com/";

	public static final String EMAIL = "";
	public static final String PASSWORD = "";

	public static final long LONG_TIMEOUT = 20;
	public static final long SHORT_TIMEOUT = 5;
	public static final long TRIVIAL_TIMEOUT = 2;

	public static final String FILE_DATA_PATH = GlobalConstants.PROJECT_PATH + "/src/test/resources/testdata/";
	public static final String JSON_FILE_DATA_PATH = GlobalConstants.PROJECT_PATH + "/src/test/resources/dataJson/";
	public static final String REGRESSION_TESTSUITE_RESULT = File.separator + "testsuite" + File.separator + "Regression_Test_Suite_AIDF_Result.xlsx";

	/** API **/
	public static final String DEV_HOST_URL = "";
	public static final String TEST_HOST_URL = "";
	
}
