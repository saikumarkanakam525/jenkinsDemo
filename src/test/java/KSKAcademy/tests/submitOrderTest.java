package KSKAcademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import KSKAcademy.TestComponents.BaseTest;
import KSKAcademy.pageobjects.CartPage;
import KSKAcademy.pageobjects.ConfirmationPage;
import KSKAcademy.pageobjects.OrderPage;
import KSKAcademy.pageobjects.ProductCatalogue;
import KSKAcademy.pageobjects.checkOutPage;

public class submitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = "purchase")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.checkProductInCart(input.get("product"));
		Assert.assertTrue(match);
		checkOutPage checkout = cartPage.goToCheckOut();
		checkout.selectCountry("India");
		ConfirmationPage confirmationpage = checkout.orderSubmit();
		String confirmMessage = confirmationpage.orderConfirm();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingpage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderPage = productCatalogue.goToOrder();
		Assert.assertTrue(orderPage.checkProductInOrder(productName));

	}

//	@DataProvider
//	public Object[][] getData() //Data driving using multidimensional array
//	{
//		return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//	}

	@DataProvider
	public Object[][] getData() throws IOException // Data driving Using Hashmap
	{
//		HashMap<String, String> map= new HashMap<String, String>();
//		map.put("email","anshika@gmail.com");
//		map.put("password","Iamking@000");
//		map.put("product","ZARA COAT 3");
//		
//		HashMap<String, String> map1= new HashMap<String, String>();
//		map1.put("email","shetty@gmail.com");
//		map1.put("password","Iamking@000");
//		map1.put("product","ADIDAS ORIGINAL");
//		return new Object[][] {{map},{map1}};

		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\KSKAcademy\\data\\purchaseOrder.json";
		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

}
