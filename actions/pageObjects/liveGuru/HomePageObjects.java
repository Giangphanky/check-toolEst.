package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.liveGuru.HomePageUI;
import pageUIs.liveGuru.LoginUserPageUI;

public class HomePageObjects extends AbstractPages{
	private WebDriver driver;
	//Hàm khởi tạo - constructor method
	//Trùng tên với tên class
	//Không có kiểu trả về: void/ String/ int/ boolean
	//Được gọi đến và chạy đầu đầu tiên khi class đó được gọi lên
	//Vẫn có hàm khởi tạo mặc định nếu như mình không tự define
	//Giúp cho  mình khởi tạo cái context nào trước
	//Overloading : Đa hình thái(Polymorphism)
	// 1 hàm cùng tên khác số lượng tham số
	// 1 hàm cùng tên, cùng số lượng tham số + khác kiểu dữ liệu của tham số

	//Gán biến local = global
	//Map dirver giữa 2 class với nhau
	public HomePageObjects(WebDriver driver) {
		this.driver = driver;
	}
	public LoginUserPageObjects clickToMyAccountLink() {
		waitElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK) ;
		return PageGeneratorManager.getLoginUserPage(driver);
	}
	
	public boolean isMyAccountLinkUndisplayed() {
		return isElementUndisplayed(driver, HomePageUI.MY_ACCOUNT_HEADER_LINK);
	}

	public void clickToAccounFootertButton() {
		waitElementClickable(driver, HomePageUI.ACCOUNT_BUTTON_HEADER);
		clickToElement(driver, HomePageUI.ACCOUNT_BUTTON_HEADER);
	}
	public void clickToSubscribeButton() {
		waitElementClickable(driver, HomePageUI.SUBSCRIBE_BUTTON);
		clickToElement(driver, HomePageUI.SUBSCRIBE_BUTTON);
	}
	public boolean isSubscribeMsgUndisplayed() {
		return isElementUndisplayed(driver, HomePageUI.REQUIRED_SUBSCIBE_MSG);
	}
}
