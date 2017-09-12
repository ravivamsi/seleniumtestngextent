/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;

/**
 * @author vamsiravi
 *
 */
public class PageUtility {

	
	public String getPageTitle(WebDriver webDriver){
		return webDriver.getTitle();
	}
	
	public String getCurrentPageUrl(WebDriver webDriver){
		return webDriver.getCurrentUrl();
		
	}
	
	public String getWindowHandle(WebDriver webDriver){
		return webDriver.getWindowHandle();
	}
	
}
