package pageUIs.liveGuru;

public class ManageCustomerPageUI {
	public static final String IMCOMING_CLOSE_BUTTON = "//div[@class='message-popup-head']/a[@title='close']";
	public static final String DYNAMIC_CULUMN_NAME = "//tr[@class='headings']//span[text()='%s']//ancestor::th/preceding-sibling::th";
	public static final String DYNAMIC_TEXTBOX_BY_INDEX = "//tr[@class='filter']//th[%s]//input";
	public static final String DYNAMIC_BUTTON_BY_TITLE= "//td[@class='filter-actions a-right']//span[text()='%s']";
	public static final String DYNAMIC_VALUE_AT_COLUMN_NAME_AND_ROW_NUMBER= "//tr[%s]//td[%s][contains(text(), '%s')]";
}

