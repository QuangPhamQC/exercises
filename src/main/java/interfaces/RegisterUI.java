package interfaces;

public class RegisterUI {
    public static final String GENDER_RADIO_BUTTON = "//input[@id='gender-" + "%s']";
    public static final String PARENT_DROPDOWN_LIST = "//select[@name='DateOfBirth" + "%s']";
    public static final String CHILD_DROPDOWN_LIST = PARENT_DROPDOWN_LIST + "/option";
    public static final String CHECKBOX_ITEM = "//input[@id ='%s']";
    public static final String SUCCESSFULLY_MESSAGE = "//div[@class='result']";
    public static final String ERROR_MESSAGE_EXIST_EMAIL = "//div[contains(@class, 'message-error')]/ul/li";
    public static final String ERROR_MESSAGE_PASSWORD = "//span[@id = 'Password-error']";
    public static final String ERROR_MESSAGE_CONFIRM_PASSWORD = "//span[@id = 'ConfirmPassword-error']";
}   
