package KSKAcademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import KSKAcademy.TestComponents.BaseTest;
import KSKAcademy.TestComponents.Retry;
import KSKAcademy.pageobjects.CartPage;
import KSKAcademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		landingpage.loginApplication("anshika@gmail.com", "Iaming@000");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());

	}

	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";

		ProductCatalogue productCatalogue = landingpage.loginApplication("saikumar.kanakam169@gmail.com", "Kasaku@l525");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.checkProductInCart("ZARA COAT 33");
		Assert.assertFalse(match);

	}

}
