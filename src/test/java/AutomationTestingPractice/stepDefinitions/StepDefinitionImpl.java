package AutomationTestingPractice.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import AutomationTestingPractice.TestComponents.BaseTest;
import AutomationTestingPractice.pageObjects.CartPage;
import AutomationTestingPractice.pageObjects.CheckOutPage;
import AutomationTestingPractice.pageObjects.ConfirmationPage;
import AutomationTestingPractice.pageObjects.LandingPage;
import AutomationTestingPractice.pageObjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	//background
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page () throws IOException {
		landingPage = launchApplication();
	}
	
	//senario
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password (String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to Cart$") 
	public void I_add_product_to_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}
	
    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) {
    	CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.isProductInCart(productName);
		Assert.assertTrue(match);	
		CheckOutPage checkoutPage = cartPage.goToCheckout();		
		
		checkoutPage.manageCountry("can");
		confirmationPage = checkoutPage.submitOrder();
    }
    
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string) {
    	String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then("{string} message is displayed")
    public void message_displayed_landingPage(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
    }



}
