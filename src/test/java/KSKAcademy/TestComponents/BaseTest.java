package KSKAcademy.TestComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import KSKAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	
	public LandingPage landingpage;
	
	public WebDriver initializeDrivers() throws IOException
	{
		//properties class - to read the global properties from .properties file
		
		Properties prop = new Properties();
		
		//To convert input file into InputStream -> FileInputStream class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\KSKAcademy\\resources\\GlobalData.properties");
		
		//To parse the InputStream - load method
		prop.load(fis);
		
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		//To get the attribute value from the file
		//String browserName = prop.getProperty("browser");
		
		
		if (browserName.contains("chrome"))
		{
			
		ChromeOptions options = new ChromeOptions();
		
		if (browserName.contains("headless"))
		{
		options.addArguments("headless");  //Headless chrome browser
		}
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		
		driver.manage().window().setSize(new Dimension(1440,900));
		
		}
		
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			//firefox driver set up 
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\SAI KUMAR\\Downloads\\Selenium WebDriver\\2. Install Java and Selenium\\geckodriver-v0.32.2-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		else if (browserName.equalsIgnoreCase("edge"))
		{
			//Edge driver set up 
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//Read json to String
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//String to HashMap -> Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
			
		});
		
		return data;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" +testCaseName+ ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//" +testCaseName+ ".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDrivers();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}

}
