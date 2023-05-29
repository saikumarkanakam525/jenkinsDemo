package KSKAcademy.stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import KSKAcademy.TestComponents.BaseTest;
import KSKAcademy.pageobjects.CartPage;
import KSKAcademy.pageobjects.ConfirmationPage;
import KSKAcademy.pageobjects.LandingPage;
import KSKAcademy.pageobjects.ProductCatalogue;
import KSKAcademy.pageobjects.checkOutPage;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationpage;
	public checkOutPage checkout;
	
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page()
	{
		//code
		
		landingPage = launchApplication();
		
	}
	
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password)
	{
		productCatalogue = landingpage.loginApplication(username, password);
	}

	
	@When("^I add product (.+) into Cart$")
	public void I_add_product_into_Cart(String productName)
	{
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^CheckOut (.+) and submit the order$")
	public void CheckOut_and_submit_the_order(String productName)
	{
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.checkProductInCart(productName);
		Assert.assertTrue(match);
		checkout = cartPage.goToCheckOut();
		checkout.selectCountry("India");
		confirmationpage = checkout.orderSubmit();
	}
	
	
	@Then("{string} message is displayed on Confirmation page")
	public void message_is_displayed_on_Confirmation_Page(String string)
	{
		String confirmMessage = confirmationpage.orderConfirm();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
}
