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
import ObjectRepository.ICJApplication;
import TestData.ICJTestData;
import Utilities.Anesthesia;
import Utilities.Frame;
import Utilities.GetScreenShot;
import Utilities.Highlighter;
import resources.base;

/**
 * @author vamsiravi
 *
 */
public class ICJQuickSearch extends base{
	ICJTestData testData = new ICJTestData();
	ICJApplication icjApplicationRepository = new ICJApplication();
	PathConfiguration pathConfig = new PathConfiguration();
	
	ExtentReports extentReports;
	
	ExtentTest test;
	
	public static WebDriver webDriver;
	
	public Long timeout = 10000l;
	
	@BeforeTest
	public void init() throws IOException{
		extentReports = new ExtentReports(pathConfig.getReportsDirectory()+"ICJQuickSearchReport.html");
		extentReports.loadConfig(new File(pathConfig.getBaseDirectory()+"extent.config")); 
	}
	
	
	@Test(enabled=true)
	public void icjApplicationLoginAndVerifyQuickSearch() throws AWTException, IOException, InterruptedException{
		
		webDriver =initializeDriver();

		webDriver.manage().window().maximize();
		
		test = extentReports.startTest("ICJ Quick Search");
		
		//Get URL 
		webDriver.get(testData.getIcjstageurl());
		
		Anesthesia.sleep(5);
		
		//User ID 
		Highlighter.verifyElement(icjApplicationRepository.getUserId(webDriver),webDriver);
		icjApplicationRepository.getUserId(webDriver).sendKeys(testData.getLoginUsername());
		Anesthesia.sleep(1);
		test.log(LogStatus.PASS, "The User ID Element is verified");
		
		// Password 
		Highlighter.verifyElement(icjApplicationRepository.getPasswordElement(webDriver),webDriver);
		icjApplicationRepository.getPasswordElement(webDriver).click();
		icjApplicationRepository.getPasswordElement(webDriver).sendKeys(testData.getLoginPassword());
		Anesthesia.sleep(1);
		test.log(LogStatus.PASS, "The Password Element is verified");
		
		// Screenshot of the Lobby Login
		String lobbyLoginPageScreenshotPath = GetScreenShot.capture(webDriver, "lobbyLoginPageScreenshotPath");
		test.log(LogStatus.PASS, "The Lobby Login has ID and Password"+test.addScreenCapture(lobbyLoginPageScreenshotPath));
		
		
		// SignIn Button
		Highlighter.verifyElement(icjApplicationRepository.getSignInElement(webDriver),webDriver);
		icjApplicationRepository.getSignInElement(webDriver).click();
		Anesthesia.sleep(5);
		test.log(LogStatus.PASS, "The SignIn Element is verified");
		
		// Screenshot of the Location Next
		String loginNextPageScreenshotPath = GetScreenShot.capture(webDriver, "loginNextPageScreenshotPath");
		test.log(LogStatus.PASS, "The Lobby Login is successful"+test.addScreenCapture(loginNextPageScreenshotPath));
		
		
		// Location Next Button 
		Highlighter.verifyElement(icjApplicationRepository.getLocationNextElement(webDriver),webDriver);
		icjApplicationRepository.getLocationNextElement(webDriver).click();
		Anesthesia.sleep(5);
		test.log(LogStatus.PASS, "The Location Next Element is verified");
		
		// Check if the Login is Successful 
		String actualPageTitle = webDriver.getTitle();
		
		
		if(actualPageTitle.contains(testData.getLoginSuccessfulPageTitle())){
			String loginSuccessfulScreenShotPath = GetScreenShot.capture(webDriver, "loginSuccessfulScreenShotPath");
			test.log(LogStatus.PASS, "The Login is successful"+test.addScreenCapture(loginSuccessfulScreenShotPath));
		}else{
			String loginFailureScreenShotPath = GetScreenShot.capture(webDriver, "loginFailureScreenShotPath");
			test.log(LogStatus.FAIL, "Check the credentials: Login Failed"+test.addScreenCapture(loginFailureScreenShotPath));
		}
		
		
		// Quick Search Verify
		if(icjApplicationRepository.getQuickSearchElement(webDriver).isDisplayed()){
			String quickSearchVerifyScreenShotPath = GetScreenShot.capture(webDriver, "quickSearchVerifyScreenShotPath");
			test.log(LogStatus.PASS, "The QuickSearch is verified"+test.addScreenCapture(quickSearchVerifyScreenShotPath));
		}else{
			String quickSearchVerifyFailScreenShotPath = GetScreenShot.capture(webDriver, "quickSearchVerifyFailScreenShotPath");
			test.log(LogStatus.FAIL, "The Quick Search is not verified"+test.addScreenCapture(quickSearchVerifyFailScreenShotPath));
		}
		
		// Click Quick Search 
		Highlighter.verifyElement(icjApplicationRepository.getQuickSearchElement(webDriver),webDriver);
		icjApplicationRepository.getQuickSearchElement(webDriver).click();
		Anesthesia.sleep(1);
		
		// Switch to default frame
		Frame.switchToDefaultFrame(webDriver);
		Anesthesia.sleep(2);

		
		String quickSearchVerifyPopupScreenShotPath = GetScreenShot.capture(webDriver, "quickSearchVerifyPopupScreenShotPath");
		test.log(LogStatus.PASS, "The QuickSearch is verified"+test.addScreenCapture(quickSearchVerifyPopupScreenShotPath));
		
		// To Do 
		/*if(icjApplicationRepository.getSearchInQuickSearch(webDriver).isDisplayed()){
			String hhonorsElementInQuickSearchScreenshot = GetScreenShot.capture(webDriver, "hhonorsElementInQuickSearchScreenshot");
			test.log(LogStatus.PASS, "The QuickSearch popup is verified"+test.addScreenCapture(hhonorsElementInQuickSearchScreenshot));
			icjApplicationRepository.getHHonorsElementInQuickSearch(webDriver).click();
			icjApplicationRepository.getHHonorsElementInQuickSearch(webDriver).sendKeys(testData.gethHonorsNumber());
			Anesthesia.sleep(1);
		}else{
			String hhonorsElementInQuickSearchVerifyFailedScreenshot = GetScreenShot.capture(webDriver, "hhonorsElementInQuickSearchVerifyFailedScreenshot");
			test.log(LogStatus.PASS, "The QuickSearch popup is not verified"+test.addScreenCapture(hhonorsElementInQuickSearchVerifyFailedScreenshot));
		}*/
		
//	
//		To Do 
//		

	/*	// Quick Search popup Verify
		if(icjApplicationRepository.getHHonorsElementInQuickSearch(webDriver).isDisplayed()){
			String hhonorsElementInQuickSearchScreenshot = GetScreenShot.capture(webDriver, "hhonorsElementInQuickSearchScreenshot");
			test.log(LogStatus.PASS, "The QuickSearch popup is verified"+test.addScreenCapture(hhonorsElementInQuickSearchScreenshot));
			icjApplicationRepository.getHHonorsElementInQuickSearch(webDriver).click();
			icjApplicationRepository.getHHonorsElementInQuickSearch(webDriver).sendKeys(testData.gethHonorsNumber());
			Anesthesia.sleep(1);
		}else{
			String hhonorsElementInQuickSearchVerifyFailedScreenshot = GetScreenShot.capture(webDriver, "hhonorsElementInQuickSearchVerifyFailedScreenshot");
			test.log(LogStatus.PASS, "The QuickSearch popup is not verified"+test.addScreenCapture(hhonorsElementInQuickSearchVerifyFailedScreenshot));
		}
		
		// Search from the Quick search
		if(icjApplicationRepository.getQuickSearchGuestSearchButton(webDriver).isDisplayed()){
			icjApplicationRepository.getQuickSearchGuestSearchButton(webDriver).click();
			Anesthesia.sleep(2);
			String searchButtonInQuickSearchScreenshotPath = GetScreenShot.capture(webDriver, "searchButtonInQuickSearchScreenshotPath");
			test.log(LogStatus.PASS, "The QuickSearch Search is about to be clicked"+test.addScreenCapture(searchButtonInQuickSearchScreenshotPath));
		}else{
			String searchButtonInQuickSearchScreenshotPath = GetScreenShot.capture(webDriver, "searchButtonInQuickSearchScreenshotPath");
			test.log(LogStatus.PASS, "The QuickSearch Search Guest is not verified"+test.addScreenCapture(searchButtonInQuickSearchScreenshotPath));
		}
		
		// Switch to default frame
		Frame.switchToDefaultFrame(webDriver);
		Anesthesia.sleep(2);
		
		
		// Verify HHnonors Number in the Results
		if(icjApplicationRepository.getHHonorsElementInQuickSearch(webDriver).isDisplayed()){
			String responseHHonorsNumber = icjApplicationRepository.getQuickSearchGuestSearchButton(webDriver).getText();
			if(responseHHonorsNumber.equalsIgnoreCase(testData.gethHonorsNumber())){
				String hhonorsNumberVerification = GetScreenShot.capture(webDriver, "hhonorsNumberVerification");
				test.log(LogStatus.PASS, "The QuickSearch Search is about to be clicked"+test.addScreenCapture(hhonorsNumberVerification));
			}else{
				String hhonorsNumberVerificationFailed = GetScreenShot.capture(webDriver, "hhonorsNumberVerificationFailed");
				test.log(LogStatus.PASS, "The QuickSearch Search is about to be clicked"+test.addScreenCapture(hhonorsNumberVerificationFailed));
			}
			Anesthesia.sleep(2);
			String searchButtonInQuickSearchScreenshotPath = GetScreenShot.capture(webDriver, "searchButtonInQuickSearchScreenshotPath");
			test.log(LogStatus.PASS, "The QuickSearch Search is about to be clicked"+test.addScreenCapture(searchButtonInQuickSearchScreenshotPath));
		}else{
			String searchButtonInQuickSearchScreenshotPath = GetScreenShot.capture(webDriver, "searchButtonInQuickSearchScreenshotPath");
			test.log(LogStatus.PASS, "The QuickSearch Search Guest is not verified"+test.addScreenCapture(searchButtonInQuickSearchScreenshotPath));
		}
*/
		
		
		Highlighter.verifyElement(icjApplicationRepository.getUserNavigationArea(webDriver),webDriver);
		icjApplicationRepository.getUserNavigationArea(webDriver).click();
		Anesthesia.sleep(1);
		
		Highlighter.verifyElement(icjApplicationRepository.getLogoutLink(webDriver),webDriver);
		icjApplicationRepository.getLogoutLink(webDriver).click();
		Anesthesia.sleep(2);
		
		String icjLogoutVerificationScreenshot = GetScreenShot.capture(webDriver, "icjLogoutVerificationScreenshot");
		test.log(LogStatus.PASS, "The Logout is verified"+test.addScreenCapture(icjLogoutVerificationScreenshot));
		
		Utilities.CloseBrowser.quit(webDriver);
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
	
	

	/*
	 * 
		https://fdstg.hilton.com/idp/startSSO.ping?PartnerSpId=https://contact-center--icjtest.cs30.my.salesforce.com/
	*/
	

	/*
	 * 
			// Check HHonors Number Element is present
			if(icjApplicationRepository.getHhonorsNumberElement().isDisplayed()){
				String quickSearchHHonorsScreenShotPath = GetScreenShot.capture(webDriver, "quickSearchHHonorsScreenShotPath");
				test.log(LogStatus.PASS, "The Login is successful"+test.addScreenCapture(quickSearchHHonorsScreenShotPath));
			}else{
				String quickSearcFailhHHonorsScreenShotPath = GetScreenShot.capture(webDriver, "quickSearcFailhHHonorsScreenShotPath");
				test.log(LogStatus.PASS, "The Login is successful"+test.addScreenCapture(quickSearcFailhHHonorsScreenShotPath));
			}
			
			icjApplicationRepository.getHhonorsNumberElement().click();
			Anesthesia.sleep(1);
			icjApplicationRepository.getHhonorsNumberElement().sendKeys(testData.gethHonorsNumber());
			Anesthesia.sleep(1);
			
			icjApplicationRepository.getQuickSearchGuestSearch().click();
			Anesthesia.sleep(2);
		
		*
		*
		*/


}
