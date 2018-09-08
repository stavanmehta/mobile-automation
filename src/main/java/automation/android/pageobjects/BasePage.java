package automation.android.pageobjects;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {
	AndroidDriver<WebElement> driver;
	
	public BasePage(AndroidDriver<WebElement> driver) {
		this.driver = driver;
	}

	public void swipeVerticalDown(int pages) {
		for (int i=0; i<pages; i++) {
			swipeVertical(this.driver, 1050, 200);
			swipeVertical(this.driver, 1250, 200);
			swipeVertical(this.driver, 1450, 200);
			swipeVertical(this.driver, 1650, 200);
			swipeVertical(this.driver, 1750, 200);
		}
	}
	
	public void goBack() {
		this.driver.navigate().back();
	}
	
	private void swipeVertical(AndroidDriver<WebElement> driver, int startPoint, int endPoint) {
		int anchor = 550;
		new TouchAction(this.driver).press(PointOption.point(anchor, startPoint)).waitAction()
				.moveTo(PointOption.point(anchor, endPoint)).release().perform();
		
	}
}
