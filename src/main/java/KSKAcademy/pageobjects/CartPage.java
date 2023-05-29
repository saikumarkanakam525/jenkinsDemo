package KSKAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KSKAcademy.abstractMethods.AbstractMethod;

public class CartPage extends AbstractMethod{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List <WebElement> productsInCart;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	public Boolean checkProductInCart(String productName)
	{		
	Boolean match = productsInCart.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	return match;
	}

	public checkOutPage goToCheckOut()
	{
	checkOutButton.click();
	return new checkOutPage(driver);
	
	}
}
