/**
 * 
 */
package TestSuites;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Configuration.PathConfiguration;
import Utilities.GetScreenShot;
import resources.base;

/**
 * @author vamsiravi
 *
 */
public class HeadlessBrowser extends base{
	
	ExtentReports extentReports;


PathConfiguration pathConfig = new PathConfiguration();

ExtentTest test;

public static WebDriver webDriver;

public Long timeout = 10000l;

@BeforeTest
public void init() throws IOException{
	extentReports = new ExtentReports(pathConfig.getReportsDirectory()+"HeadlessBrowserTestReport.html");
	extentReports.loadConfig(new File(pathConfig.getBaseDirectory()+"extent.config")); 
}

@Test(enabled=false)
public void testHeadlessBrowser(){
	
	System.setProperty("phantomjs.binary.path", pathConfig.getPhanthomDriver());
	
	// Initialize the Headless Browser
	webDriver = new PhantomJSDriver();
	
	test = extentReports.startTest("Headless Browser Execution");
	//Load the URL
	webDriver.get("http://www.google.com");
	test.log(LogStatus.PASS, "Brwoser launched");
	
	WebElement searchElement = webDriver.findElement(By.name("q"));
	
	if(searchElement.isDisplayed()){
		test.log(LogStatus.PASS, "Element is verified");
	}else{
		test.log(LogStatus.FAIL, "Element failed");
	}
	searchElement.sendKeys("Cheese!");
	test.log(LogStatus.INFO, "Some keys are entered into the Search parameter");
	searchElement.submit();
	test.log(LogStatus.INFO, "Clicked the Search");
	
	String pageTitle = webDriver.getTitle();
	
	System.out.println("Headless Browser Implementation in Progress");
	
	System.out.println("Page Title"+pageTitle+" is verified");
	
	test.log(LogStatus.INFO, "Browser about to quit");
	webDriver.quit();
}

@AfterMethod
public void getResult(ITestResult result) throws IOException{
	if(result.getStatus()==ITestResult.FAILURE){
		String screenshotPath = GetScreenShot.capture(webDriver, "screenshotForExtentReportFail");
		test.log(LogStatus.FAIL, result.getThrowable());
		test.log(LogStatus.FAIL, "Screenshot below"+test.addScreenCapture(screenshotPath));
		
	}
	extentReports.endTest(test);
}

@AfterTest
public void endReport(){
		extentReports.flush();
		extentReports.close();
}

}
