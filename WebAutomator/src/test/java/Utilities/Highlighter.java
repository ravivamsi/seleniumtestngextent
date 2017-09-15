/**
 * 
 */
package Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * @author vamsiravi
 *
 */
public class Highlighter {
	
	public static void verifyElement(WebElement webElement, WebDriver driver){
		for (int i = 0; i <2; i++) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "color: yellow; border: 2px solid red;");
	            Anesthesia.sleep(1); 
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
            }
	}
	
	public static void verifyElementAndGetScreenshot(WebElement webElement, WebDriver driver, String screenShotPath, ExtentTest test, String webElementName){
		 for (int i = 0; i <2; i++) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "color: yellow; border: 2px solid red;");
	            Anesthesia.sleep(1); 
	            // Screenshot Code
	            test.log(LogStatus.INFO, "Verified and Highlighted the Element "+ webElementName + test.addScreenCapture(screenShotPath));
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
         }
	}

}
