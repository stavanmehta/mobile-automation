package automation.android;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * @author Stavan Mehta
 * 
 */
public class ElKeywords {

	/**
	 * @param driver
	 * @param locator
	 * @return WebElement
	 * 
	 * Wait until given element is visible and return WebElement.
	 * If element is not visible in 30 seconds, it returns null
	 */
	public static WebElement waitUntilElementVisible(AndroidDriver<WebElement> driver, String locator) {

		String[] ls = locator.split("=");
		AndroidElement webElement = null;
		int timeout = 30;
		try {
			switch (ls[0]) {
			case "id":
				webElement = (AndroidElement) new WebDriverWait(driver, timeout)
						.until(ExpectedConditions.visibilityOfElementLocated(By.id(ls[1])));
				break;
			case "className":
				webElement = (AndroidElement) new WebDriverWait(driver, timeout)
						.until(ExpectedConditions.visibilityOfElementLocated(By.className(ls[1])));
				break;
			}
		} catch (TimeoutException timeoutException) {
			return null;
		} catch (NoSuchElementException noSuchElementException) {
			return null;
		}
		return webElement;
	}

	/**
	 * @param driver
	 * @param locator
	 * @return WebElement
	 * 
	 * Wait until given element is clickable and return WebElement.
	 * If element is not clickable in 30 seconds, it returns null
	 */
	public static WebElement waitUntilElementIsClickable(AndroidDriver<WebElement> driver, String locator) {
		
		String[] ls = locator.split("=");
		AndroidElement webElement = null;
		int timeout = 30;
		try {
			switch (ls[0]) {
			case "id":
				webElement = (AndroidElement) new WebDriverWait(driver, timeout)
						.until(ExpectedConditions.elementToBeClickable(By.id(ls[1])));
				break;
			case "className":
				webElement = (AndroidElement) new WebDriverWait(driver, timeout)
						.until(ExpectedConditions.elementToBeClickable(By.className(ls[1])));
				break;
			}
		} catch (TimeoutException timeoutException) {
			return null;
		} catch (NoSuchElementException noSuchElementException) {
			return null;
		}
		return webElement;
	}
	
	/**
	 * @param driver
	 * @param locator
	 * @return WebElement
	 * 
	 * Find webElemnet based on locator type and locator value. 
	 * Return null if WebElement not found for given locator
	 */
	public static WebElement getElement(AndroidDriver<WebElement> driver, String locator) {
		
		String[] ls = locator.split("=");
		AndroidElement webElement = null;
		switch (ls[0]) {
		case "id":
			webElement = (AndroidElement) driver.findElementById(ls[1]);
			break;
		case "className":
			webElement = (AndroidElement) driver.findElementByClassName(ls[1]);
			break;
		}
		return webElement;
	}

}
