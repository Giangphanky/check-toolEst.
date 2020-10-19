package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPages;
import pageUIs.liveGuru.HomePageUI;

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
	public LoginPageObjects clickToMyAccountLink() {
		waitElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK) ;
		return PageGeneratorManager.getLoginPage(driver);
	}

}
