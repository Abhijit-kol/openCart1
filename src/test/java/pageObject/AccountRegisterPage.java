package pageObject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegisterPage extends BasePage {

	public AccountRegisterPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//input[@name='firstname']")
	WebElement firstName;
	
	@FindBy(xpath = "//input[@name='lastname']")
	WebElement lastname;
	
	@FindBy(xpath = "//input[@name='email']")
	WebElement email;
	
	@FindBy(xpath = "//input[@name='telephone']")
	WebElement phoneNum;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@name='confirm']")
	WebElement confirmPassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement checkBoxPolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueBtn;
	
	@FindBy(xpath = "//*[@id=\"content\"]/h1")
	WebElement confirmMag;
	
	
	public void setFirstName(String fName) {
		firstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		lastname.sendKeys(lName);
	}
	
	public void setEmail(String e) {
		email.sendKeys(e);
	}
	
	public void setPhoneNum(String pNumber) {
		phoneNum.sendKeys(pNumber);
	}
	
	public void setPassword(String pass) {
		password.sendKeys(pass);
	}
	
	public void setconfirmPassword(String confirmPass) {
		confirmPassword.sendKeys(confirmPass);
	}
	
	public void clickPolicy() {
		checkBoxPolicy.click();
	}
	
	public void clickContinue() {
		//sol1
		continueBtn.click();
		
		//sol2
//		continueBtn.submit();
		
		//sol3
//		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(8));
//		wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
		
		//sol4
//		Actions act=new Actions(driver);
//		act.moveToElement(continueBtn).click().perform();
		
		//sol 5
//		JavascriptExecutor js=(JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click()", continueBtn);
		
		
	}
	
	public String checkregistration() {
		try {
			return confirmMag.getText();
			}
		catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	
	

}
