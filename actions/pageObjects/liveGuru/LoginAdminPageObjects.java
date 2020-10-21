package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.liveGuru.HomePageUI;
import pageUIs.liveGuru.LoginAdminPageUI;
import pageUIs.liveGuru.LoginUserPageUI;

public class LoginAdminPageObjects extends AbstractPages {

	private WebDriver driver;

	public LoginAdminPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTextbox(String value) {
		waitElementVisible(driver, LoginAdminPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, LoginAdminPageUI.EMAIL_TEXTBOX, value);
	}

	public void inputToPasswordTextbox(String value) {
		waitElementVisible(driver, LoginAdminPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, LoginAdminPageUI.PASSWORD_TEXTBOX, value);		
	}

	public ManageCustomerPageObjects clickToLoginButton() {
		waitElementClickable(driver, LoginAdminPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginAdminPageUI.LOGIN_BUTTON);
		return new ManageCustomerPageObjects(driver);
	}
}
