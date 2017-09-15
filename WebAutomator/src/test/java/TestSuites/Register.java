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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Configuration.PathConfiguration;
import Utilities.Anesthesia;
import Utilities.GetScreenShot;
import resources.base;

/**
 * @author vamsiravi
 *
 */
public class Register extends base{

	PathConfiguration pathConfig = new PathConfiguration();
	
	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports(pathConfig.getReportsDirectory()+"TMTPRegister.html");
//		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
		 
//		webDriver =initializeDriver();
	
	}
	
	
	
	@Test(enabled=false)
	public void tmtpJoin() throws IOException{
		
	
		webDriver =initializeDriver();

		webDriver.manage().window().maximize();
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
		Thread thread = new Thread();
		
		test = extentReports.startTest("TMTP Join");
		
		webDriver.get("https://tmtp-stg.hilton.com/tmtp/entry.html");

		// userId xpath .//*[@id='txtUserID']
		WebElement userId = webDriver.findElement(By.id("txtUserID"));
		userId.sendKeys("vamsiravi");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		//	password xpath .//*[@id='txtPassword']
//		WebElement password = webDriver.findElement(By.cssSelector("#txtPassword"));
//		password.click();
//		password.sendKeys("July31-");
		
		webDriver.findElement(By.cssSelector("#txtPassword")).click();
		webDriver.findElement(By.cssSelector("#txtPassword")).sendKeys("Aarvy@@2789");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String loginScreenshotPath = GetScreenShot.capture(webDriver, "screenshotForLoginLobby");
		test.log(LogStatus.PASS, "Screenshot for the Lobby Login"+test.addScreenCapture(loginScreenshotPath));
		
		//	Lobby Login Button xpath .//*[@id='btnLogin']
		WebElement lobbyLoginButton = webDriver.findElement(By.id("btnLogin"));
		lobbyLoginButton.click();
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		Anesthesia.sleep(10);
		
		WebElement joinNow = webDriver.findElement(By.xpath(".//*[@id='joinButtonA']/span"));
		
		joinNow.click();
		
		Anesthesia.sleep(5);
		
		WebElement firstNameTextBox = webDriver.findElement(By.id(""));
		WebElement lastNameTextBox = webDriver.findElement(By.id(""));
		WebElement cityTextBox = webDriver.findElement(By.id(""));
		WebElement addressTextBox = webDriver.findElement(By.id(""));
		WebElement address2TextBox = webDriver.findElement(By.id(""));
		
		WebElement zipTextBox = webDriver.findElement(By.id(""));
		WebElement emailTextBox = webDriver.findElement(By.id(""));
		WebElement passwordTextBox = webDriver.findElement(By.id(""));
		WebElement confirmPasswordTextBox = webDriver.findElement(By.id(""));
		
		
		
		webDriver.close();
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
