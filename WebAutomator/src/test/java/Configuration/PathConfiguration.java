/**
 * 
 */
package Configuration;

/**
 * @author vamsiravi
 *
 */
public class PathConfiguration {

	
	public String dataProprties = "/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/src/main/java/resources/data.properties";
	
	public String chromeDriver	= "/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/drivers/chromedriver";
	
	public  String geckoDriver = "/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/drivers/geckodriver";
	
	public String baseDirectory = "/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/";
			
	public String errorConfiguration = "/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/ErrorScreenshot/";	
	
	public String reportsDirectory = "/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/reports/";
	
	public String exetentConfigFile = "/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config";

	public String getDataProprties() {
		return dataProprties;
	}

	public void setDataProprties(String dataProprties) {
		this.dataProprties = dataProprties;
	}

	public String getChromeDriver() {
		return chromeDriver;
	}

	public void setChromeDriver(String chromeDriver) {
		this.chromeDriver = chromeDriver;
	}

	public String getGeckoDriver() {
		return geckoDriver;
	}

	public void setGeckoDriver(String geckoDriver) {
		this.geckoDriver = geckoDriver;
	}

	public String getBaseDirectory() {
		return baseDirectory;
	}

	public void setBaseDirectory(String baseDirectory) {
		this.baseDirectory = baseDirectory;
	}

	public String getErrorConfiguration() {
		return errorConfiguration;
	}

	public void setErrorConfiguration(String errorConfiguration) {
		this.errorConfiguration = errorConfiguration;
	}

	public String getReportsDirectory() {
		return reportsDirectory;
	}

	public void setReportsDirectory(String reportsDirectory) {
		this.reportsDirectory = reportsDirectory;
	}

	public String getExetentConfigFile() {
		return exetentConfigFile;
	}

	public void setExetentConfigFile(String exetentConfigFile) {
		this.exetentConfigFile = exetentConfigFile;
	}




}
