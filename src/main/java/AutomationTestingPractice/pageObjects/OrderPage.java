package AutomationTestingPractice.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTestingPractice.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="table tbody tr td:nth-of-type(2)")
	List<WebElement> titlesListEle;
	
	By itemTitlesInOrder = By.cssSelector("table tbody tr td:nth-of-type(2)");
	
	public boolean isProductInOrder(String productName) {
		waitForElementsToAppear(itemTitlesInOrder);
		Boolean match = titlesListEle.stream().anyMatch(product -> product.getText().trim().equalsIgnoreCase(productName));
		return match;
	}
}
