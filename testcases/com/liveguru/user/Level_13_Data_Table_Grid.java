package com.liveguru.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractPages;
import commons.AbstractTest;
import pageObjects.liveGuru.AboutUsPageObjects;
import pageObjects.liveGuru.ContactUsPageObjects;
import pageObjects.liveGuru.HomePageObjects;
import pageObjects.liveGuru.LoginAdminPageObjects;
import pageObjects.liveGuru.LoginUserPageObjects;
import pageObjects.liveGuru.ManageCustomerPageObjects;
import pageObjects.liveGuru.ManageCustomerPageObjects;
import pageObjects.liveGuru.MyDashboardPageObjects;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObjects;
import pageUIs.liveGuru.AbstractPageUI;

public class Level_13_Data_Table_Grid extends AbstractTest {
	private WebDriver driver;
	LoginAdminPageObjects loginAdminPage;
	ManageCustomerPageObjects manageCustomerPage;
	
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		loginAdminPage = PageGeneratorManager.getLoginAdminPage(driver);
		loginAdminPage.inputToEmailTextbox("user01");
		loginAdminPage.inputToPasswordTextbox("guru99com");
	
		manageCustomerPage = loginAdminPage.clickToLoginButton();
		manageCustomerPage.clickToIncomingMessageCloseButton();
	}
	
	@Test
	public void TC_01_Search_Customer_Data() {
		manageCustomerPage.inputToCustomerTableTextboxByColumnName("Name","Automation FC");
		manageCustomerPage.inputToCustomerTableTextboxByColumnName("Email","automationfc.vn@gmail.com ");
		manageCustomerPage.inputToCustomerTableTextboxByColumnName("Telephone","0123654789");
		manageCustomerPage.inputToCustomerTableTextboxByColumnName("ZIP","550000");
		manageCustomerPage.clickButtonByTitleName("Search");
		Assert.assertTrue(manageCustomerPage.isValueDisplayedAtColumnNameByRowNumber("Name", "1", "Automation FC" ));
	}
	
	@AfterClass
	public void afterClass() {
		removeDriver();
	}

}
