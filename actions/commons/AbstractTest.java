package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {

	WebDriver driver;

	protected WebDriver getBrowserDriver(String browserName) {

		Browser browser = Browser.valueOf(browserName.toUpperCase());

		if (browser == browser.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser == browser.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser == browser.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if (browser == browser.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		} else if (browser == browser.COCCOC) {
			WebDriverManager.chromedriver().browserVersion("81.0.4044.69").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("/Users/giangpk/Downloads/coccoc (1).dmg");
			driver = new ChromeDriver(options);
		} else if (browser == browser.SAFARI) {
			driver = new SafariDriver();
		} else {
			throw new RuntimeException("Please input your browser");
		}
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String url) {

		Browser browser = Browser.valueOf(browserName.toUpperCase());

		if (browser == browser.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser == browser.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser == browser.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if (browser == browser.IE) {
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		} else if (browser == browser.COCCOC) {
			try {
				WebDriverManager.chromedriver().driverVersion("80.0.3987.16").setup();
				System.out.println("1");
			} catch (Exception e) {
				try {
					WebDriverManager.chromedriver().driverVersion("86.0.4240.22").setup();
					System.out.println("2");
				} catch (Exception e2) {
					try {
						WebDriverManager.chromedriver().driverVersion("85.0.4183.87").setup();
						System.out.println("3");
					} catch (Exception e3) {
						try {
							WebDriverManager.chromedriver().driverVersion("85.0.4183.83").setup();
							System.out.println("4");
						} catch (Exception e4) {
							WebDriverManager.chromedriver().driverVersion("85.0.4183.38").setup();
							System.out.println("5");
						}
					}
				}
			}
			ChromeOptions options = new ChromeOptions();
			options.setBinary("/Users/giangpk/Downloads/coccoc (1).dmg"); 
			driver = new ChromeDriver(options);
		} else if (browser == browser.SAFARI) {
			driver = new SafariDriver();
		} else {
			throw new RuntimeException("Please input your browser");
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		return driver;
	}
}
