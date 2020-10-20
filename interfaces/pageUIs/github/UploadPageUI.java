package pageUIs.github;

public class UploadPageUI {
	public static final String DYNAMIC_IMAGE_TEXT = "//p[text()='%s']";
	public static final String START_BUTTON = DYNAMIC_IMAGE_TEXT + "/parent::td/following-sibling::td/button[contains(@class,'start')]";	
	public static final String DYNAMIC_IMAGE_LINK = "//a[@title='%s']";
}
