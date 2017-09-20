/**
 * 
 */
package TestSuites;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
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
import TestData.TMTPTestData;
import Utilities.Anesthesia;
import Utilities.GetScreenShot;
import resources.base;

/**
 * @author vamsiravi
 *
 */
public class TMTPSmoke extends base{


	PathConfiguration pathConfig = new PathConfiguration();
	TMTPTestData testData = new TMTPTestData();

	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	Anesthesia anesthesia = new Anesthesia();
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports(pathConfig.getReportsDirectory()+"TMTPSmokeTestReport.html");
//		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
		webDriver =initializeDriver();

		webDriver.manage().window().maximize();
		
	}

	@Test(enabled=true)
	public void smokeTestSuiteTMTPRegisteredUser() throws AWTException, IOException, InterruptedException{

		test = extentReports.startTest("TMTP Smoke Test Registered User");
		
		
		// Go to the Lobby Login Page 
		webDriver.get("https://lobby.hilton.com/Pages/default.aspx");
		
		// User ID Verification
		// userId xpath .//*[@id='txtUserID']
		WebElement userId = webDriver.findElement(By.id("txtUserID"));
		userId.sendKeys(testData.getRegisteredUserName());
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "User ID has the username ");
		if(userId.isDisplayed()){
			test.log(LogStatus.PASS, "User ID is verified");
		}else{
			test.log(LogStatus.FAIL, "User ID is not verified");
		}
		
		
		//	Password Verification	
		webDriver.findElement(By.cssSelector("#txtPassword")).click();
		webDriver.findElement(By.cssSelector("#txtPassword")).sendKeys(testData.getRegisteredpassword());
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String loginScreenshotPath = GetScreenShot.capture(webDriver, "screenshotForLoginLobby");
		test.log(LogStatus.PASS, "Screenshot for the Lobby Login of Registered User"+test.addScreenCapture(loginScreenshotPath));
		
		//	Lobby Login Button xpath .//*[@id='btnLogin']
		WebElement lobbyLoginButton = webDriver.findElement(By.id("btnLogin"));
		lobbyLoginButton.click();
		
		Anesthesia.sleep(1);

		// Verify the User Information
		WebElement userInformation = webDriver.findElement(By.xpath(".//*[@id='zz4_Menu']"));
		String userInformationExpected = "Tom Steele";
		String userInformationActual = userInformation.getText();
		
		if(userInformationActual.contains(userInformationExpected)){
			String userInformationPassScreenshotPath = GetScreenShot.capture(webDriver, "userInformationPassScreenshotPath");
			test.log(LogStatus.PASS, "UserInformation is Verified"+test.addScreenCapture(userInformationPassScreenshotPath));
		}else{
			String userInformationFailScreenshotPath = GetScreenShot.capture(webDriver, "userInformationFailScreenshotPath");
			test.log(LogStatus.FAIL, "UserInformation is not Verified"+test.addScreenCapture(userInformationFailScreenshotPath));
		
		}
		
		// Goto My Applications and Click on the Element
		String oldTab = webDriver.getWindowHandle();
		WebElement myApplications = webDriver.findElement(By.xpath(".//*[@id='hlMenu']/li[4]/a/span[1]"));
//		TC01_TMTP option is available in the Lobby under "My applications"
		if(myApplications.isDisplayed()){
			test.log(LogStatus.PASS, "My Applications Link is verified");
		}else{
			test.log(LogStatus.PASS, "My Applications Link is not verified");
		}
		
		myApplications.click();
		Anesthesia.sleep(1);
		WebElement tmtpLink = webDriver.findElement(By.xpath(".//*[@id='myAppsAll']/div[28]/a"));
		
		if(tmtpLink.isDisplayed()){
			test.log(LogStatus.PASS, "TMTP Link is verified");
		}else{
			test.log(LogStatus.PASS, "TMTP Link is not verified");
		}
		
		tmtpLink.click();
		
		Anesthesia.sleep(2);
		
		ArrayList<String> newTab = new ArrayList<String>(webDriver.getWindowHandles());
		
		newTab.remove(oldTab);
		
		webDriver.switchTo().window(newTab.get(0));
		
		Anesthesia.sleep(5);
		
		
