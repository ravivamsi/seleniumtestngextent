/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;

/**
 * @author vamsiravi
 *
 */
public class Refresh {

	public static void currentPage(WebDriver webDriver){
		webDriver.navigate().refresh();
	}
	
	public static void multipleTimes(WebDriver webDriver, Integer n){
		for (int i=0; i<n; i++){
			webDriver.navigate().refresh();
			Anesthesia.sleep(1);
		}
	}
	
}
