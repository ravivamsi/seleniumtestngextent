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
public class AddMember extends base{

	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/reports/TMTPAddMemberReport.html");
//		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
		 
		
	
	}


	

	@Test(enabled=false)
	public void tmtpAddFriend() throws AWTException, IOException, InterruptedException{
		
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
		
		
		WebElement friendFirstName = webDriver.findElement(By.id("ffFirstNameInput"));
		WebElement friendLastName = webDriver.findElement(By.id("ffLastNameInput"));
		WebElement friendHHonors = webDriver.findElement(By.id("ffHHonorsInput"));
		WebElement friendAdd = webDriver.findElement(By.id("addFFLink"));
		
		
		
		friendFirstName.sendKeys("MANOO");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("KUMAA");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("001341833");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		Thread.sleep(5000l);
		
//		Verification
		
		WebElement currentUser = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div/span[1]"));
		if(currentUser.getText().contains("MANOO")){
			String currentFriendScreenshotPath = GetScreenShot.capture(webDriver, "currentFriendScreenshotPath");
			
			test.log(LogStatus.PASS, "Verified the Current Friend or Family"+test.addScreenCapture(currentFriendScreenshotPath));
		}else{
			String currentFriendFailScreenshotPath = GetScreenShot.capture(webDriver, "currentFriendScreenshotPath");
			
			test.log(LogStatus.FAIL, "Failed to verify the Current Friend or Family"+test.addScreenCapture(currentFriendFailScreenshotPath));
			
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
		//	webDriver.close();
			extentReports.flush();
			extentReports.close();
	}
	
	
	


}



/*@Test
public void tmtpSignIn() throws AWTException, IOException{
	test = extentReports.startTest("TMTP Sign In");
	
	webDriver.get("https://tmtp-stg.hilton.com/tmtp/entry.html");
	
	// userId xpath .//*[@id='txtUserID']
	WebElement userId = webDriver.findElement(By.id("txtUserID"));
	userId.sendKeys("HRCCTest");
	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	
	
	//	password xpath .//*[@id='txtPassword']
//	WebElement password = webDriver.findElement(By.cssSelector("#txtPassword"));
//	password.click();
//	password.sendKeys("July31-");
	
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
	
}*/