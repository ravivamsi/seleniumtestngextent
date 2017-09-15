/**
 * 
 */
package Configuration;

/**
 * @author vamsiravi
 *
 */
public class PathConfiguration {

	// Change to forward slash for MAC or Linux , Change to backward slash for Windows Based as in the above	
	// MAC or Linux 
	public String baseDirectory = "/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/";
 
	public String dataProprties = baseDirectory+"src/main/java/resources/data.properties";
	
	public String chromeDriver	= baseDirectory+"drivers/chromedriver";
	
	public String phanthomDriver = baseDirectory+"drivers/phantomjs";
	
	public  String geckoDriver = baseDirectory+"drivers/geckodriver";
	
	public String errorConfiguration = baseDirectory+"ErrorScreenshot/";	
	
	public String reportsDirectory = baseDirectory+"reports/";
	
	// Windows - Comment the Above paths and uncomment the below paths
	
	/*
	 * 
		public String baseDirectory = "C:\\git\\WebAutomator\\";
	 
		public String dataProprties = baseDirectory+"src\\main\\java\\resources\\data.properties";
		
		public String chromeDriver	= baseDirectory+"drivers\\chromedriver.exe";
		
		public String phanthomDriver = baseDirectory+"drivers\\phantomjs.exe";
		
		public  String geckoDriver = baseDirectory+"drivers\\geckodriver.exe";
		
		public String errorConfiguration = baseDirectory+"ErrorScreenshot\\";	
		
		public String reportsDirectory = baseDirectory+"reports\\";
		
		*
		*/
	
	// Config File for the extent is the same for both Windows and Linux.
	public String exetentConfigFile = baseDirectory+"extent.config";

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

	public String getPhanthomDriver() {
		return phanthomDriver;
	}

	public void setPhanthomDriver(String phanthomDriver) {
		this.phanthomDriver = phanthomDriver;
	}

}
