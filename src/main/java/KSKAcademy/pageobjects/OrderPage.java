package KSKAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KSKAcademy.abstractMethods.AbstractMethod;

public class OrderPage extends AbstractMethod{
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List <WebElement> productsName;
	
	
	
	public Boolean checkProductInOrder(String productName)
	{		
	Boolean match = productsName.stream().anyMatch(Product-> Product.getText().equalsIgnoreCase(productName));
	return match;
	}

	
}
