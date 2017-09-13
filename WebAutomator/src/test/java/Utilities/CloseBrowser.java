/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;

/**
 * @author vamsiravi
 *
 */
public class CloseBrowser {

	public static void quit(WebDriver webDriver){
		webDriver.close();
	}
}
