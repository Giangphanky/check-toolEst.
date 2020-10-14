package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.liveGuru.MyDashboardPageUI;

public class MyDashboardPageObjects extends AbstractPages{

	private WebDriver driver;
	
	public MyDashboardPageObjects(WebDriver driver) {
		this.driver = driver;
	}
	public boolean checkNameDisplayed() {
		return isElementDisplayed(driver, MyDashboardPageUI.NAME_TEXT);
	}

	public boolean checkEmailDisplayed() {
		return isElementDisplayed(driver, MyDashboardPageUI.EMAIL_TEXT);
	}

}
