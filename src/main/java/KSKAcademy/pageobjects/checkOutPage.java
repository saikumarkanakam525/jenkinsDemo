package KSKAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KSKAcademy.abstractMethods.AbstractMethod;

public class checkOutPage extends AbstractMethod{
	WebDriver driver;
	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

	@FindBy(css="[placeholder='Select Country']")
	private WebElement selectCountry;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement pickCountry;
	
	@FindBy(css=".action__submit")
	private WebElement submit;
	
	private By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(selectCountry, countryName).build().perform();
		waitForElementToAppear(results);
		pickCountry.click();
	}
	
	public ConfirmationPage orderSubmit()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", submit);
		return new ConfirmationPage(driver);
	}
}
