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
import pageObjects.liveGuru.RegisterPageObjects;

public class Level_09_Page_Generator extends AbstractTest {
	private WebDriver driver;
	HomePageObjects homePage;
	LoginUserPageObjects loginPage;
	RegisterPageObjects registerPage;
	MyDashboardPageObjects myDashboarbPage;
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		
		driver = getBrowserDriver(browserName, url);
		
		// Get url -> Mở ra trang home	
		homePage = new HomePageObjects(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		// Click vào link My Account -> Mở ra trang Login
		loginPage = homePage.clickToMyAccountLink();
	}
	
	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {

		// Nhập dữ liệu vào field Email
		loginPage.inputToEmail("");

		// Nhập dữ liệu vào field Password
		loginPage.inputToPassword("");

		// Click Login button
		loginPage.clickToLoginButton();

		// Verify dữ liệu nhập vào

		assertEquals(loginPage.getErrMessageAtEmailTextbox(), "This is a required field.");
		assertEquals(loginPage.getErrMessageAtPasswordTextbox(), "This is a required field.");
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		// Nhập dữ liệu vào field Email
		loginPage.inputToEmail("automationfc.vn@gmail.com");

		// Nhập dữ liệu vào field Password
		loginPage.inputToPassword("123123");

		// Click Login button -> Chuyển qua trang My Dashboard
		loginPage.clickToLoginButton();
		myDashboarbPage = new MyDashboardPageObjects(driver);

		// Hàm verify dữ liệu
		assertTrue(myDashboarbPage.checkNameDisplayed());
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
