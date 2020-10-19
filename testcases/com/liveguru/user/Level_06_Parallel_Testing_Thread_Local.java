package com.liveguru.user;

import static org.testng.Assert.assertEquals;

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
import pageObjects.liveGuru.LoginPageObjects;
import pageObjects.liveGuru.MyDashboardPageObjects;
import pageObjects.liveGuru.RegisterPageObjects;

public class Level_06_Parallel_Testing_Thread_Local extends AbstractTest {
	WebDriver driver;
	HomePageObjects homePage;
	LoginPageObjects loginPage;
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
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

}
