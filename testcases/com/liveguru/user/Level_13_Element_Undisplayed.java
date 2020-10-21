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
import pageObjects.liveGuru.LoginUserPageObjects;
import pageObjects.liveGuru.MyDashboardPageObjects;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObjects;
import pageUIs.liveGuru.AbstractPageUI;

public class Level_13_Element_Undisplayed extends AbstractTest {
	private WebDriver driver;
	HomePageObjects homePage;
	LoginUserPageObjects loginPage;
	RegisterPageObjects registerPage;
	MyDashboardPageObjects myDashboarbPage;
	AboutUsPageObjects aboutUsPage;
	ContactUsPageObjects contactPage;
	
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		
		
		Assert.assertTrue(homePage.isMyAccountLinkUndisplayed());
		
		homePage.clickToAccounFootertButton();
		
		Assert.assertFalse(homePage.isMyAccountLinkUndisplayed());
		
		Assert.assertTrue(homePage.isSubscribeMsgUndisplayed());
		
		homePage.clickToSubscribeButton();
		
		Assert.assertFalse(homePage.isSubscribeMsgUndisplayed());
		
		loginPage = homePage.clickToMyAccountLink();
		
		Assert.assertTrue(loginPage.isSubscribeMsgUndisplayed());
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
