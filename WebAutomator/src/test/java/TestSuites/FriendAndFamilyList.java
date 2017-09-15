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
import org.openqa.selenium.support.ui.ExpectedConditions;
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
public class FriendAndFamilyList extends base{


	PathConfiguration pathConfig = new PathConfiguration();

	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	Anesthesia anesthesia = new Anesthesia();
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports(pathConfig.getReportsDirectory()+"TMTPFriendAndFamilyListReport.html");
//		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 

	}

	@Test(enabled=false)
	public void verifyLobbyLoginGotoMyApplicationsAndTMTP() throws AWTException, IOException, InterruptedException{
		
		webDriver =initializeDriver();

		webDriver.manage().window().maximize();
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
		Thread thread = new Thread();
		test = extentReports.startTest("TMTP Friends And Family List");
		
		webDriver.get("https://lobby.hilton.com/Pages/default.aspx");
		
		// userId xpath .//*[@id='txtUserID']
		WebElement userId = webDriver.findElement(By.id("txtUserID"));
		userId.sendKeys("HRCCTest");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
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
		
		// 2nd Member
		/*Vamsi
		Ravi
		751402395*/
		friendFirstName.sendKeys("Vamsi");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Ravi");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("751402395");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ffFirstNameInput")));
		
		Anesthesia.sleep(5);
		
		// 3rd Member
	/*Sandeep
		Pola
		750910894*/
		friendFirstName.sendKeys("Sandeep");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Pola");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("750910894");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Anesthesia.sleep(5);
		
		
		// 4th Member
	/*Sandeep
		Reddy
		751406862*/
		friendFirstName.sendKeys("Sandeep");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Reddy");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("751406862");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ffFirstNameInput")));
		Anesthesia.sleep(5);
		
		
		// 5th Member
/*Vamsi
		Ravi
		751233342*/
		friendFirstName.sendKeys("Vamsi");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Ravi");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("751233342");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ffFirstNameInput")));
		
		Anesthesia.sleep(5);
		
		// 6th Member
		/*
		 * Vamsi
		Ravi
		751326727
		 */
		friendFirstName.sendKeys("Vamsi");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Ravi");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("751326727");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ffFirstNameInput")));
		Anesthesia.sleep(5);
		
		
		
		// 7th Member
		/*
		 * SaiChand
		Gopal
		751316322
		 */
		friendFirstName.sendKeys("SaiChand");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Gopal");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("751316322");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ffFirstNameInput")));
		Anesthesia.sleep(5);
		
		// 8th Member
		
		/*
		 * Sandeep
		Pola
		750494104
		 */
		friendFirstName.sendKeys("Sandeep");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Pola");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("750494104");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ffFirstNameInput")));
		Anesthesia.sleep(5);
		
		// 9th Member
		/*Akshay
		Kotla
		751221484
		*/
		friendFirstName.sendKeys("Akshay");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Kotla");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("751221484");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ffFirstNameInput")));
		Anesthesia.sleep(5);
		
		// 10th Member
		
		/*
		 * Sandeep
		Pola
		751336932
		 */
		friendFirstName.sendKeys("Sandeep");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendLastName.sendKeys("Pola");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendHHonors.sendKeys("751336932");
		
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		friendAdd.click();
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ffFirstNameInput")));
		
		Anesthesia.sleep(5);
		
		String verifyAddFriendsFamilyListScreenshotPath = GetScreenShot.capture(webDriver, "verifyAddFriendsFamilyListScreenshotPath");
		
		
		test.log(LogStatus.PASS, "Screenshot of the List"+test.addScreenCapture(verifyAddFriendsFamilyListScreenshotPath));
		
		/*	Try to Add the 11th and capture the error
		 * 
		 * /*FirstName
		LastName
		HHonorsNumber
		Vamsi
		Ravi
		751317424
		
		*/
		if(friendFirstName.isDisplayed()){
			test.log(LogStatus.FAIL, "Failed as this is not the 10th element"+test.addScreenCapture(verifyAddFriendsFamilyListScreenshotPath));
		}else{
			test.log(LogStatus.PASS, "Passed as we have all the 10 members added"+test.addScreenCapture(verifyAddFriendsFamilyListScreenshotPath));
		}
		
		
	// Remove the 10 Honor Members 
		String verifyRemoveFriendsFamilyListScreenshotPath1 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath1");
		
		WebElement removeUser = webDriver.findElement(By.linkText("Remove"));
		WebElement removeUser1 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		removeUser1.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		test.log(LogStatus.PASS, "Removed the First Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath1));
		
		String verifyRemoveFriendsFamilyListScreenshotPath2 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath2");

		WebElement removeUser2 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		
		removeUser2.click(); // //*[@id="currentFFs"]/div[1]/span[3]/a
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(LogStatus.PASS, "Removed the 2nd Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath2));
		
		String verifyRemoveFriendsFamilyListScreenshotPath3 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath3");

		WebElement removeUser3 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		
		removeUser3.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(LogStatus.PASS, "Removed the 3rd Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath3));
		
		String verifyRemoveFriendsFamilyListScreenshotPath4 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath4");
		WebElement removeUser4 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		
		removeUser4.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(LogStatus.PASS, "Removed the 4th Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath4));
		
		String verifyRemoveFriendsFamilyListScreenshotPath5 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath5");
		WebElement removeUser5 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		
		removeUser5.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(LogStatus.PASS, "Removed the 5th Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath5));
		
		String verifyRemoveFriendsFamilyListScreenshotPath6 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath6");
		WebElement removeUser6 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		
		removeUser6.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(LogStatus.PASS, "Removed the 6th Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath6));
		String verifyRemoveFriendsFamilyListScreenshotPath7 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath7");
		WebElement removeUser7 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		
		removeUser7.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(LogStatus.PASS, "Removed the 7th Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath7));
		String verifyRemoveFriendsFamilyListScreenshotPath8 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath8");
		WebElement removeUser8 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		
		removeUser8.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(LogStatus.PASS, "Removed the 8th Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath8));
		String verifyRemoveFriendsFamilyListScreenshotPath9 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath9");
		WebElement removeUser9 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		
		removeUser9.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(LogStatus.PASS, "Removed the 9th Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath9));

		String verifyRemoveFriendsFamilyListScreenshotPath10 = GetScreenShot.capture(webDriver, "verifyRemoveFriendsFamilyListScreenshotPath10");
		WebElement removeUser10 = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div[1]/span[3]/a"));
		
		removeUser10.click();
		Anesthesia.sleep(5);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		test.log(LogStatus.PASS, "Removed the 10th Member"+test.addScreenCapture(verifyRemoveFriendsFamilyListScreenshotPath10));

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
	
	
	







	
	
//	https://onqinsider.hilton.com/Insider/OnQLogin/Login.aspx
	
//	HRCCTest
	
//	July31-
	
//	UserInformation Verify xpath 	.//*[@id='zz4_Menu']
	
//	My Applications xpath 			.//*[@id='hlMenu']/li[4]/a/span[1]
	
//	Go Hilton Account				.//*[@id='myAppsAll']/div[28]/a

	
	
	

}
