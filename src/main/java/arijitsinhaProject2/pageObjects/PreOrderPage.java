package arijitsinhaProject2.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinhaProject2.AbstractComponents.AbstractComponent;

public class PreOrderPage extends AbstractComponent{
	WebDriver driver;
	public PreOrderPage(WebDriver driver) {
		// TODO Auto-generated constructor 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryEditBox;
	
	@FindBy(css="section[class*=list-group] button span")
	List<WebElement> buttonsOfCountry;
	
	@FindBy(css="a[class*='action__submit']")
	WebElement placeOrderButton;
	
	@FindBy(xpath="//div[contains(@class,'toast-title')]")
	WebElement preOrderErrorToast;
	
	public void preOrderSteps(String country) {
		waitForElementToAppear(countryEditBox);
		Actions a =new Actions(driver);
		a.moveToElement(countryEditBox).click().build().perform();
		a.sendKeys(countryEditBox, country).build().perform();
		//buttonsOfCountry iteration
		for(int i=0;i<buttonsOfCountry.size();i++) {
			String text=buttonsOfCountry.get(i).getText().trim();
			if(text.equalsIgnoreCase(country)) {
				ElementClick(buttonsOfCountry.get(i));
				break;
			}
		}
	}
	
	public String preOrderErrorValidation() {
		ElementClick(placeOrderButton);
		String errorMssg=preOrderErrorToast.getText().trim();
		return errorMssg;
	}
	
	public LastPageofOrder placeOrderAction() {
		ElementClick(placeOrderButton);
		return new LastPageofOrder(driver);
	}
	
}
