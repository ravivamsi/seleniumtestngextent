/**
 * 
 */
package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author vamsiravi
 *
 */
public class DropDownSelector {
	
	/*
	 * This function can click on the drop down and select the value 
	 */
	public static void populateDropDown(WebDriver driver, WebElement webElement, String value) {

		Select select = new Select(webElement);
		select.selectByValue(value);

	}

}
