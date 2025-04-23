package AutomationTestingPractice;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTestingPractice.TestComponents.BaseTest;
import AutomationTestingPractice.TestComponents.Retry;
import AutomationTestingPractice.pageObjects.CartPage;
import AutomationTestingPractice.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

		@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
		public void loginErrorValidation() throws IOException {
			
			landingPage.loginApplication("we123@daum.com", "we123!@");
			Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		}
		
		@Test
		public void productErrorValidation() throws IOException {
			
			String productName ="ADIDAS ORIGINAL";
			ProductCatalogue productCatalogue = landingPage.loginApplication("qwe123@daum.com", "Qwe123!@");
			
			productCatalogue.addProductToCart(productName);
			CartPage cartPage = productCatalogue.goToCartPage();
			
			Boolean match = cartPage.isProductInCart("ADIDAS ORIGINALL");
			Assert.assertFalse(match);	
		}

}
