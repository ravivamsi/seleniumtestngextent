/**
 * 
 */
package TestData;

/**
 * @author vamsiravi
 *
 */
public class ICJTestData {
	
	// ICJ Urls 
	public String icjstageurl = "https://fdstg.hilton.com/idp/startSSO.ping?PartnerSpId=https://contact-center--icjtest.cs30.my.salesforce.com/";

	public String icjtesturl = "https://fdstg.hilton.com/idp/startSSO.ping?PartnerSpId=https://contact-center--icjstg.cs30.my.salesforce.com/";

	//	Lobby Login Username	- 	amath1		| bmehta		| vbalasa	|nkumar3
	public String loginUsername = "nkumar3";
	
	// Lobby Login Password		-	
	public String loginPassword = "Hilton@77";

	public String loginSuccessfulPageTitle = "Reservation";
	
	public String hHonorsNumber = "405654312";
	
	/*
	 * getters and setters
	 * 
	 */
	
	public String getIcjstageurl() {
		return icjstageurl;
	}

	public void setIcjstageurl(String icjstageurl) {
		this.icjstageurl = icjstageurl;
	}

	public String getIcjtesturl() {
		return icjtesturl;
	}

	public void setIcjtesturl(String icjtesturl) {
		this.icjtesturl = icjtesturl;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getLoginSuccessfulPageTitle() {
		return loginSuccessfulPageTitle;
	}

	public void setLoginSuccessfulPageTitle(String loginSuccessfulPageTitle) {
		this.loginSuccessfulPageTitle = loginSuccessfulPageTitle;
	}

	public String gethHonorsNumber() {
		return hHonorsNumber;
	}

	public void sethHonorsNumber(String hHonorsNumber) {
		this.hHonorsNumber = hHonorsNumber;
	}
	
	
	
	
}
