/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author vamsiravi
 *
 */
public class Frame {
	
	public static void switchToDefaultFrame(WebDriver webDriver) {
		webDriver.switchTo().defaultContent();
	}
	
	public static void switchToFrameByFrameName(WebDriver webDriver, String frameName){
		webDriver.switchTo().frame(frameName);
	}
	
	public static void switchToFrameByFrameIndex(WebDriver webDriver, Integer index){
		webDriver.switchTo().frame(index);
	}
	
	public static void switchToFrameByFrameWebElement(WebDriver webDriver, WebElement frameWebElement){
		webDriver.switchTo().frame(frameWebElement);
	}
}
