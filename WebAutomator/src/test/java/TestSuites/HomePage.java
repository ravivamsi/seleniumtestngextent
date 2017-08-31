package TestSuites;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.base;

public class HomePage extends base{
	
	 public static Logger log =LogManager.getLogger(base.class.getName());
	 String outputDirectory = "/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/reports/";
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
		ExtentReports extent= new ExtentReports(outputDirectory + File.separator + dateString +"HomePageTest.html", true);
		ExtentTest test;
	 
	 
	 
	 
	 @BeforeTest
	public void initialize() throws IOException
	{
		 extent.loadConfig(new File("/Users/vamsiravi/git/seleniumtestngextent/WebAutomator/extent.config")); 
		 
		 driver =initializeDriver();
		 
	}
	@Test(dataProvider="getData")
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
		}

	@Test
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
	}
	
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
		
		driver.close();
		driver=null;
		
		extent.flush();
	        extent.close();
	}

	
	@DataProvider
	public Object[][] getData()
	{
		// Row stands for how many different data types test should run
		//coloumn stands for how many values per each test
		
		// Array size is 2
		// 0,1
		Object[][] data=new Object[2][3];
		//0th row
		data[0][0]="nonrestricteduser@qw.com";
		data[0][1]="123456";
		data[0][2]="Restrcited User";
		//1st row
		data[1][0]="restricteduser@qw.com";
		data[1][1]="456788";
		data[1][2]= "Non restricted user";
		
		return data;

	}
	
}
