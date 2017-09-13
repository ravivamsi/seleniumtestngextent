/**
 * 
 */
package TestSuites;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utilities.Anesthesia;

/**
 * @author vamsiravi
 *
 */
public class Methods {
	
	public void typeKeys (WebElement webElement, String input){
		 webElement.sendKeys(input);
	}
	
	public void enterUrl(WebDriver driver, String url){
		driver.get(url);
	}
	
	public void clicker(WebElement webElement){
		webElement.click();
	}

	// Use the Methods.highlightElement(webElement, driver);
	public static void highlighter(WebElement webElement, WebDriver driver){

        for (int i = 0; i <2; i++) {
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "color: yellow; border: 2px solid red;");
	            Anesthesia.sleep(1); 
	            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
            }
	}
	
	
	public boolean displayIdentifier(WebElement webElement){
		return webElement.isDisplayed();
	}
}


