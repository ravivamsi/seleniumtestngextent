/**
 * 
 */
package TestSuites;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
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
		extentReports = new ExtentReports("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/reports/TMTPLoginReport.html");
		extentReports.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
		 
		
	
	}

	@Test(enabled=false)
	public void tmtpSignIn() throws AWTException, IOException{
		
		webDriver =initializeDriver();

		webDriver.manage().window().maximize();
		
		test = extentReports.startTest("TMTP Sign In");
		
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


// Waste Work or  Information Dump

/*//		Robot robot = new Robot();
Actions action = new Actions(webDriver);
webDriver.get("https://secure3-stg.hilton.com/en/hh/customer/login/index.htm");
//webDriver.get("https://secure3-stg.hilton.com/en/hh/customer/join/joinHHonors.htm");
webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

//webDriver.findElement(By.xpath("//*[@id="formSignIn"]/p[1]/label"));

WebElement userName = webDriver.findElement(By.id("username"));

userName.sendKeys("053902496");
webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

WebElement password = webDriver.findElement(By.id("password"));

password.sendKeys("Hilton@1234");
webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

//password.sendKeys(Keys.TAB);

WebElement signInButton = webDriver.findElement(By.xpath(".//*[@id='formSignIn']/p[5]/a/span"));


WebElement frame = webDriver.findElement(By.xpath(".//iframe[@title='recaptcha widget']"));

webDriver.switchTo().frame(frame);

webDriver.findElement(By.xpath(".//*[@id='recaptcha-anchor']/div[5]")).click();
webDriver.switchTo().defaultContent();



//WebElement captcha = webDriver.findElement(By.id("recaptcha-anchor"));

//captcha.click();
//action.click();

password.sendKeys(Keys.ENTER);

WebElement captcha = webDriver.findElement(By.className("recaptcha-checkbox-checkmark"));
//xpath(".//*[@id='recaptcha-anchor']/div[5]"));

captcha.click();
robot.keyPress(KeyEvent.VK_TAB);
robot.keyRelease(KeyEvent.VK_TAB);
webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

robot.mousePress(InputEvent.BUTTON1_MASK);
robot.mouseRelease(InputEvent.BUTTON1_MASK);



webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


signInButton.click();
webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
*/

/*@Test
public void anotherTest() throws InterruptedException{
	test = extentReports.startTest("ICJ Stage");
	
	
	if(webDriver.findElement(By.className("txtPassword")).isSelected()){
		test.log(LogStatus.PASS, "Password Textbox is selected");
		webDriver.findElement(By.className("txtPassword")).sendKeys("Hilton@77");
		System.out.println("Password is set");
	}else{
		test.log(LogStatus.FAIL, "Password Textbox is missing");
	}
	
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
	
	String title = webDriver.getTitle();
	Assert.assertEquals("New Reservation - Console", title);
	test.log(LogStatus.PASS, "Test Passed as titles are  equal");
	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	webDriver.findElement(By.id("ext-gen55")).click();
	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	webDriver.findElement(By.xpath("/html/body/div[1]/ul/li[3]/a")).click();
	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	WebElement reservationConfirmationTextBox = webDriver.findElement(By.id("resNum"));
	reservationConfirmationTextBox.sendKeys("12345678");
	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	reservationConfirmationTextBox.sendKeys(Keys.TAB);
	reservationConfirmationTextBox.sendKeys(Keys.ENTER);
	webDriver.findElement(By.id("userNavLabel")).click();
	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	webDriver.findElement(By.id("app_logout")).click();
	webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	
}*/


/*@Test
public void captureScreenShot(){
	test = extentReports.startTest("Capture Screenshot");
	//webDriver = new FirefoxDriver();
	webDriver.get("http://www.automationtesting.in");
	String title = webDriver.getTitle();
	Assert.assertEquals("Home - Automation Test", title);
	test.log(LogStatus.PASS, "Test Passed as titles are equal");
}*/


//WebElement friendRemove = webDriver.findElement(By.id("ffFirstNameInput"));

//First Name
//.//*[@id='ffFirstNameInput']

//Last Name
//.//*[@id='ffLastNameInput']
//Honors Input
//.//*[@id='ffHHonorsInput']
//Add
//.//*[@id='addFFLink']
//Remove	
//.//*[@id='currentFFs']/div/span[3]/a



/*@Test
public void tmtpAddFriend() throws AWTException, IOException{
	test = extentReports.startTest("TMTP Add Member");
	
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
	
	
//	Verification
	String currentFriendScreenshotPath = GetScreenShot.capture(webDriver, "currentFriendScreenshotPath");
	
	WebElement currentUser = webDriver.findElement(By.xpath(".//*[@id='currentFFs']/div/span[1]"));
	if(currentUser.getText().contains("MANOO")){
		test.log(LogStatus.PASS, "Verified the Current Friend or Family"+test.addScreenCapture(currentFriendScreenshotPath));
	}else{
		test.log(LogStatus.FAIL, "Failed to verify the Current Friend or Family"+test.addScreenCapture(currentFriendScreenshotPath));
		
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
	
}*/
