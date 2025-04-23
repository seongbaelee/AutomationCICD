package AutomationTestingPractice.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	//this constructor runs first
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	//Page Factory
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css=".toast-message.ng-star-inserted")
	WebElement errorMessage;
	
	By error = By.cssSelector(".toast-message.ng-star-inserted");

	
	//action method
	public ProductCatalogue loginApplication(String userEmail, String userPassword) {
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(error);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
