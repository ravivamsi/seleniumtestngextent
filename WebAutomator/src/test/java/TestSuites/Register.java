/**
 * 
 */
package TestSuites;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import resources.base;

/**
 * @author vamsiravi
 *
 */
public class Register extends base{

	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/reports/TMTPRegisterSample.html");
		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
		 
//		webDriver =initializeDriver();
	
	}
	
	@Test(enabled=false)
	public void captureScreenShot(){
		test = extentReports.startTest("Capture Screenshot");
		//webDriver = new FirefoxDriver();
		webDriver.get("http://www.automationtesting.in");
		String title = webDriver.getTitle();
		Assert.assertEquals("Home - Automation Test", title);
		test.log(LogStatus.PASS, "Test Passed as titles are equal");
	}
	
	@Test(enabled=false)
	public void tmtpJoin(){
		test = extentReports.startTest("TMTP Join");
		
		webDriver.get("https://secure3-stg.hilton.com/en/hh/customer/join/joinHHonors.htm");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		WebElement firstName = webDriver.findElement(By.id("firstNameJoin"));
		firstName.sendKeys("Vamsi");
		
		WebElement lastname = webDriver.findElement(By.id("LastNameJoin"));
		lastname.sendKeys("Ravi");
		
		WebElement phoneNumber = webDriver.findElement(By.id("phoneJoin"));
		phoneNumber.sendKeys("8572056865");
		
		WebElement email = webDriver.findElement(By.id("emailJoin"));
		email.sendKeys("ravivamsi@yahoo.com");
		
		WebElement address = webDriver.findElement(By.id("street1"));
		address.sendKeys("606 Stonecrossing Pl");
		
		WebElement address2 = webDriver.findElement(By.id("street2"));
		address2.sendKeys("Ravi");
		
		WebElement zip = webDriver.findElement(By.id("postalCode"));
		zip.sendKeys("46227");
		
		WebElement city = webDriver.findElement(By.id("LastNameJoin"));
		city.sendKeys("Memphis");
		
		WebElement state = webDriver.findElement(By.xpath(".//*[@id='State']/optgroup[1]/option[43]/text()"));
		Select selectState = new Select(state);
		selectState.selectByVisibleText("Tennessee");
		// or
		state.click();
		// or https://stackoverflow.com/questions/20138761/how-to-select-a-dropdown-value-in-selenium-webdriver-using-java
		//	
		
		WebElement password = webDriver.findElement(By.id("LastNameJoin"));
		lastname.sendKeys("Ravi");
		
		
		WebElement confirmPassword = webDriver.findElement(By.id("LastNameJoin"));
		lastname.sendKeys("Ravi");
		
		WebElement joinButton = webDriver.findElement(By.xpath(".//*[@id='enrollForm']/p[2]/button"));
		lastname.sendKeys("Ravi");
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
//			webDriver.close();
			extentReports.flush();
			extentReports.close();
	}
	


}
