package AutomationTestingPractice.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver;
	
	public ConfirmationPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMsgEle;
	
	By confirmMessageLocator = By.cssSelector(".hero-primary");
	
	public String getConfirmMessage() {
		waitForElementToAppear(confirmMessageLocator);
		return confirmMsgEle.getText().trim();
	}
	
}
