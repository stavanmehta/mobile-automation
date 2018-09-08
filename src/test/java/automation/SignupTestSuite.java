package automation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.android.pageobjects.SignupPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;

public class SignupTestSuite extends BaseTest {

	private SignupPage signupPage;
	private AndroidDriver<WebElement> driver;

	@BeforeSuite
	public void suiteSetup() throws IOException {
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "src/test/resources/apps");
		File app = new File(appDir.getCanonicalPath(), "bird.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Pixel_2_API_28");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		driver = new AndroidDriver<WebElement>(getServiceUrl(), capabilities);
		signupPage = new SignupPage(driver);
	}

	@AfterClass
	public void tearDown() {
		driver.removeApp("co.bird.android");
		driver.closeApp();
	}

	@BeforeTest
	public void testSetup() {
		signupPage.startMainActivity();
		signupPage.waitUntilEmailFieldVisible();
	}

	@Test(priority = 3, dataProvider = "loginEmail")
	public void testSignupAndLogin(String email) {

		signupPage.clearEmailSearchElement();
		signupPage.clickEmailSearchElement();
		signupPage.clearEmailSearchElement();

		Assert.assertEquals(signupPage.getAgreementText(),
				"By clicking 'RIDE', I confirm that I'm at least 18 years old, and I agree to Bird's Terms of Service and Privacy Policy");

		signupPage.enterEmail(email);
		signupPage.clickRideButton();

		Assert.assertEquals(signupPage.getAgreementSection15Text(), signupPage.section15Text);
		Assert.assertEquals(signupPage.getAgreementUnder18Text(), signupPage.under18Text);
		Assert.assertEquals(driver.currentActivity(), ".app.feature.rentalagreement.RentalAgreementActivity");

		signupPage.swipeVerticalDown(4);
		signupPage.waitUntilAgreementSection15Clickable();
		signupPage.clickAgreementSection15();
		signupPage.waitUntilgetAgreementUnder18Clickable();
		signupPage.clickAgreementUnder18();

		Assert.assertEquals(signupPage.getIAcceptButtonText(), "I AGREE");

		signupPage.clickIAcceptButton();

		Assert.assertEquals(signupPage.getRideActionButtonText(), "RIDE");
		Assert.assertEquals(driver.currentActivity(), ".app.feature.ride.activity.RideActivity");

	}

	@Test(priority = 2, dataProvider = "validEmail", enabled=true)
	public void testValidSignup(String email) {
		
		signupPage.clearEmailSearchElement();
		signupPage.clickEmailSearchElement();
		signupPage.clearEmailSearchElement();
		Assert.assertEquals(signupPage.getAgreementText(),
				"By clicking 'RIDE', I confirm that I'm at least 18 years old, and I agree to Bird's Terms of Service and Privacy Policy");
		signupPage.enterEmail(email);
		signupPage.clickRideButton();
		Assert.assertEquals(signupPage.getAgreementTitleText(), "CHECK YOUR EMAIL");

		Assert.assertEquals(signupPage.getEnterCodeLinkText(), "ENTER CODE from email or contact hello@bird.co");
		signupPage.goBack();
	}

	@Test(priority = 1, dataProvider = "invalidEmail", enabled=true)
	public void testInvalidEmail(String email) {
		
		signupPage.clearEmailSearchElement();
		signupPage.clickEmailSearchElement();
		signupPage.clearEmailSearchElement();
		
		Assert.assertEquals(signupPage.getAgreementText(),
				"By clicking 'RIDE', I confirm that I'm at least 18 years old, and I agree to Bird's Terms of Service and Privacy Policy");
		signupPage.enterEmail(email);
		signupPage.clickRideButton();
		Assert.assertNotEquals(signupPage.getAgreementTitleText(), "CHECK YOUR EMAIL");
		Assert.assertEquals(signupPage.getAgreementText(),
				"By clicking 'RIDE', I confirm that I'm at least 18 years old, and I agree to Bird's Terms of Service and Privacy Policy");
	}

	@DataProvider(name = "invalidEmail")
	public static Object[][] invalidEmails() {
		return new Object[][] { { " " }, { "birdauto" }, { "birdauto@" }, { "birdauto@mailinator" } };
	}

	@DataProvider(name = "validEmail")
	public static Object[][] validEmails() {
		return new Object[][] { { "birdauto@mailinator.com" }, { "bird.auto@mailinator.com" },
				{ "bird@bike.mailinator.com" }, { "bird+auto@mailinator.com" }, { "1234567890@mailinator.com" },
				{ "_______@mailinator.com" } };
	}

	@DataProvider(name = "loginEmail")
	public static Object[][] loginEmail() {
		String generatedString = RandomStringUtils.randomAlphanumeric(10);
		return new Object[][] { { generatedString + "@mailinator.com" } };
	}

}
