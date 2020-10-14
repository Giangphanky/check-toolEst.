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

public class Level_02_Common_Function_01 {
	WebDriver driver;
	AbstractPage2 abstractPage;

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", "./browserDriver/geckodriver72");
		driver = new FirefoxDriver();
		
		abstractPage = new AbstractPage2();
		abstractPage.setImplicitWait(driver, 30);
		abstractPage.openPageUrl(driver, "http://live.demoguru99.com");
	}

	@BeforeMethod
	public void beforeMethod() {
		abstractPage.clickToElement(driver, "//div[@class='footer']//a[text()='My Account']");
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		abstractPage.sendKeysToElement(driver, "//input[@id='email']", "");
		abstractPage.sendKeysToElement(driver, "//input[@id='pass']", "");
		abstractPage.clickToElement(driver, "//button[@id='send2']");
	
		assertEquals(abstractPage.getElementText(driver, "//div[@id='advice-required-entry-email']"), "This is a required field.");
		assertEquals(abstractPage.getElementText(driver, "//div[@id='advice-required-entry-pass']"), "This is a required field.");
		abstractPage.sleepInSeconds(5);
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		abstractPage.sendKeysToElement(driver, "//input[@id='email']", "123@456.789");
		abstractPage.sendKeysToElement(driver, "//input[@id='pass']", "123456");
		abstractPage.clickToElement(driver, "//button[@id='send2']");
		
		assertEquals(abstractPage.getElementText(driver, "//div[@id='advice-validate-email-email']"), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test(description = "Email not exist in application")
	public void TC_03_LoginWithIncorrectEmail() {
		abstractPage.sendKeysToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		abstractPage.sendKeysToElement(driver, "//input[@id='pass']", "123456");
		abstractPage.clickToElement(driver, "//button[@id='send2']");
		
		assertEquals(abstractPage.getElementText(driver, "//li[@class='error-msg']//span"), "Invalid login or password.");
	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_LoginWithInvalidPassword() {
		abstractPage.sendKeysToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		abstractPage.sendKeysToElement(driver, "//input[@id='pass']", "123");
		abstractPage.clickToElement(driver, "//button[@id='send2']");

		assertEquals(abstractPage.getElementText(driver, "//div[@id='advice-validate-password-pass']"), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		abstractPage.sendKeysToElement(driver, "//input[@id='email']", "auto_test" + randomNumber() + "@live.com");
		abstractPage.sendKeysToElement(driver, "//input[@id='pass']", randomNumber() + "");
		abstractPage.clickToElement(driver, "//button[@id='send2']");

		assertEquals(abstractPage.getElementText(driver, "//li[@class='error-msg']//span"), "Invalid login or password.");
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		abstractPage.sendKeysToElement(driver, "//input[@id='email']", "automationfc.vn@gmail.com");
		abstractPage.sendKeysToElement(driver, "//input[@id='pass']", "123123");
		abstractPage.clickToElement(driver, "//button[@id='send2']");

		assertTrue(abstractPage.isElementDisplayed(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(.,'Automation FC')]"));
		assertTrue(abstractPage.isElementDisplayed(driver, "//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p[contains(.,'automationfc.vn@gmail.com')]"));
		
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
