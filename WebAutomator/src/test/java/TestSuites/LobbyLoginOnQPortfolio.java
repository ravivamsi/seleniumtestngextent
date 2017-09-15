/**
 * 
 */
package TestSuites;

import java.awt.AWTException;
import java.io.File;
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
import Utilities.GetScreenShot;
import resources.base;

/**
 * @author vamsiravi
 *
 */
public class LobbyLoginOnQPortfolio extends base{

	PathConfiguration pathConfig = new PathConfiguration();

	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports(pathConfig.getReportsDirectory()+"TMTPVerifyLobbyUserInformationReport.html");
//		extentReports.loadConfig(new File(pathConfig.getBaseDirectory()+"extent.config")); 
	
	}

	@Test(enabled=false)
	public void verifyLobbyLoginGotoMyApplicationsAndTMTP() throws AWTException, IOException, InterruptedException{
		
		webDriver =initializeDriver();

		webDriver.manage().window().maximize();
		
		test = extentReports.startTest("TMTP Goto My Applications");
		
		webDriver.get("https://lobby.hilton.com/Pages/default.aspx");
		
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
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		// Verify the User Information
		
		WebElement userInformation = webDriver.findElement(By.xpath(".//*[@id='zz4_Menu']"));
		String userInformationExpected = "Tom Steele";
		String userInformationActual = userInformation.getText();
		
		if(userInformationActual.contains(userInformationExpected)){
			String userInformationPassScreenshotPath = GetScreenShot.capture(webDriver, "userInformationPassScreenshotPath");
			test.log(LogStatus.PASS, "UserInformation is Verified"+test.addScreenCapture(userInformationPassScreenshotPath));
		}else{
			String userInformationFailScreenshotPath = GetScreenShot.capture(webDriver, "userInformationFailScreenshotPath");
			test.log(LogStatus.FAIL, "UserInformation is Verified"+test.addScreenCapture(userInformationFailScreenshotPath));
		
		}
		
		// Goto My Applications and Click on the Element
		String oldTab = webDriver.getWindowHandle();
		WebElement myApplications = webDriver.findElement(By.xpath(".//*[@id='hlMenu']/li[4]/a/span[1]"));
		myApplications.click();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		WebElement tmtpLink = webDriver.findElement(By.xpath(".//*[@id='myAppsAll']/div[28]/a"));
		tmtpLink.click();
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		ArrayList<String> newTab = new ArrayList<String>(webDriver.getWindowHandles());
		
		newTab.remove(oldTab);
		
		webDriver.switchTo().window(newTab.get(0));
		
		
		
		// Goto TMTP Application Verify the Texts and other elements
		String verifyElementsHomePageScreenshotPath = GetScreenShot.capture(webDriver, "verifyElementsHomePageScreenshotPath");
		WebElement welcomeBanner = webDriver.findElement(By.xpath(".//*[@id='welcome']/div/span"));
		WebElement familyFriendsBanner = webDriver.findElement(By.xpath(".//*[@id='main']/div[4]/div[1]/span"));
		WebElement advantageBanner = webDriver.findElement(By.xpath(".//*[@id='welcome']/p[2]"));
		WebElement warningBanner = webDriver.findElement(By.xpath(".//*[@id='welcome']/b"));
		WebElement authorizedUserBanner = webDriver.findElement(By.xpath(".//*[@id='main']/div[4]/p[2]"));
		WebElement addUpto10UsersBanner = webDriver.findElement(By.xpath(".//*[@id='main']/div[4]/p[2]"));
		
		
		
		
		
		String expectedWelcomeBanner = "Welcome to Team Member Hilton Honors,";
		String expectedFamilyFriendsBanner = "Family & Friends";
		String expectedAdvantageBanner = "Take advantage of all your Team Member Hilton Honors account";
		String expectedWarningBanner1 = "If you are not";
		String expectedWarningBanner2 = "of this application, sign out of the Lobby, and sign back in with your own Lobby ID and Password.";
		String expectedAuthorizedUsersBanner1 = "As authorized users of the Family ";
		String expectedAuthorizedUsersBanner2 = "without notifying you. Consider asking them to let you know when they book, or add them before their booking and then remove them once their reservation is complete. This will help you keep track of how many Family & Friends discounted room nights are remaining from your annual allotment (30 nights at 50% off Best Available Rate).";
		String expectedAddUpto10UsersBanner1 = "You may add up to 10 authorized family and friends below who are Hilton Honors Members";
		String expectedAddUpto10UsersBanner2 = "before you add them. Be sure to enter each person’s name and Hilton Honors number exactly as they appear on their accounts.";
		
		
		String actualWelcomeBanner = welcomeBanner.getText();
		String actualFamilyFriendsBanner = familyFriendsBanner.getText();
		String actualAdvantageBanner = advantageBanner.getText();
		String actualWarningbanner = warningBanner.getText();
		String actualAuthorizedUserBanner = authorizedUserBanner.getText();
		String actualAddUpto10UsersBanner = addUpto10UsersBanner.getText();
		
		if(actualWelcomeBanner.contains(expectedWelcomeBanner)){
			test.log(LogStatus.PASS, "Verified the Welcome Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}else{
			test.log(LogStatus.FAIL, "Failed the Verification of the Welcome Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}
		
		
		if(actualFamilyFriendsBanner.contains(expectedFamilyFriendsBanner)){
			test.log(LogStatus.PASS, "Verified the Family and Friends Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}else{
			test.log(LogStatus.FAIL, "Failed the Verification of Family and Friends Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}
		
		if(actualAdvantageBanner.contains(expectedAdvantageBanner)){
			test.log(LogStatus.PASS, "Verified the Advantage Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}else{
			test.log(LogStatus.FAIL, "Failed the Verification of Advantage Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}
		
		if(actualWarningbanner.contains(expectedWarningBanner1) && actualWarningbanner.contains(expectedWarningBanner2)){
			test.log(LogStatus.PASS, "Verified the Warning Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}else{
			test.log(LogStatus.FAIL, "Failed the Verification of Warning Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}	
		
		if(actualAuthorizedUserBanner.contains(expectedAuthorizedUsersBanner1) ){
			test.log(LogStatus.PASS, "Verified the Autorization Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}else{
			test.log(LogStatus.FAIL, "Failed the Verification of Authorization Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}
		
		/*if(actualAddUpto10UsersBanner.contains(expectedAddUpto10UsersBanner1) ){
			test.log(LogStatus.PASS, "Verified the Add Upto 10 members Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}else{
			test.log(LogStatus.FAIL, "Failed the Verification of Add Upto 10 members Banner"+test.addScreenCapture(verifyElementsHomePageScreenshotPath));
		}*/
		
		
		
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
