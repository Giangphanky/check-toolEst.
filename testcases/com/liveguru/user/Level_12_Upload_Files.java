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
import pageObjects.github.PageGeneratorManager;
import pageObjects.github.UploadPageObjects;
import pageObjects.liveGuru.AboutUsPageObjects;
import pageObjects.liveGuru.ContactUsPageObjects;
import pageObjects.liveGuru.HomePageObjects;
import pageObjects.liveGuru.LoginUserPageObjects;
import pageObjects.liveGuru.MyDashboardPageObjects;
import pageObjects.liveGuru.RegisterPageObjects;

import pageUIs.liveGuru.AbstractPageUI;

public class Level_12_Upload_Files extends AbstractTest {
	private WebDriver driver;
	UploadPageObjects uploadPage;
	String imgPath1 = "hinhanh1.png";
	String imgPath2 = "hinhanh2.png";
	String imgPath3 = "hinhanh3.png";

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String url) {
		driver = getBrowserDriver(browserName, url);
		uploadPage = PageGeneratorManager.getUploadPageObject(driver);
	}

	
	public void TC_01_Upload_One_File_Per_Time() {
		uploadPage.upLoadMultipleFiles(driver, imgPath1);
		uploadPage.clickToStartButton(imgPath1);
		Assert.assertTrue(uploadPage.isFileLoaded(imgPath1));
		Assert.assertTrue(uploadPage.isFileLoadSuccess(imgPath1));

		uploadPage.upLoadMultipleFiles(driver,imgPath2);
		uploadPage.clickToStartButton(imgPath2);
		Assert.assertTrue(uploadPage.isFileLoaded(imgPath2));
		Assert.assertTrue(uploadPage.isFileLoadSuccess(imgPath2));

		uploadPage.upLoadMultipleFiles(driver,imgPath3);
		uploadPage.clickToStartButton(imgPath3);
		Assert.assertTrue(uploadPage.isFileLoaded(imgPath3));
		Assert.assertTrue(uploadPage.isFileLoadSuccess(imgPath3));
	}

	@Test
	public void TC_02_Upload_Multiple_Files_Per_Time() {
		
		uploadPage.upLoadMultipleFiles(driver, imgPath1, imgPath2);
		uploadPage.clickToStartButton(imgPath1);
		Assert.assertTrue(uploadPage.isFileLoaded(imgPath1));
		Assert.assertTrue(uploadPage.isFileLoadSuccess(imgPath1));
		uploadPage.clickToStartButton(imgPath2);
		Assert.assertTrue(uploadPage.isFileLoaded(imgPath2));
		Assert.assertTrue(uploadPage.isFileLoadSuccess(imgPath2));
		
	}

	@AfterClass
	public void afterClass() {
		removeDriver();
	}

}
