/**
 * 
 */
package Utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author vamsiravi
 *
 */
public class JavaScriptClicker {

	
	/*
	 * Use if this function is to click the Elements using Java Script
	 */
	public static void clickElementUsingJavaScript(WebElement element, WebDriver webDriver) {
		JavascriptExecutor executor = (JavascriptExecutor) webDriver;
		executor.executeScript("arguments[0].click();", element);
	}
}
