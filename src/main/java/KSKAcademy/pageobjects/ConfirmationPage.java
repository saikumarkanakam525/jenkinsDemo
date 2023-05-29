package KSKAcademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KSKAcademy.abstractMethods.AbstractMethod;

public class ConfirmationPage extends AbstractMethod {
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);
	
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmText;
	
	public String orderConfirm()
	{
	
	String confirmMessage = confirmText.getText();
	return confirmMessage;
	}

}
