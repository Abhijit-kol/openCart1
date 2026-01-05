package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id=\"input-email\"]")
	WebElement loginEmail;
	
	@FindBy(xpath = "//input[@id=\"input-password\"]")
	WebElement loginPass;
	
	@FindBy(xpath = "//input[@value=\"Login\"]")
	WebElement loginBtn;
	
	public void setlogEmail(String email) {
		loginEmail.sendKeys(email);
	}
	
	public void setLoginPassword(String pass) {
		loginPass.sendKeys(pass);
	}
	
	public void logClick() {
		loginBtn.click();
	}

}
