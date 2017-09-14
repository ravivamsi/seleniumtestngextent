/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;

/**
 * @author vamsiravi
 *
 */
public class Frame {
	
	public static void switchToDefaultFrame(WebDriver webDriver) {
		webDriver.switchTo().defaultContent();
	}
}
