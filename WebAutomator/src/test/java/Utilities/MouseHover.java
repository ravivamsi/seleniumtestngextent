/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * @author vamsiravi
 *
 */
public class MouseHover {

	/*
	 * The function purpose is to move the Mouse over to the Element and highlight
	 */
	public static void MouseOver(WebElement webElement, WebDriver webDriver){
		Actions actObj=new Actions(webDriver);
		actObj.moveToElement(webElement).build().perform();
		}
	
}
