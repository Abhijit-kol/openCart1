package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[normalize-space()=\"My Account\"]")
	WebElement accountMsg;
	
	@FindBy(xpath = "//*[@id=\"column-right\"]/div/a[13]")
	WebElement logoutLink;
	
	
	public boolean isMyAccountPageExists() {
		try {
			return accountMsg.isDisplayed();
		}
		catch(Exception e){
			return false;
		}
	}
	
	public void clickLogout() {
		logoutLink.click();
	}
	
	

}
