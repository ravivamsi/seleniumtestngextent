/**
 * 
 */
package ObjectRepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author vamsiravi
 *
 */
public class ICJApplication {
	
	@FindBy(how = How.ID, using = "loginId")
	public WebElement loginId;
	
	@FindBy(how = How.XPATH, using = "//.xpath ")
	public WebElement password; 
	
	@FindBy(how = How.CLASS_NAME, using = "class_name")
	public WebElement signInButton;

	
	// Getters and Setters
	
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

	
	
	
	
	
	
	
	
}
