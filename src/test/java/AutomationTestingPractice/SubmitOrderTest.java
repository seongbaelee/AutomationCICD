package AutomationTestingPractice;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationTestingPractice.TestComponents.BaseTest;
import AutomationTestingPractice.pageObjects.CartPage;
import AutomationTestingPractice.pageObjects.CheckOutPage;
import AutomationTestingPractice.pageObjects.ConfirmationPage;
import AutomationTestingPractice.pageObjects.OrderPage;
import AutomationTestingPractice.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	
		String productName ="ADIDAS ORIGINAL";
		//demo
		@Test(dataProvider="getData", groups= {"Purchase"})
		public void submitOrder(HashMap<String, String> input) throws IOException {
			
			ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
			
			productCatalogue.addProductToCart(input.get("productName"));
			CartPage cartPage = productCatalogue.goToCartPage();
			
			Boolean match = cartPage.isProductInCart(input.get("productName"));
			Assert.assertTrue(match);	
			CheckOutPage checkoutPage = cartPage.goToCheckout();		
			
			checkoutPage.manageCountry("can");
			ConfirmationPage confirmationPage = checkoutPage.submitOrder();
			
			String confirmMessage = confirmationPage.getConfirmMessage();
			Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		}

		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest() {
			ProductCatalogue productCatalogue = landingPage.loginApplication("qwe123@daum.com", "Qwe123!@");
			OrderPage orderpage = productCatalogue.goToOrdersPage();
			Boolean match = orderpage.isProductInOrder(productName);
			Assert.assertTrue(match);	
		}
		
		//Extent Reports
		
		@DataProvider
		public Object[][] getData() throws IOException {
//			HashMap<String, String> map = new HashMap<String, String>();
//			map.put("email", "qwe123@daum.com");
//			map.put("password", "Qwe123!@");
//			map.put("productName", "ADIDAS ORIGINAL");
//			
//			HashMap<String, String> map1 = new HashMap<String, String>();
//			map1.put("email", "qwe1233@daum.com");
//			map1.put("password", "Qwe123!@");
//			map1.put("productName", "IPHONE 13 PRO");
//			
//			return new Object[][] {{ map }, { map1}};
			
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\AutomationTestingPractice\\data\\PurchaseOrder.json");
			System.out.println(data);
			return new Object[][] { { data.get(0) }, { data.get(1) } };
		}
}
