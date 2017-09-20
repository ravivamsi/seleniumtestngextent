package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import Configuration.PathConfiguration;

public class base {

	public static WebDriver driver;
	public Properties prop;
	
	PathConfiguration pathConfig = new PathConfiguration();
public WebDriver initializeDriver() throws IOException
{
	
 prop= new Properties();
FileInputStream fis=new FileInputStream(pathConfig.dataProprties);

prop.load(fis);
String browserName=prop.getProperty("browser");
System.out.println(browserName);

if(browserName.equals("chrome"))
{
	System.setProperty("webdriver.chrome.driver", pathConfig.chromeDriver);
	driver= new ChromeDriver();
	driver.manage().window().maximize();
		//execute in chrome driver
	
}else if (browserName.equals("firefox")){
	
	
	System.setProperty("webdriver.gecko.driver", pathConfig.getGeckoDriver());
	driver= new FirefoxDriver();
	//firefox code
	 
}else if (browserName.equals("ie")){
//	IE code
//	System.setProperty("webdriver.ie.driver", pathConfig.ieDriver);
//	driver = new InternetExplorerDriver();
//	driver.manage().window().maximize();
}else if(browserName.equals("headless")){
	// Initialize the Headless Browser
	System.setProperty("phantomjs.binary.path", pathConfig.getPhanthomDriver());
	driver = new PhantomJSDriver();
}

return driver;


}

public void getScreenshot(String result) throws IOException
{
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File(pathConfig.baseDirectory+result+"screenshot.png"));
	
}


}
