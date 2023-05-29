package KSKAcademy.abstractMethods;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import KSKAcademy.pageobjects.CartPage;
import KSKAcademy.pageobjects.OrderPage;

public class AbstractMethod {
	
	
	
	WebDriver driver;

	public AbstractMethod(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersButton;
	
	public CartPage goToCart()
	{
		cartButton.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrder()
	{
		ordersButton.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	

	public void waitForElementToAppear(By findBy)  //Waiting for WebElement to appear with locator as Parameter
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) //Waiting for WebElement to appear with WebElement as Parameter
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOf(ele));
	Thread.sleep(10000L);
	}

}
