package KSKAcademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject()
	{
		//ExtentReports, ExtentSparkReporter - Helper class to set configurations to the report that is going to be created by main class - ExtentReports
		
				String path = System.getProperty("user.dir")+ "//reports//index.html";
				ExtentSparkReporter reporter = new ExtentSparkReporter(path); //ExtentSparkReporter takes "path" to create report
				reporter.config().setReportName("Web Automation Results");  //To set report name in html
				reporter.config().setDocumentTitle("Test Results");       //To set tab name in html
				
				
				ExtentReports extent = new ExtentReports();
				
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Sai Kumar");
				return extent;
	}

}
