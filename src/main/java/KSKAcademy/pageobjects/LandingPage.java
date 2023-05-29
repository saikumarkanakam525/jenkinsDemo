package KSKAcademy.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KSKAcademy.abstractMethods.AbstractMethod;

public class LandingPage extends AbstractMethod {
		
		WebDriver driver;
		public LandingPage(WebDriver driver) 
		{
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
			
		}
		
		//WebElement userEmail = driver.findElement(By.id("userEmail"));
		
		
		//PageFactory
		
		
		@FindBy(id="userEmail")
		WebElement userEmailElement;
		
		@FindBy(id="userPassword")
		WebElement passwordElement;
		
		@FindBy(id="login")
		WebElement submit;
		
		//.ng-tns-c4-6 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error --> value of class attribute for error message
				
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;
				
				
		
		public ProductCatalogue loginApplication(String Email, String password)
		{
			userEmailElement.sendKeys(Email);
			passwordElement.sendKeys(password);
			submit.click();
			ProductCatalogue productCatalogue = new ProductCatalogue(driver);
			return productCatalogue;
		}
		
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
		}
	
}
