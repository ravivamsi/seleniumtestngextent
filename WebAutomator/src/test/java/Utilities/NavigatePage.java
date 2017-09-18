/**
 * 
 */
package Utilities;

import java.net.URL;

import org.openqa.selenium.WebDriver;

/**
 * @author vamsiravi
 *
 */
public class NavigatePage {
	
	public static void navigateFarword(WebDriver webDriver){
		webDriver.navigate().forward();
	}

	public static void navigateBackword(WebDriver webDriver){
		webDriver.navigate().forward();
	}
	
	public static void navigateToUrlString(WebDriver webDriver, String url){
		webDriver.navigate().to(url);
	}
	
	public static void navigateToUrl(WebDriver webDriver, URL url){
		webDriver.navigate().to(url);
	}
}
