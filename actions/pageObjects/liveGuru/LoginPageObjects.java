package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.liveGuru.LoginPageUI;

public class LoginPageObjects extends AbstractPages{
	
	private WebDriver driver;
	
	public LoginPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToEmail(String string) {
		waitElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.EMAIL_TEXTBOX, string);
	}

	public void clickToLoginButton() {
		waitElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON); 
	}

	public void inputToPassword(String string) {
		waitElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, string);
	}

	public String getInValidErrMessageAtEmailOrPassword() {
		waitElementVisible(driver, LoginPageUI.VALIDATE_ERROR_MSG_EMAIL_OR_PASSWORD_TEXTBOX);
		return getElementText(driver, LoginPageUI.VALIDATE_ERROR_MSG_EMAIL_OR_PASSWORD_TEXTBOX);
	}

	public String getInValidErrMessageAtPasswordTextbox() {
		waitElementVisible(driver, LoginPageUI.VALIDATE_INVALID_ERROR_MSG_PASSWORD_TEXTBOX);
		return getElementText(driver, LoginPageUI.VALIDATE_INVALID_ERROR_MSG_PASSWORD_TEXTBOX);
	}

	public String getInvalidErrMessageAtEmailTextbox() {
		waitElementVisible(driver, LoginPageUI.VALIDATE_INVALID_ERROR_MSG_EMAIL_TEXTBOX);
		return getElementText(driver, LoginPageUI.VALIDATE_INVALID_ERROR_MSG_EMAIL_TEXTBOX);
	}

	public String getErrMessageAtPasswordTextbox() {
		waitElementVisible(driver, LoginPageUI.REQUIRED_ERROR_MSG_PASSWORD_TEXTBOX);
		return getElementText(driver, LoginPageUI.REQUIRED_ERROR_MSG_PASSWORD_TEXTBOX);
	}

	public String getErrMessageAtEmailTextbox() {
		waitElementVisible(driver, LoginPageUI.REQUIRED_ERROR_MSG_EMAIL_TEXTBOX);
		return getElementText(driver, LoginPageUI.REQUIRED_ERROR_MSG_EMAIL_TEXTBOX);
	}

}
