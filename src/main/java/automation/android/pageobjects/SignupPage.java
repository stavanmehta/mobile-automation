package automation.android.pageobjects;

import org.openqa.selenium.WebElement;

import automation.android.ElKeywords;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class SignupPage extends BasePage{

	public SignupPage(AndroidDriver<WebElement> driver) {
		super(driver);
	}

	private final String PACKAGE = "co.bird.android";
	private final String MAIN_ACTIVITY = "co.bird.android.app.feature.main.MainActivity";
	private final String ENTER_CODE_ACTIVITY = "co.bird.android.app.feature.magiclink.activity.EnterCodeActivity";
	public final String section15Text = " I certify that I have read and expressly agree to the terms and conditions of Section 15 Releases; "
			+ "Disclaimers; Assumption of Risk, and I acknowledge that this section limits my legal rights and remedies. "
			+ "I intend my assent to this Agreement to be a complete and unconditional release of all liability to the greatest "
			+ "extent permitted by law. I represent and certify that I am familiar with the operation of the Vehicle, and am "
			+ "reasonably competent and physically fit to ride the Vehicle.";
	public final String under18Text = " I certify that I am the Rider, I am 18 years old or over, I will wear a helmet where required by law, "
			+ "I will not ride a Bird with another occupant, I will obey all traffic laws, I will ride at my own risk, "
			+ "and I have read and expressly agree to the terms and conditions set forth in this Agreement.";

	/* Locators */
	private final String EMAIL_SEARCH_BOX = "id=emailEditor";
	private final String RIDE_BUTTON = "id=rideButton";
	private final String AGREEMENT_TEXT = "className=android.widget.TextView";
	private final String AGREEMENT_TITLE_TEXT = "id=titleText";
	private final String ENTER_CODE_LINK = "id=enterCodeOrContactText";
	private final String CODE_EDITOR = "id=codeEditor";
	private final String AGREEMENT_SECTION_15 = "id=agreement_checkbox_section15";
	private final String AGREEMNT_UNDER_18 = "id=agreement_checkbox_under_18";
	private final String I_ACCEPT_BUTTON = "id=accept-btn";
	private final String RIDE_ACTION_BUTTON = "id=action";

	/* Accessors */

	private AndroidElement getEmailSearchElement() {
		return (AndroidElement) ElKeywords.getElement(this.driver, EMAIL_SEARCH_BOX);
	}

	private AndroidElement getRideButton() {
		return (AndroidElement) ElKeywords.waitUntilElementVisible(this.driver, RIDE_BUTTON);
	}

	private AndroidElement getEnterCodeLinkEl() {
		return (AndroidElement) ElKeywords.getElement(this.driver, ENTER_CODE_LINK);
	}

	private AndroidElement getCodeEditor() {
		return (AndroidElement) ElKeywords.waitUntilElementVisible(this.driver, CODE_EDITOR);
	}

	private AndroidElement getAgreementSection15() {
		return (AndroidElement) ElKeywords.waitUntilElementVisible(this.driver, AGREEMENT_SECTION_15);
	}

	private AndroidElement getAgreementUnder18() {
		return (AndroidElement) ElKeywords.waitUntilElementVisible(this.driver, AGREEMNT_UNDER_18);
	}

	private AndroidElement getIAcceptButton() {
		return (AndroidElement) ElKeywords.waitUntilElementVisible(this.driver, I_ACCEPT_BUTTON);
	}

	public AndroidElement getRideActionButton() {
		return (AndroidElement) ElKeywords.waitUntilElementVisible(this.driver, RIDE_ACTION_BUTTON);
	}

	/* Helpers */
	public void startMainActivity() {
		this.driver.startActivity(new Activity(PACKAGE, MAIN_ACTIVITY));
	}

	public void startEnterCodeActivity() {
		this.driver.startActivity(new Activity(PACKAGE, ENTER_CODE_ACTIVITY));
	}

	public void waitUntilEmailFieldVisible() {
		ElKeywords.waitUntilElementVisible(this.driver, EMAIL_SEARCH_BOX);
	}

	public void enterEmail(String email) {
		getEmailSearchElement().sendKeys(email);
	}

	public void clickEmailSearchElement() {
		getEmailSearchElement().click();
	}

	public void clearEmailSearchElement() {
		getEmailSearchElement().clear();
	}
	
	public void clickRideButton() {
		ElKeywords.waitUntilElementVisible(this.driver, RIDE_BUTTON);
		getRideButton().click();
	}

	public AndroidElement waitUntilAgreementSection15Clickable() {
		return (AndroidElement) ElKeywords.waitUntilElementIsClickable(this.driver, AGREEMENT_SECTION_15);
	}

	public AndroidElement waitUntilgetAgreementUnder18Clickable() {
		return (AndroidElement) ElKeywords.waitUntilElementIsClickable(this.driver, AGREEMNT_UNDER_18);
	}

	public AndroidElement waitUntilgetIAcceptButtonClickable() {
		return (AndroidElement) ElKeywords.waitUntilElementIsClickable(this.driver, I_ACCEPT_BUTTON);
	}

	public String getAgreementText() {
		AndroidElement agreementEl = (AndroidElement) ElKeywords.waitUntilElementVisible(this.driver, AGREEMENT_TEXT);
		if (agreementEl != null) {
			return agreementEl.getText();
		}
		return null;
	}

	public String getAgreementTitleText() {
		AndroidElement agreementTitleEl = (AndroidElement) ElKeywords.waitUntilElementVisible(this.driver,
				AGREEMENT_TITLE_TEXT);
		if (agreementTitleEl != null) {
			return agreementTitleEl.getText();
		}
		return null;
	}

	public void clickEnterCodeLink() {
		getEnterCodeLinkEl().click();
	}

	public String getEnterCodeLinkText() {
		return getEnterCodeLinkEl().getText();
	}

	public void enterCode(String code) {
		getCodeEditor().sendKeys(code);
	}

	public void clickAgreementSection15() {
		getAgreementSection15().click();
	}

	public String getAgreementSection15Text() {
		return getAgreementSection15().getText();
	}

	public void clickAgreementUnder18() {
		getAgreementUnder18().click();
	}

	public String getAgreementUnder18Text() {
		return getAgreementUnder18().getText();
	}

	public String getIAcceptButtonText() {
		return getIAcceptButton().getText();
	}

	public void clickIAcceptButton() {
		getIAcceptButton().click();
	}

	public void clickRideActionButton() {
		getRideActionButton().click();
	}

	public String getRideActionButtonText() {
		return getRideActionButton().getText();
	}

	public String getInvalidEmailErrorMessage() {
		return "";
	}
}
