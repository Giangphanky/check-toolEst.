package pageObjects.github;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static UploadPageObjects getUploadPageObject(WebDriver driver) {
		return new UploadPageObjects(driver);
	}

}
