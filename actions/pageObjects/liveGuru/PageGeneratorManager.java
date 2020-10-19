package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static HomePageObjects getHomePage(WebDriver driver) {
		return new HomePageObjects(driver);
	}
	public static LoginPageObjects getLoginPage(WebDriver driver) {
		return new LoginPageObjects(driver);
	}
	public static RegisterPageObjects getRegisterPage(WebDriver driver) {
		return new RegisterPageObjects(driver);
	}
	public static MyDashboardPageObjects getMyDashboardPage(WebDriver driver) {
		return new MyDashboardPageObjects(driver);
	}
	public static AboutUsPageObjects getAboutUsPage(WebDriver driver) {
		return new AboutUsPageObjects(driver);
	}
	public static ContactUsPageObjects getContactUsPage(WebDriver driver) {
		return new ContactUsPageObjects(driver);
	}

}
