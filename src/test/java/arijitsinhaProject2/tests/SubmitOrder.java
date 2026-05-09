package arijitsinhaProject2.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import arijitsinhaProject2.TestComponents.BaseTest;
import arijitsinhaProject2.TestComponents.Retry;
import arijitsinhaProject2.pageObjects.CartPage;
import arijitsinhaProject2.pageObjects.LandingPage;
import arijitsinhaProject2.pageObjects.LastPageofOrder;
import arijitsinhaProject2.pageObjects.OrderPage;
import arijitsinhaProject2.pageObjects.PreOrderPage;
import arijitsinhaProject2.pageObjects.ProductCatalogue;

public class SubmitOrder extends BaseTest{
	@Test(groups= {"Run"}, dataProvider="getData",retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String,String> hm) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String[] products= {hm.get("product1"),hm.get("product2")};
		String country="india";
		
		//Login flow
		ProductCatalogue pc=lp.loginApplication(hm.get("email"),hm.get("password"));
		
		//Products add to cart
		
		pc.addingProductsToCart(products);
		pc.afterAddingSync();
		
		//Cart Page Flow
		CartPage cp=pc.goToCart();
		boolean result=cp.checkingCart(products);
		Assert.assertTrue(result,"The actual list is not matched with expected");
		PreOrderPage po=cp.checkingOut();
		
		//Preorder page
		po.preOrderSteps(country);
		LastPageofOrder lop=po.placeOrderAction();
		
		//OrderConfirmationText
		String orderText=lop.orderConfirmation();
		Assert.assertTrue(orderText.equalsIgnoreCase("THANKYOU FOR THE ORDER."),"Not matched");
		
		
	}
	
	@Test(dataProvider="getData", dependsOnMethods="submitOrder")
	public void orderVerification(HashMap<String,String> input) throws InterruptedException {
		String[] products= {input.get("product1"),input.get("product2")};
		ProductCatalogue pc=lp.loginApplication(input.get("email"),input.get("password"));
		OrderPage op=pc.goToOrderPage();
		boolean status=op.verifyOrderProduct(products);
		Assert.assertTrue(status,"The order is not same as the product selected");
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		//return new Object[][] {{"contact@arijitsinha.com","Asdf@1234",new String[]{"ZARA COAT 3","ADIDAS ORIGINAL"}},
			//{"alpiak@gmail.com","Asdf@1234",new String[] {"ZARA COAT 3","IPHONE 13 PRO"}}};
		List<HashMap<String,String>> data=jsonData();
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
