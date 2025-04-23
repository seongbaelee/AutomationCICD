package AutomationTestingPractice.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationTestingPractice.pageObjects.CartPage;
import AutomationTestingPractice.pageObjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	
	public void waitForElementToAppear(By locater) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locater));
	}
	
	public void waitForElementsToAppear(By locater) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locater));
	}
	
	public void waitForElementsToDisappear(By locater) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locater));
	}
	
	@FindBy(css="button[routerlink*='cart'")
	WebElement cartHeader;
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderHeader;
	
	By navBar = By.cssSelector("nav");
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		waitForElementToAppear(navBar);
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
}
