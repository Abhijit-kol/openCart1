package testCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClassTest;

public class TC002_LoginTest extends BaseClassTest {
	
	@Test(groups = {"Sanity","Master"})
	public void  verifyLogin() {
		logger.info("***** starting TC002_LoginTest ******");
		
		try {
		HomePage hp=new HomePage(driver);
		
		//homepage
		hp.clickMyAccount();
		logger.info("clicking my account mu=y link");	
		hp.clickLogin();
		logger.info("clicking login link");
		
		//loginPage
		LoginPage lp=new LoginPage(driver);
		lp.setlogEmail(p.getProperty("email"));
		lp.setLoginPassword(p.getProperty("password"));
		lp.logClick();
		
		//MyAccountPage
		
		MyAccountPage mAcc=new MyAccountPage(driver);
		boolean isPresent= mAcc.isMyAccountPageExists();
//		assertEquals(isPresent, true,"login fail");
		Assert.assertTrue(isPresent);
		}
		catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("*****finished TC002_LoginTes ****");
		
	}
	
}
