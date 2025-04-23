package AutomationTestingPractice.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart h3")
	List<WebElement> productTitlesEle;
	
	@FindBy(css=".subtotal button")
	WebElement checkoutEle;
	
	By itemTagInCart = By.cssSelector(".cart h3");
	
	public boolean isProductInCart(String productName) {
		waitForElementsToAppear(itemTagInCart);
		Boolean match = productTitlesEle.stream().anyMatch(product -> product.getText().trim().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goToCheckout() {
		checkoutEle.click();
		return new CheckOutPage(driver);
	}
}
