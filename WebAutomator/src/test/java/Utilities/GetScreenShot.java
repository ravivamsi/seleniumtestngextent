/**
 * 
 */
package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Configuration.PathConfiguration;

/**
 * @author vamsiravi
 *
 */
public class GetScreenShot {
	
	public static String capture(WebDriver driver, String screenshotName) throws IOException{
		
		PathConfiguration pathConfig = new PathConfiguration();
		
		
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = pathConfig.errorConfiguration+screenshotName+".png";
		
		File permanentDestination = new File(destination);
		FileUtils.copyFile(source, permanentDestination);
		return destination;
	}

}
