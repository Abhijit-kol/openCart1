package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClassTest;
import utilities.DataProviders;

/*
 data is valid-- login successfull-test is passed  --logout
 data is valid-- login Unsuccessfull-test is failed
 
 data is invalid-- login successfull-test is failed  --logout
 data is invalid-- login Unsuccessfull-test is passed 
 
 */

class TC003_LoginDDT extends BaseClassTest {
	
	@Test(dataProvider = "loginData",dataProviderClass = DataProviders.class,groups = "Datadriven")  //if dataProvider is part of this class then dataProvider.class is not required
	public void verify_loginDDT(String email,String pwd,String expectedResult) {
		
		logger.info("*** Strating TC003_LoginDDT ***");
		
		try {
			
		HomePage hp=new HomePage(driver);
		
		//homePage
		hp.clickMyAccount();
		logger.info("clicking my account mu=y link");	
		hp.clickLogin();
		logger.info("clicking login link");
		
		//loginPage
		LoginPage lp=new LoginPage(driver);
		lp.setlogEmail(email);
		lp.setLoginPassword(pwd);
		lp.logClick();
		
		//MyAccountPage		
		MyAccountPage mAcc=new MyAccountPage(driver);
		boolean isPresent= mAcc.isMyAccountPageExists();
		
		if(isPresent==true) {
			if(expectedResult.equalsIgnoreCase("Valid")) {
				mAcc.clickLogout();
				Assert.assertTrue(true);
			}else {
				Assert.assertTrue(false);
			}
		}
		
		if(isPresent==false) {
			if(expectedResult.equalsIgnoreCase("Invalid")) {
				mAcc.clickLogout();
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(true);
			}
		}
		
		}catch (Exception e) {
			Assert.fail();
			logger.info("test is failed");
		}
		
		logger.info("*** finished TC003_LoginDDT ***");
		
	}
	
	

}
