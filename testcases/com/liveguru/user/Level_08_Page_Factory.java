package com.liveguru.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ISelect;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.AbstractPage2;
import commons.AbstractPages;
import pageObjects.liveGuru.HomePageObjects;
import pageObjects.liveGuru.LoginPageObjects;
import pageObjects.liveGuru.MyDashboardPageObjects;
import pageObjects.liveGuru.RegisterPageObjects;
import pageUIs.liveGuru.MyDashboardPageUI;

public class Level_08_Page_Factory extends AbstractPages {
	WebDriver driver;
	HomePageObjects homePage;
	LoginPageObjects loginPage;
	RegisterPageObjects registerPage;
	MyDashboardPageObjects myDashboarbPage;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", "./browserDriver/geckodriver");
		driver = new FirefoxDriver();

		driver.get("http://live.demoguru99.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Get url -> Mở ra trang home
		homePage = new HomePageObjects(driver);

	}

	@BeforeMethod
	public void beforeMethod() {
		// Click vào link My Account -> Mở ra trang Login
		homePage.clickToMyAccountLink();
		loginPage = new LoginPageObjects(driver);
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
	public void TC_02_LoginWithInvalidEmail() {
		// Nhập dữ liệu vào field Email
		loginPage.inputToEmail("123@456.789");

		// Nhập dữ liệu vào field Password
		loginPage.inputToPassword("123456");

		// Click Login button
		loginPage.clickToLoginButton();

		// Hàm verify dữ liệu
		assertEquals(loginPage.getInvalidErrMessageAtEmailTextbox(),
				"Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test(description = "Email not exist in application")
	public void TC_03_LoginWithIncorrectEmail() {
		// Nhập dữ liệu vào field Email
		loginPage.inputToEmail("auto_test" + randomNumber() + "@live.com");

		// Nhập dữ liệu vào field Password
		loginPage.inputToPassword("123456");

		// Click Login button
		loginPage.clickToLoginButton();

		// Hàm Verify dữ liệu
		assertEquals(loginPage.getInValidErrMessageAtEmailOrPassword(), "Invalid login or password.");

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_LoginWithInvalidPassword() {
		// Nhập dữ liệu vào field Email
		loginPage.inputToEmail("auto_test" + randomNumber() + "@live.com");

		// Nhập dữ liệu vào field Password
		loginPage.inputToPassword("123");

		// Click Login button
		loginPage.clickToLoginButton();

		// Hàm verify dữ liệu
		assertEquals(loginPage.getInValidErrMessageAtPasswordTextbox(),
				"Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		// Nhập dữ liệu vào field Email
		loginPage.inputToEmail("auto_test" + randomNumber() + "@live.com");

		// Nhập dữ liệu vào field Password
		loginPage.inputToPassword(randomNumber() + "");

		// Click Login button
		loginPage.clickToLoginButton();

		// Hàm Verify dữ liệu
		assertEquals(loginPage.getInValidErrMessageAtEmailOrPassword(), "Invalid login or password.");

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
		driver.quit();
	}

	private int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

}
