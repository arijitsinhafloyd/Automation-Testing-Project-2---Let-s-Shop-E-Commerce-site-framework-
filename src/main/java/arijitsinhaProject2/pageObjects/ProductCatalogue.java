package arijitsinhaProject2.pageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import arijitsinhaProject2.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> productList;
	
	By productElements=By.cssSelector(".mb-3");
	By addedToCartToast= By.xpath("//div[@aria-label='Product Added To Cart']");
	
	public List<WebElement> getProducts() {
		waitForElementToAppear(productElements);
		return productList;
	}
	
	public void addingProductsToCart(String[] products) {
		List<WebElement> productList=getProducts();
		List<String> productListExpected=Arrays.asList(products);
		int count=0;
		
		for(int i=0;i<productList.size();i++) {
			String text=productList.get(i).findElement(By.cssSelector("h5 b")).getText().trim();
			if(productListExpected.contains(text)) {
				count++;
				WebElement click=productList.get(i).findElement(By.cssSelector("button[class*='w-10']"));
				ElementClick(click);
				
				if(count==productListExpected.size())
					break;
			}
		}
	}
	
	public void afterAddingSync() {
		List<WebElement> toastList=waitForAllElementToAppear(addedToCartToast);
		waitForAllElementToDisappear(toastList);
	}
	
}
