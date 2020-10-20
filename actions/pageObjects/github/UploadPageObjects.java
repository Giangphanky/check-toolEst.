package pageObjects.github;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.github.UploadPageUI;

public class UploadPageObjects extends AbstractPages{

	private WebDriver driver;
	
	public UploadPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToStartButton( String imgPath) {
		waitElementClickable(driver, UploadPageUI.START_BUTTON ,imgPath);
		clickToElement(driver, UploadPageUI.START_BUTTON ,imgPath);
	}

	public boolean isFileLoaded(String imgPath) {
		waitElementVisible(driver, UploadPageUI.DYNAMIC_IMAGE_TEXT ,imgPath);
		return isElementDisplayed(driver, UploadPageUI.DYNAMIC_IMAGE_TEXT, imgPath);
	}

	public boolean isFileLoadSuccess( String imgPath) {
		waitElementVisible(driver, UploadPageUI.DYNAMIC_IMAGE_LINK ,imgPath);
		return isElementDisplayed(driver, UploadPageUI.DYNAMIC_IMAGE_LINK, imgPath);
	}

}
