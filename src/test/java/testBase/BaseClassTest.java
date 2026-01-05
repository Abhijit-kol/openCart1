package testBase;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;  //log4j
import org.apache.logging.log4j.Logger;  //log4j

public class BaseClassTest {
	
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups = {"Sanity","Master","Regression"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException {
		
		//loading config.properties
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		
		
		logger=LogManager.getLogger(this.getClass());  //log4j2
		
		
		switch (br.toLowerCase()) {
			case "chrome": driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			case "safari": driver= new SafariDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;

			default: System.out.println("invalid browser"); return;		
		
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.get(p.getProperty("appURL"));  //reading value from config.properties file
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups = {"Sanity","Master","Regression"})
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}
	
	
	
	@SuppressWarnings("deprecation")
	public String randomString() {
		
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
		
	}
	
	@SuppressWarnings("deprecation")
	public String randomNumber() {
		
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return generatedNumber;
		
	}
	
	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric() {
		
		String generatedString=RandomStringUtils.randomAlphabetic(3);
		String generatedNumber=RandomStringUtils.randomNumeric(3);
		return (generatedString+generatedNumber);
		
	}
	
	public String captureScreen(String tName) {
		String timeStamp=new SimpleDateFormat("yyyyMMDDhhmmss").format(new Date());
		
		TakesScreenshot takeScreenShot=(TakesScreenshot) driver;
		File sourceFile=takeScreenShot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ tName+"_"+timeStamp+".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
		
	}

}
