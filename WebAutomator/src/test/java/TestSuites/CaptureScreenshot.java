/**
 * 
 */
package TestSuites;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;
import resources.base;

/**
 * @author vamsiravi
 *
 */
public class CaptureScreenshot extends base{

	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/reports/Sample.html");
		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
		 
		webDriver =initializeDriver();
	
	}
	
	@Test
	public void captureScreenShot(){
		test = extentReports.startTest("Capture Screenshot");
		//webDriver = new FirefoxDriver();
		webDriver.get("http://www.automationtesting.in");
		String title = webDriver.getTitle();
		Assert.assertEquals("Home - Automation Test", title);
		test.log(LogStatus.PASS, "Test Passed as titles are equal");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE){
			String screenshotPath = GetScreenShot.capture(webDriver, "screenshotForExtentReport");
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, "Screenshot below"+test.addScreenCapture(screenshotPath));
			
		}
		extentReports.endTest(test);
	}
	
	@AfterTest
	public void endReport(){
			webDriver.close();
			extentReports.flush();
			extentReports.close();
	}
	
}
