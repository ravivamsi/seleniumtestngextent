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
public class Highlighter {
	
	public static void verifyElement(WebElement webElement, WebDriver driver){
    for (int i = 0; i <2; i++) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "color: yellow; border: 2px solid red;");
	            Anesthesia.sleep(1); 
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
            }
	}

}
