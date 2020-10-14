package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPages {

	public void setImplicitWait(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public void openPageUrl(WebDriver driver, String url) {
		driver.get(url);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void waitAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void accecptAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.dismiss();
	}

	public String getAlertText(WebDriver driver) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void sendKeysToAlert(WebDriver driver, String text) {
		waitAlertPresence(driver);
		alert = driver.switchTo().alert();
		alert.sendKeys(text);
	}
	
	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindow = driver.getWindowHandles();

		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title))
				break;
		}
	}

	public void switchWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindow : allWindows) {

			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	public WebElement find(WebDriver driver, String xpathValue) {
		return driver.findElement(byXpath(xpathValue));
	}

	public List<WebElement> finds(WebDriver driver, String xpathValue) {
		return driver.findElements(byXpath(xpathValue));
	}

	public By byXpath(String xpathValue) {
		return By.xpath(xpathValue);
	}

	public void clickToElement(WebDriver driver, String xpathValue) {
		find(driver, xpathValue).click();
	}

	public void sendKeysToElement(WebDriver driver, String xpathValue, String text) {
		element = find(driver, xpathValue);
		element.clear();
		element.sendKeys(text);
	}

	public void selectItemInDropDown(WebDriver driver, String xpathValue, String itemValue) {
		select = new Select(find(driver, xpathValue));
		select.selectByVisibleText(itemValue);
	}

	public String getSeltectedItemDropDown(WebDriver driver, String xpathValue) {
		select = new Select(find(driver, xpathValue));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String xpathValue) {
		select = new Select(find(driver, xpathValue));
		return select.isMultiple();
	}

	public void selectItemCustomDropdown(WebDriver driver, String parentLocator, String childSelectLocator,
			String expectedItem) {
		find(driver, parentLocator).click();
		sleepInSeconds(2);
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(childSelectLocator)));
		elements = driver.findElements(byXpath(childSelectLocator));
		for (WebElement webElement : elements) {
			if (webElement.getText().equals(expectedItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
				sleepInSeconds(2);
				webElement.click();
				sleepInSeconds(2);
				break;
			}
		}

	}

	public String getElementAttributeValue(WebDriver driver, String xpathValue, String attributeName) {
		element = find(driver, xpathValue);
		return element.getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		return element.getText();
	}

	public int countElementNumber(WebDriver driver, String xpathValue) {
		elements = finds(driver, xpathValue);
		return elements.size();
	}

	public void checkToCheckBox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void unCheckToCheckBox(WebDriver driver, String xpathValue) {
		element = find(driver, xpathValue);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String xpathValue) {
		return find(driver, xpathValue).isSelected();
	}

	public void switchToFrameOrIFrame(WebDriver driver, String xpathValue) {
		driver.switchTo().frame(find(driver, xpathValue));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String xpathValue) {
		action = new Actions(driver);
		action.moveToElement(find(driver, xpathValue)).perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, String xpathValue, Keys key) {
		action = new Actions(driver);
		action.sendKeys(find(driver, xpathValue), key);
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public void highlightElement(WebDriver driver, String xpathValue) {
		WebElement element = find(driver, xpathValue);
		String originalStyle = element.getAttribute("style");
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSeconds(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", find(driver, xpathValue));
	}

	public void scrollToElement(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", find(driver, xpathValue));
	}

	public void sendkeyToElementByJS(WebDriver driver, String xpathValue, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", find(driver, xpathValue));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathValue, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", find(driver, xpathValue));
	}

	public boolean isImageLoaded(WebDriver driver, String xpathValue) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 0", find(driver, xpathValue));
		
		if(status)
			return true;
		return false;
	}
	
	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public void waitElementVisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byXpath(xpathValue)));
		
	}
	
	public void waitElementInvisible(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(xpathValue)));
		
	}
	
	public void waitElementClickable(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(xpathValue)));
		
	}
	
	 public void waitElementPresence(WebDriver driver, String xpathValue) {
		explicitWait = new WebDriverWait(driver, longTimeOut);
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(byXpath(xpathValue)));
	}
	
	
	 
	public void sleepInSeconds(long time) {
		try {
			Thread.sleep(1000 * time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private long longTimeOut = 30;
	private long shortTimeOut = 10;
	private Select select;
	private Alert alert;
	private WebElement element;
	private List<WebElement> elements;
	private Actions action;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;

}
