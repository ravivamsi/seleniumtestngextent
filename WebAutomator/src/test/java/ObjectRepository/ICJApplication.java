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
	
	@FindBy(how = How.ID, using = "txtUserID")
	public WebElement loginId;
	
	@FindBy(how = How.CSS, using = "#txtPassword")
	public WebElement password; 
	
	@FindBy(how = How.ID, using = "btnLogin")
	public WebElement signInButton;

	@FindBy(how	= How.XPATH, using = "//*[@id='flowContainer']/article/div[3]/footer/div/div[2]/button")
	public WebElement nextButton;
	
	@FindBy(how = How.ID, using = "ext-gen55")
	public WebElement quickSearchButton;
	
	@FindBy(how = How.ID, using = "searchHonors")
	public WebElement hhonorsNumberElement;
	
	@FindBy(how = How.XPATH, using = "//*[@id='tab-scoped-search__guest']/fieldset/div/div[5]/button")
	public WebElement quickSearchGuestSearch; 
	
	
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
	
	public WebElement getHHonorsElementInQuickSearch(WebDriver webDriver){
		return webDriver.findElement(By.id("searchHonors"));
	}
	
	public WebElement getQuickSearchGuestSearchButton(WebDriver webDriver){
		return webDriver.findElement(By.xpath("//*[@id='tab-scoped-search__guest']/fieldset/div/div[5]/button"));
	}
	
	
	public WebElement gethhonorsInGrid(WebDriver webDriver){
		return webDriver.findElement(By.xpath("//*[@id='customerList']/span[2]"));
	}
	
	public WebElement getLoginId() {
		return loginId;
	}

	public void setLoginId(WebElement loginId) {
		this.loginId = loginId;
	}

	public WebElement getPassword() {
		return password;
	}

	public void setPassword(WebElement password) {
		this.password = password;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}

	public void setSignInButton(WebElement signInButton) {
		this.signInButton = signInButton;
	}

	public WebElement getNextButton() {
		return nextButton;
	}

	public void setNextButton(WebElement nextButton) {
		this.nextButton = nextButton;
	}

	public WebElement getQuickSearchButton() {
		return quickSearchButton;
	}

	public void setQuickSearchButton(WebElement quickSearchButton) {
		this.quickSearchButton = quickSearchButton;
	}

	public WebElement getHhonorsNumberElement() {
		return hhonorsNumberElement;
	}

	public void setHhonorsNumberElement(WebElement hhonorsNumberElement) {
		this.hhonorsNumberElement = hhonorsNumberElement;
	}

	public WebElement getQuickSearchGuestSearch() {
		return quickSearchGuestSearch;
	}

	public void setQuickSearchGuestSearch(WebElement quickSearchGuestSearch) {
		this.quickSearchGuestSearch = quickSearchGuestSearch;
	}

	
	
	
	
	
	
	
	
}
