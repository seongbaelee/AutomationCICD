package AutomationTestingPractice.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	
	public CheckOutPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".user__address input")
	private WebElement countryInputEle;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	private WebElement selectCountry;
	
	@FindBy(css=".actions a")
	private WebElement submitEle;
	
	By countryListLocator = By.cssSelector(".ta-results button");
			
	public void manageCountry(String targetCountry) {
		Actions a = new Actions(driver);
		a.sendKeys(countryInputEle, targetCountry).build().perform();
		waitForElementsToAppear(countryListLocator);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submitEle.click();
		return new ConfirmationPage(driver);
	}
	
}
