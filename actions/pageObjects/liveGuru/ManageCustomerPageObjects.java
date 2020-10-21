package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.liveGuru.ManageCustomerPageUI;

public class ManageCustomerPageObjects extends AbstractPages{
	private WebDriver driver;
	
	public ManageCustomerPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToIncomingMessageCloseButton() {
		waitElementClickable(driver, ManageCustomerPageUI.IMCOMING_CLOSE_BUTTON);
		clickToElement(driver, ManageCustomerPageUI.IMCOMING_CLOSE_BUTTON);
	}

	public void inputToCustomerTableTextboxByColumnName(String columnName, String value) {
		waitElementVisible(driver, ManageCustomerPageUI.DYNAMIC_CULUMN_NAME, columnName);
		String columnIndex = String.valueOf(countElementNumber(driver, ManageCustomerPageUI.DYNAMIC_CULUMN_NAME, columnName) + 1);
		waitElementVisible(driver, ManageCustomerPageUI.DYNAMIC_TEXTBOX_BY_INDEX, columnIndex);
		sendKeysToElement(driver, ManageCustomerPageUI.DYNAMIC_TEXTBOX_BY_INDEX, value, columnIndex);
	}

	public void clickButtonByTitleName(String title) {
		waitElementVisible(driver, ManageCustomerPageUI.DYNAMIC_BUTTON_BY_TITLE, title);
		clickToElement(driver, ManageCustomerPageUI.DYNAMIC_BUTTON_BY_TITLE, title);
	}

	public boolean isValueDisplayedAtColumnNameByRowNumber(String columnName, String rowNumber, String value) {
		waitElementVisible(driver, ManageCustomerPageUI.DYNAMIC_CULUMN_NAME, columnName);
		String columnIndex = String.valueOf(countElementNumber(driver, ManageCustomerPageUI.DYNAMIC_CULUMN_NAME, columnName) + 1);
		return isElementDisplayed(driver, ManageCustomerPageUI.DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_NUMBER, rowNumber, columnIndex, value);
	}
}
