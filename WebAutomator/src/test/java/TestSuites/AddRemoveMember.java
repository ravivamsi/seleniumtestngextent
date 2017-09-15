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
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class AddRemoveMember extends base{

	PathConfiguration pathConfig = new PathConfiguration();

	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	Anesthesia anesthesia = new Anesthesia();
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports(pathConfig.getReportsDirectory()+"TMTPAddRemoveMemberReport.html");
//		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
	
	}


	

	@Test(enabled=false)
	public void verifyAddAndRemoveMember() throws AWTException, IOException, InterruptedException{
		
		webDriver =initializeDriver();

		webDriver.manage().window().maximize();
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
		Thread thread = new Thread();
		test = extentReports.startTest("TMTP Add and Remove Member");
		
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
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		ArrayList<String> newTab = new ArrayList<String>(webDriver.getWindowHandles());
		
		newTab.remove(oldTab);
		
		webDriver.switchTo().window(newTab.get(0));
		
		Anesthesia.sleep(5);
		
		// Add the 10 Honor Members
		
		WebElement friendFirstName = webDriver.findElement(By.id("ffFirstNameInput"));
		WebElement friendLastName = webDriver.findElement(By.id("ffLastNameInput"));
		WebElement friendHHonors = webDriver.findElement(By.id("ffHHonorsInput"));
		WebElement friendAdd = webDriver.findElement(By.linkText("Add"));
//		WebElement friendAddXpath = webDriver.findElement(By.xpath(".//*[@id='addFFLink']"));
		
		
//		1st Member
		friendFirstName.sendKeys("Vamsi");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Ravi");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("751317424");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Anesthesia.sleep(5);
		
		
		String verifyAddFriendsFamilyListScreenshotPath = GetScreenShot.capture(webDriver, "verifyAddFriendsFamilyListScreenshotPath");
		
		
		test.log(LogStatus.PASS, "Screenshot of the List"+test.addScreenCapture(verifyAddFriendsFamilyListScreenshotPath));
		
		

		String verifyRemoveFriendsFamilyListScreenshotPath1 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath1");
		
		WebElement removeUser1 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		removeUser1.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		test.log(LogStatus.PASS, "Removed the First Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath1));
		

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
//			webDriver.close();
			extentReports.flush();
			extentReports.close();
	}

}
