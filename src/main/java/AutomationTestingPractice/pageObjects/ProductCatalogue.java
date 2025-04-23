package AutomationTestingPractice.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;
	
	//this constructor runs first
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	List<WebElement> products = driver.findElements(By.cssSelector(".container .card"));

	
	//Page Factory
	@FindBy(css=".container .card")
	List<WebElement> products;

	By ProductsBy = By.cssSelector(".container .card");
	By addToCart = By.cssSelector(".card button:nth-of-type(2)");
	By toastMessage = By.cssSelector("[role='alert']");
	By spinner = By.cssSelector(".ng-animating");
	
	//action method
	public List<WebElement> getProductList() {
		waitForElementToAppear(ProductsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement Product = getProductList().stream().filter(product -> product.findElement(By.tagName("h5")).getText().equals(productName))
		.findFirst().orElse(null);
		return Product;
	}
	
	public void addProductToCart(String productName) {
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementsToDisappear(spinner);
	}
}
