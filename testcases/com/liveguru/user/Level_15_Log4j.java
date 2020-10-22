package com.liveguru.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractPages;
import commons.AbstractTest;
import pageObjects.liveGuru.HomePageObjects;
import pageObjects.liveGuru.LoginUserPageObjects;
import pageObjects.liveGuru.MyDashboardPageObjects;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObjects;

public class Level_15_Log4j extends AbstractTest {
	private WebDriver driver;
	HomePageObjects homePage;
	LoginUserPageObjects loginPage;
	RegisterPageObjects registerPage;
	MyDashboardPageObjects myDashboarbPage;
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		log.info("Pre-Conditon Step 01: Open");
		driver = getBrowserDriver(browserName, url);
		
		log.info("Pre-Condition Step 02: Open 'Home Page'");
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		log.info("Pre-Condition Step 01: Click to 'My Account' Link");
		loginPage = homePage.clickToMyAccountLink();
	}
	
	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {

		log.info("Login - Step 01: Input to Email");
		loginPage.inputToEmail("");

		log.info("Login - Step 02: Input to Password");
		loginPage.inputToPassword("");

		log.info("Login - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		log.info("Login - Step 04: Verify email required message");
		assertEquals(loginPage.getErrMessageAtEmailTextbox(), "This is a required field.");
		
		log.info("Login - Step 05: Verify password required message");
		assertEquals(loginPage.getErrMessageAtPasswordTextbox(), "This is a required field.");
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		log.info("Login - Step 01: Input to Email");
		loginPage.inputToEmail("automationfc.vn@gmail.com");

		log.info("Login - Step 02: Input to Password");
		loginPage.inputToPassword("123123");

		log.info("Login - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();
		
		log.info("Login - Step 04: Open 'Dashboard' Page");
		myDashboarbPage = new MyDashboardPageObjects(driver);

		log.info("Dashboard - Step 05: Verify Name Displayed");
		assertTrue(myDashboarbPage.checkNameDisplayed());
		
		log.info("Dashboard - Step 06: Verify Email Displayed");
		assertTrue(myDashboarbPage.checkEmailDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		removeDriver();
	}

	private int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

}
