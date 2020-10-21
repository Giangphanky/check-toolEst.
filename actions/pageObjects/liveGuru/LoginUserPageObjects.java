package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.liveGuru.HomePageUI;
import pageUIs.liveGuru.LoginUserPageUI;

public class LoginUserPageObjects extends AbstractPages {

	private WebDriver driver;

	public LoginUserPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmail(String string) {
		waitElementVisible(driver, LoginUserPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginUserPageUI.EMAIL_TEXTBOX, string);
	}

	public void clickToLoginButton() {
		waitElementClickable(driver, LoginUserPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginUserPageUI.LOGIN_BUTTON);
	}

	public MyDashboardPageObjects clickToLoginButtonLoginSuccess() {
		waitElementClickable(driver, LoginUserPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginUserPageUI.LOGIN_BUTTON);
		return new MyDashboardPageObjects(driver);
	}

	public void inputToPassword(String string) {
		waitElementVisible(driver, LoginUserPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginUserPageUI.PASSWORD_TEXTBOX, string);
	}

	public String getInValidErrMessageAtEmailOrPassword() {
		waitElementVisible(driver, LoginUserPageUI.VALIDATE_ERROR_MSG_EMAIL_OR_PASSWORD_TEXTBOX);
		return getElementText(driver, LoginUserPageUI.VALIDATE_ERROR_MSG_EMAIL_OR_PASSWORD_TEXTBOX);
	}

	public String getInValidErrMessageAtPasswordTextbox() {
		waitElementVisible(driver, LoginUserPageUI.VALIDATE_INVALID_ERROR_MSG_PASSWORD_TEXTBOX);
		return getElementText(driver, LoginUserPageUI.VALIDATE_INVALID_ERROR_MSG_PASSWORD_TEXTBOX);
	}

	public String getInvalidErrMessageAtEmailTextbox() {
		waitElementVisible(driver, LoginUserPageUI.VALIDATE_INVALID_ERROR_MSG_EMAIL_TEXTBOX);
		return getElementText(driver, LoginUserPageUI.VALIDATE_INVALID_ERROR_MSG_EMAIL_TEXTBOX);
	}

	public String getErrMessageAtPasswordTextbox() {
		waitElementVisible(driver, LoginUserPageUI.REQUIRED_ERROR_MSG_PASSWORD_TEXTBOX);
		return getElementText(driver, LoginUserPageUI.REQUIRED_ERROR_MSG_PASSWORD_TEXTBOX);
	}

	public String getErrMessageAtEmailTextbox() {
		waitElementVisible(driver, LoginUserPageUI.REQUIRED_ERROR_MSG_EMAIL_TEXTBOX);
		return getElementText(driver, LoginUserPageUI.REQUIRED_ERROR_MSG_EMAIL_TEXTBOX);
	}

	public boolean isSubscribeMsgUndisplayed() {
		return isElementUndisplayed(driver, HomePageUI.REQUIRED_SUBSCIBE_MSG);
	}

}
