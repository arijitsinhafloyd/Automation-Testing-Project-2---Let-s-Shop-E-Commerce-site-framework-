package arijitsinhaProject2.pageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinhaProject2.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartItems;
	
	@FindBy(css="li[class='totalRow'] button")
	WebElement checkOutButton;
	
	public boolean checkingCart(String[] products) {
		List<String> listOfcartItems=new ArrayList<String>();
		List<String> expList=Arrays.asList(products);
		boolean result=false;
		for(int i=0;i<cartItems.size();i++) {
			String cartProducts=cartItems.get(i).getText().trim();
			listOfcartItems.add(cartProducts);
			Collections.sort(listOfcartItems);
			Collections.sort(expList);
			if(expList.equals(listOfcartItems)) {
				result=true;
				break;
				
			}
		}
		return result;
	}
	
	public PreOrderPage checkingOut() {
		ElementClick(checkOutButton);
		return new PreOrderPage(driver);
	}
	

}
