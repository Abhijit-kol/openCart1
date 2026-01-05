package utilities;


import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceBaseResolver;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClassTest;

public class ExtentReportManager implements ITestListener  {
	
	public ExtentSparkReporter sparkReport;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		/*
		SimpleDateFormat df=new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		Date dt=new Date();
		String currentDateTieFormat=df.format(dt);
		*/
		
		String timeStamp=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		repName="Test Report-"+timeStamp+".html";
		sparkReport=new ExtentSparkReporter(".\\reports\\"+repName);   //specify location
		
		sparkReport.config().setDocumentTitle("Openkart Automation Report");
		sparkReport.config().setReportName("OpenCart functional testing ");
		sparkReport.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReport);
		extent.setSystemInfo("Application","OpenCart");
		extent.setSystemInfo("Module","Admin");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("UserName",System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());  //to display groups in repName;
		test.log(Status.PASS,result.getName()+"Successfully Executed");
	}
	
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); 
		
		test.log(Status.FAIL,result.getName()+"Failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgPath=new BaseClassTest().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); 	
		test.log(Status.SKIP,result.getName()+"Skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());	
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
		
		//this will open report automatically
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		//send email to the team
		
		try {
			@SuppressWarnings("deprecation")
			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
			
			//create the email message
			ImageHtmlEmail email=new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googleemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("testbanerjee1@gmail.com","banerjee@123" ));
			email.setSSLOnConnect(true);
			email.setFrom("testbanerjee1@gmail.com");
			email.setSubject("Test Report");
			email.setMsg("please find the attachment..");
			email.addTo("abhijitbanerjee2@gmail.com");
			email.attach(url, "extent report","please chech report");
			email.send();	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
 
}
