package arijitsinhaProject2.pageObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinhaProject2.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="tbody tr td:nth-child(3)")
	List<WebElement> orderProducts;
	
	By products=By.cssSelector("tbody tr td:nth-child(3)");
	
	
	public List<WebElement> gettingProdFromOrderPage() {
		waitForAllElementToAppear(products);
		return orderProducts;
	}
	
	public boolean verifyOrderProduct(String[] products) {
		List<WebElement> orderProducts=gettingProdFromOrderPage();
		List<String> expectedList=Arrays.asList(products);
		List<String> actualList=new ArrayList<String>(products.length);
		
		for(int i=0;i<orderProducts.size();i++) {
			String productText=orderProducts.get(i).getText().trim().toUpperCase();
			actualList.add(productText);
		}
		Collections.sort(actualList);
		Collections.sort(expectedList);
		return actualList.containsAll(expectedList);
	}
	
	
}
