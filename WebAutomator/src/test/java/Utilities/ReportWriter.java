/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author vamsiravi
 *
 */
public class ReportWriter {
	
	public static void verifyandUpdateReport(WebDriver webDriver, WebElement webElement, String webElementName, String screenshotPath, ExtentTest test){	
		if(webElement.isDisplayed()){
			test.log(LogStatus.PASS, "Element " +webElementName+" is Verified"+test.addScreenCapture(screenshotPath));
		}else{
			test.log(LogStatus.FAIL, "Element " +webElementName+" is not Verified"+test.addScreenCapture(screenshotPath));
		}
		
	}
}
