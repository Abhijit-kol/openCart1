package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegisterPage;
import pageObject.HomePage;
import testBase.BaseClassTest;

public class TC001_AccountRegistrationTest extends BaseClassTest {
	
	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() throws InterruptedException {
		
		
		logger.info("*** starting TC001_AccountRegistrationTest ****");	
		
		try {
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("clicked MyAcoount link");
		
		hp.clickRegister();
		logger.info("clicked Register link");
		
		AccountRegisterPage regPage=new AccountRegisterPage(driver);
		
		logger.info("Providing customer details.....");
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");  //random string generated gmail
		
		regPage.setPhoneNum(randomNumber());
		
		String pass=randomAlphaNumeric();
		regPage.setPassword(pass);
		regPage.setconfirmPassword(pass);
		
		regPage.clickPolicy();
		Thread.sleep(3000);
		regPage.clickContinue();
		
		logger.info("Validating expected message...");
		String msg= regPage.checkregistration();
		if(msg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("test failed..");
			logger.debug("Debug log...");
			Assert.assertTrue(false);
		}
		
//		Assert.assertEquals(msg, "Your Account Has Been Created!");	
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		
		logger.info("*** finished TC001_AccountRegistrationTest ****");	
		
	}
	

}
