package KSKAcademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	
	int count=0;
	int maxTry =1;   //Number of reruns
	@Override
	public boolean retry(ITestResult result) {  //This method runs when it returns true
		// TODO Auto-generated method stub
		
		if (count<maxTry)
		{
			count++;
			return true;
		}
		
		return false;
	}
	


}
