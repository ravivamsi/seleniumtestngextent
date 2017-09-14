package TestSuites;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Configuration.PathConfiguration;
import TestData.Credentials;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class HomePage extends base{
	
	PathConfiguration pathConfig = new PathConfiguration();
	Credentials credentials = new Credentials();
	 public static Logger log =LogManager.getLogger(base.class.getName());
	 String outputDirectory = pathConfig.reportsDirectory;
		/*List<XmlSuite> xmlSuites = null;
		List<ISuite> suites = null;
		reporter.generateReport(xmlSuites, suites, outputDirectory);*/
		
	public String dateStringConverter(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		 LocalDateTime now = LocalDateTime.now();
		 System.out.println(dtf.format(now).toString());
		 return dtf.format(now).toString();
	}
	 
	 
	 
	 String dateString = dateStringConverter();
	 
// To get the date functions added to the Test if needed	 
//		ExtentReports extent= new ExtentReports(outputDirectory + File.separator + dateString +"HomePageTest.html", true);
	
	 
		ExtentReports extent= new ExtentReports(outputDirectory +"HomePageTest.html", true);
	 ExtentTest test;
	 
	 
	 
	 
	 @BeforeTest
	public void initialize() throws IOException
	{
		 extent.loadConfig(new File(pathConfig.exetentConfigFile)); 
		 
//		 driver =initializeDriver();
		 
	}
	/*@Test(dataProvider="getData")
	public void basePageNavigation(String Username,String Password,String text) throws IOException
	{

		test=extent.startTest("BasePageNavigation");
		//one is inheritance

		// creating object to that class and invoke methods of it
		driver.get(prop.getProperty("url"));
		LandingPage l=new LandingPage(driver);
		l.getLogin().click(); //driver.findElement(By.css()
		LoginPage lp=new LoginPage(driver);
		lp.getEmail().sendKeys(Username);
		lp.getPassword().sendKeys(Password);

		log.info(text);
		
		lp.getLogin().click();
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Assert Pass as condition is True");
		}*/
	
	/*@Test
	public void amazonWebpageTesting() throws InterruptedException{
		test=extent.startTest("Amazon Webpage Testing");
		
		driver.get(prop.getProperty("amazonurl"));
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mazda 6");
		driver.wait(1000);
		
	}*/
	
/*	@Test
	public void demoReportPass(){
		test=extent.startTest("DemoReportPass");
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Assert Pass as condition is True");
		
		
	}
	
	@Test
	public void demoReportFail(){
		test=extent.startTest("DemoReportFail");
		Assert.assertTrue(false);
		test.log(LogStatus.FAIL, "Assert Fail as condition is False");
	}*/
	
	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus()==ITestResult.FAILURE){
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		extent.endTest(test);
	}
	
	@AfterTest
	public void teardown()
	{
		
//		driver.close();
//		driver=null;
		
		extent.flush();
	        extent.close();
	}

	
	@DataProvider
	public Object[][] getData()
	{
		
		Object[][] data= credentials.getCredentials();
		
		return data;

	}
	
}
