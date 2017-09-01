/**
 * 
 */
package TestSuites;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
	
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/reports/Sample.html");
		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
		 
		webDriver =initializeDriver();
	
	}
	
	/*@Test
	public void captureScreenShot(){
		test = extentReports.startTest("Capture Screenshot");
		//webDriver = new FirefoxDriver();
		webDriver.get("http://www.automationtesting.in");
		String title = webDriver.getTitle();
		Assert.assertEquals("Home - Automation Test", title);
		test.log(LogStatus.PASS, "Test Passed as titles are equal");
	}*/
	
	
	@Test
	public void anotherTest() throws InterruptedException{
		test = extentReports.startTest("ICJ Stage");
		webDriver.get("https://fdstg.hilton.com/idp/startSSO.ping?PartnerSpId=https://contact-center--icjtest.cs30.my.salesforce.com/");
		
		webDriver.findElement(By.id("txtUserID")).sendKeys("nkumar3");
		//webDriver.wait(timeout);
		System.out.println("Username is set");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		webDriver.findElement(By.id("txtPassword")).clear();
		webDriver.findElement(By.id("txtPassword")).sendKeys("Hilton@77");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		/*if(webDriver.findElement(By.className("txtPassword")).isSelected()){
			test.log(LogStatus.PASS, "Password Textbox is selected");
			webDriver.findElement(By.className("txtPassword")).sendKeys("Hilton@77");
			System.out.println("Password is set");
		}else{
			test.log(LogStatus.FAIL, "Password Textbox is missing");
		}*/
		
		//sendKeys("Hilton@77");
		
		
		//webDriver.wait(timeout);
		//webDriver.findElement(By.xpath(".//*[@id='txtPassword']")).sendKeys("Hilton@77");
		
		//webDriver.wait(timeout);
		
		
		webDriver.findElement(By.id("btnLogin")).click();
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		webDriver.findElement(By.id("thePage:j_id2:i:f:pb:pbb:nextAjax")).click();
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//webDriver.findElement(By.xpath(".//*[@id='thePage:j_id2:i:f:pb:pbb:nextAjax']")).click();
		
		//webDriver.wait(timeout);
		
		/*String title = webDriver.getTitle();
		Assert.assertEquals("New Reservation - Console", title);
		test.log(LogStatus.PASS, "Test Passed as titles are  equal");
		*/webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		webDriver.findElement(By.id("ext-gen55")).click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		/*webDriver.findElement(By.xpath("/html/body/div[1]/ul/li[3]/a")).click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		WebElement reservationConfirmationTextBox = webDriver.findElement(By.id("resNum"));
		reservationConfirmationTextBox.sendKeys("12345678");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		reservationConfirmationTextBox.sendKeys(Keys.TAB);
		reservationConfirmationTextBox.sendKeys(Keys.ENTER);*/
		webDriver.findElement(By.id("userNavLabel")).click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		webDriver.findElement(By.id("app_logout")).click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
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
