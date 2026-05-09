package arijitsinhaProject2.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinhaProject2.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(xpath="//div[contains(@aria-label,'Incorrect')]")
	WebElement toastError;
	
	public ProductCatalogue loginApplication(String email1,String password1) {
		email.sendKeys(email1);
		password.sendKeys(password1);
		ElementClick(loginButton);
		return new ProductCatalogue(driver);
	}
	
	public String errorValidation() {
		String text=toastError.getText().trim();
		return text;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
