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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractPages;
import commons.AbstractTest;
import pageObjects.liveGuru.AboutUsPageObjects;
import pageObjects.liveGuru.ContactUsPageObjects;
import pageObjects.liveGuru.HomePageObjects;
import pageObjects.liveGuru.LoginUserPageObjects;
import pageObjects.liveGuru.MyDashboardPageObjects;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObjects;
import pageUIs.liveGuru.AbstractPageUI;

public class Level_14_Soft_Assert extends AbstractTest {
	private WebDriver driver;
	HomePageObjects homePage;
	LoginUserPageObjects loginPage;
	RegisterPageObjects registerPage;
	MyDashboardPageObjects myDashboarbPage;
	AboutUsPageObjects aboutUsPage;
	ContactUsPageObjects contactPage;

	@Parameters({ "browser", "url" })
	@BeforeMethod
	public void beforeMethod(String browserName, String url) {

		driver = getBrowserDriver(browserName, url);

		// Get url -> Mở ra trang home
		homePage = new HomePageObjects(driver);
		// Click vào link My Account -> Mở ra trang Login
		loginPage = homePage.clickToMyAccountLink();
	}


	@Test
	public void TC_01_Assert() {

		//Assert: failed
		Assert.assertTrue(false);
		
		// Nhập dữ liệu vào field Email
		loginPage.inputToEmail("");

		// Nhập dữ liệu vào field Password
		loginPage.inputToPassword("");

		// Click Login button
		loginPage.clickToLoginButton();

		// Verify dữ liệu nhập vào

		assertEquals(loginPage.getErrMessageAtEmailTextbox(), "This is a required field. ");//Failed
		assertEquals(loginPage.getErrMessageAtPasswordTextbox(), "This is a required field. ");//Failed
	}

	@Test
	public void TC_02_Assert() {
		//Verify: failed but not stop
		verifyFalse(true);
		
		// Nhập dữ liệu vào field Email
		loginPage.inputToEmail("");

		// Nhập dữ liệu vào field Password
		loginPage.inputToPassword("");

		// Click Login button
		loginPage.clickToLoginButton();

		// Verify dữ liệu nhập vào

		verifyEquals(loginPage.getErrMessageAtEmailTextbox(), "This is a required field. ");//Failed
		verifyEquals(loginPage.getErrMessageAtPasswordTextbox(), "This is a required field.");//Failed
		
		
	}

	@AfterMethod
	public void afterMethod() {
		removeDriver();
	}
}
