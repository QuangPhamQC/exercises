package interfaces;

public class MyAccountUI {
    public static final String MY_ACCOUNT_LINK = "//a[@class = 'ico-account']";
    public static final String LOG_IN_OUT_LINK = "//a[@class = 'ico-%s']";
    public static final String LIST_ITEM_LINK = "//div//li[contains(@class, '%s')]/a";
    public static final String GENDER_RADIO_BUTTON = "//input[@value = '%s']";
    public static final String BUTTON_LINK = "//button[contains(., '%s')]";
    public static final String SUCCESSFUL_MESSAGE = "//p[@class = 'content']";
    public static final String CLOSE_SUCCESSFUL_MESSAGE = "//span[@class = 'close']";
    public static final String PARENT_DROPDOWN_LIST = "//select[contains(@name, '%s')]";
    public static final String CHILD_DROPDOWN_LIST = PARENT_DROPDOWN_LIST + "/option";
    public static final String ADDRESSES_INFO_LABEL = "//li[@class = '%s']";
    public static final String ADDRESSES_TITLE_LABEL = "//li[@class = '%s']/label";
    public static final String HEADER_TAB_LINK = "//ul[contains(@class, 'not')]/li/a[text() = '%s ']";
    public static final String PRODUCT_LINK = "//div/a[contains(@title, '%s')]";
    public static final String ADD_REVIEW_LINK = "//a[contains(text(), '%s')]";
    public static final String REVIEW_TITLE_TEXTBOX = "//input[@class = '%s']";
    public static final String REVIEW_TEXT_TEXTBOX = "//textarea[@class = '%s']";
    public static final String RATING_RADIO_BUTTON = "//input[@id = 'addproductrating_%s']";
    public static final String SUCCESSFULL_MESSAGE_REVIEW = "//div[@class='result']";
}