//		TC03_Validate to ensure "Friends and Family" screen is displayed when TM who is already registered to TMTP is logged in
//		TC04_Validate upon clicking on link , user is directed to the "Friends and Family" page for a TM who is already registered to TMTP
		if(webDriver.getTitle().equalsIgnoreCase("")){
			test.log(LogStatus.PASS, "Friend and Family Link and Page are verified");
		}else{
			test.log(LogStatus.FAIL, "Friend and Family Link and Page are not verified");
		}
		
		// Verify if the Logged In User is verified in case of Friends and Family Screen
		
		/*
		 * TC11_Ensure that if the logged in user name has a middle name then First Name, Middle Name and Last name is displayed in the header of the application on all applicable screens 

			TC12_Ensure that if the logged in user name has a special character in his name then the character should be displayed along with First Name, Middle Name and Last name in the header of the application on all applicable screens 
		 *
		 *
		 */
	
		
		
//		TC24_Blank row with "Add" button is displayed for a TM who has no F&F added in the list
		
		
		
//		TC28_Ensure user's Lobby username is displayed in Family & Friends screen
		
		
		
//		TC29_Validate to ensure all the modified text component and remaining text component displayed in Family and Friends screen
		
		
		
		
		
//			
		
		
		
//		Logout
		WebElement logoutLink = webDriver.findElement(By.xpath(".//*[@id='userInfo']/a"));
		logoutLink.click();
		Anesthesia.sleep(2);
		if(webDriver.getCurrentUrl().contains("logout.html")){
			String logoutScreenshotPath = GetScreenShot.capture(webDriver, "screenshotForLogout");
			test.log(LogStatus.PASS, "Verified the Logout Window Title"+test.addScreenCapture(logoutScreenshotPath));
		}else{
			String logoutFailScreenshotPath = GetScreenShot.capture(webDriver, "screenshotForLogoutFail");
			test.log(LogStatus.FAIL, "Failed to verify the Window Title"+test.addScreenCapture(logoutFailScreenshotPath));		
		}
		
//		TC31_Verify "log Out" button on Family & Friends screen and also verify "Return to the Lobby" link
		
		
		
		
		
		
//		webDriver.close();
	}
	
	
	@Test(enabled = true)
	public void smokeTestSuiteTMTPUnRegisteredUser() throws IOException{
test = extentReports.startTest("TMTP Smoke Test Registered User");
		
		
		// Go to the Lobby Login Page 
		webDriver.get("https://lobby.hilton.com/Pages/default.aspx");
		
		// User ID Verification
		// userId xpath .//*[@id='txtUserID']
		WebElement userId = webDriver.findElement(By.id("txtUserID"));
		userId.sendKeys(testData.getNonRegisteredUserName());
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "User ID has the username ");
		if(userId.isDisplayed()){
			test.log(LogStatus.PASS, "User ID is verified");
		}else{
			test.log(LogStatus.FAIL, "User ID is not verified");
		}
		
		
		//	Password Verification	
		webDriver.findElement(By.cssSelector("#txtPassword")).click();
		webDriver.findElement(By.cssSelector("#txtPassword")).sendKeys(testData.getNonRegisteredpassword());
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String loginScreenshotUnregisteredTMTPUserPath = GetScreenShot.capture(webDriver, "loginScreenshotUnregisteredTMTPUserPath");
		test.log(LogStatus.PASS, "Screenshot for the Lobby Login of Unregistered User"+test.addScreenCapture(loginScreenshotUnregisteredTMTPUserPath));
		
		//	Lobby Login Button xpath .//*[@id='btnLogin']
		WebElement lobbyLoginButton = webDriver.findElement(By.id("btnLogin"));
		lobbyLoginButton.click();
		
		Anesthesia.sleep(1);
		
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
