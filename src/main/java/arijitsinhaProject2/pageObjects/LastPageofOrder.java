package arijitsinhaProject2.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinhaProject2.AbstractComponents.AbstractComponent;

public class LastPageofOrder extends AbstractComponent {
	WebDriver driver;
	public LastPageofOrder(WebDriver driver) {
		// TODO Auto-generated constructor 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(tagName="h1")
	WebElement orderTextConfirmation;
	
	public String orderConfirmation() {
		String text=orderTextConfirmation.getText().trim();
		return text;
	}
}
