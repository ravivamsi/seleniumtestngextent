package TestSuites;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
 * 
 */

/**
 * @author vamsiravi
 *
 */
public class RemoveMember extends base{

	PathConfiguration pathConfig = new PathConfiguration();

	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports(pathConfig.getReportsDirectory()+"TMTPAddMemberReport.html");
//		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
		 
		
	
	}


	

	@Test(enabled=false)
	public void tmtpRemoveFriend() throws AWTException, IOException, InterruptedException{
		webDriver =initializeDriver();
		webDriver.manage().window().maximize();
		test = extentReports.startTest("TMTP Add Member");
		
		webDriver.get("https://tmtp-stg.hilton.com/tmtp/entry.html");
		
		// userId xpath .//*[@id='txtUserID']
		WebElement userId = webDriver.findElement(By.id("txtUserID"));
		userId.sendKeys("HRCCTest");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		//	password xpath .//*[@id='txtPassword']
//		WebElement password = webDriver.findElement(By.cssSelector("#txtPassword"));
//		password.click();
//		password.sendKeys("July31-");
		
		webDriver.findElement(By.cssSelector("#txtPassword")).click();
		webDriver.findElement(By.cssSelector("#txtPassword")).sendKeys("July31-");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String loginScreenshotPath = GetScreenShot.capture(webDriver, "screenshotForLoginLobby");
		test.log(LogStatus.PASS, "Screenshot for the Lobby Login"+test.addScreenCapture(loginScreenshotPath));
		
		//	Lobby Login Button xpath .//*[@id='btnLogin']
		WebElement lobbyLoginButton = webDriver.findElement(By.id("btnLogin"));
		lobbyLoginButton.click();
		String titleScreenshotPath = GetScreenShot.capture(webDriver, "screenshotForTitle");
		String windowscreenshotPath = GetScreenShot.capture(webDriver, "screenshotForTitle");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String actualTitle = webDriver.getTitle();
		String expectedTitle = "Member";
		
		if(actualTitle.contains(expectedTitle)){
			
			test.log(LogStatus.PASS, "Verified the Window Title"+test.addScreenCapture(titleScreenshotPath));
			
		}else{
			test.log(LogStatus.FAIL, "Failed to verify the Window Title"+test.addScreenCapture(windowscreenshotPath));
			
		}
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
//		Verification
		
		WebElement currentUser = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div/span[1]"));
		if(currentUser.getText().contains("MANOO")){
			String currentFriendScreenshotPath = GetScreenShot.capture(webDriver, "currentFriendScreenshotPath");
			
			test.log(LogStatus.PASS, "Verified the Current Friend or Family"+test.addScreenCapture(currentFriendScreenshotPath));
		}else{
			String currentFriendFailScreenshotPath = GetScreenShot.capture(webDriver, "currentFriendScreenshotPath");
			
			test.log(LogStatus.FAIL, "Failed to verify the Current Friend or Family"+test.addScreenCapture(currentFriendFailScreenshotPath));
			
		}
		
		WebElement removeUser = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div/span[3]/a"));
		removeUser.click();
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		WebElement currentRemovedUser = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div/span[1]"));
		if(currentRemovedUser.getText().contains("MANOO")){
			String currentFriendRemoveScreenshotPath = GetScreenShot.capture(webDriver, "currentFriendScreenshotPath");
			
			test.log(LogStatus.FAIL, "Verified the Current Friend or Family"+test.addScreenCapture(currentFriendRemoveScreenshotPath));
		}else{
			String currentFriendRemoveFailScreenshotPath = GetScreenShot.capture(webDriver, "currentFriendScreenshotPath");
			
			test.log(LogStatus.PASS, "Failed to verify the Current Friend or Family"+test.addScreenCapture(currentFriendRemoveFailScreenshotPath));
			
		}
		
		WebElement logoutLink = webDriver.findElement(By.xpath(".//*[@id='userInfo']/a"));
		logoutLink.click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		if(webDriver.getCurrentUrl().contains("logout.html")){
			String logoutScreenshotPath = GetScreenShot.capture(webDriver, "screenshotForLogout");
			test.log(LogStatus.PASS, "Verified the Logout Window Title"+test.addScreenCapture(logoutScreenshotPath));
		}else{
			String logoutFailScreenshotPath = GetScreenShot.capture(webDriver, "screenshotForLogoutFail");
			test.log(LogStatus.FAIL, "Failed to verify the Window Title"+test.addScreenCapture(logoutFailScreenshotPath));		
		}
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
			
			extentReports.flush();
			extentReports.close();
	}
	
	
	



}
