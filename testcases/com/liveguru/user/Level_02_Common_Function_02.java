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

public class Level_02_Common_Function_02 extends AbstractPages{
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", "./browserDriver/geckodriver72");
		driver = new FirefoxDriver();
		
		
		setImplicitWait(driver, 30);
		openPageUrl(driver, "http://live.demoguru99.com");
	}

	@BeforeMethod
	public void beforeMethod() {
		clickToElement(driver, "//div[@class='footer']//a[text()='My Account']");
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		sendKeysToElement(driver, "//input[@id='email']", "");
		sendKeysToElement(driver, "//input[@id='pass']", "");
		clickToElement(driver, "//button[@id='send2']");
	
		assertEquals(getElementText(driver, "//div[@id='advice-required-entry-email']"), "This is a required field.");
		assertEquals(getElementText(driver, "//div[@id='advice-required-entry-pass']"), "This is a required field.");
		sleepInSeconds(5);
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		sendKeysToElement(driver, "//input[@id='email']", "123@456.789");
		sendKeysToElement(driver, "//input[@id='pass']", "123456");
		clickToElement(driver, "//button[@id='send2']");
		
		assertEquals(getElementText(driver, "//div[@id='advice-validate-email-email']"), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test(description = "Email not exist in application")
	public void TC_03_LoginWithIncorrectEmail() {
		sendKeysToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		sendKeysToElement(driver, "//input[@id='pass']", "123456");
		clickToElement(driver, "//button[@id='send2']");
		
		assertEquals(getElementText(driver, "//li[@class='error-msg']//span"), "Invalid login or password.");
	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_LoginWithInvalidPassword() {
		sendKeysToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		sendKeysToElement(driver, "//input[@id='pass']", "123");
		clickToElement(driver, "//button[@id='send2']");

		assertEquals(getElementText(driver, "//div[@id='advice-validate-password-pass']"), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		sendKeysToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		sendKeysToElement(driver, "//input[@id='pass']", randomNumber() + "");
		clickToElement(driver, "//button[@id='send2']");

		assertEquals(getElementText(driver, "//li[@class='error-msg']//span"), "Invalid login or password.");
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		sendKeysToElement(driver, "//input[@id='email']", "automationfc.vn@gmail.com");
		sendKeysToElement(driver, "//input[@id='pass']", "123123");
		clickToElement(driver, "//button[@id='send2']");

		assertTrue(isElementDisplayed(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(.,'Automation FC')]"));
		assertTrue(isElementDisplayed(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(.,'automationfc.vn@gmail.com')]"));
		
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
