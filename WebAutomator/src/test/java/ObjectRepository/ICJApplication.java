/**
 * 
 */
package ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author vamsiravi
 *
 */
public class ICJApplication {
	
	public WebElement getUserId(WebDriver webDriver){
		return webDriver.findElement(By.id("txtUserID"));
	}
	 
	public WebElement getPasswordElement(WebDriver webDriver){
		return webDriver.findElement(By.id("txtPassword"));
	}
	
	public WebElement getSignInElement(WebDriver webDriver){
		return webDriver.findElement(By.id("btnLogin"));
	}
	
	
	public WebElement getQuickSearchElement(WebDriver webDriver){
		return webDriver.findElement(By.id("ext-gen55"));
	}
	
	public WebElement getUserNavigationArea(WebDriver webDriver){
		return webDriver.findElement(By.id("userNavLabel"));
	}
	
	public WebElement getLocationNextElement(WebDriver webDriver){
		return webDriver.findElement(By.xpath("//*[@id='flowContainer']/article/div[3]/footer/div/div[2]/button"));
	}
	
	public WebElement getLogoutLink(WebDriver webDriver){
		return webDriver.findElement(By.linkText("Logout"));
	}
	
	public WebElement getSearchInQuickSearch(WebDriver webDriver){
		return webDriver.findElement(By.xpath("/html/body/div[1]/ul/li[1]/a"));
	}
	
	public WebElement getHHonorsElementInQuickSearch(WebDriver webDriver){
		return webDriver.findElement(By.id("searchHonors"));
	}
	
	public WebElement getQuickSearchGuestSearchButton(WebDriver webDriver){
		return webDriver.findElement(By.xpath("//*[@id='tab-scoped-search__guest']/fieldset/div/div[5]/button"));
	}
	
	
	public WebElement gethhonorsInGrid(WebDriver webDriver){
		return webDriver.findElement(By.xpath("//*[@id='customerList']/span[2]"));
	}
	
}
