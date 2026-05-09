package arijitsinhaProject2.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import arijitsinhaProject2.TestComponents.BaseTest;
import arijitsinhaProject2.TestComponents.Retry;
import arijitsinhaProject2.pageObjects.CartPage;
import arijitsinhaProject2.pageObjects.PreOrderPage;
import arijitsinhaProject2.pageObjects.ProductCatalogue;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"Run"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() {
		lp.loginApplication("vndkvneok@gmail.com", "vndkvneok@gmail.com");
		String errorMessage=lp.errorValidation();
		Assert.assertEquals(errorMessage,"Incorrect email or password.");
	}
	
	@Test(retryAnalyzer=Retry.class)
	public void ProductErrorValidation() {
		String[] products= {"ZARA COAT 3","ADIDAS ORIGINAL"};
		
		
		//Login flow
		ProductCatalogue pc=lp.loginApplication("contact@arijitsinha.com","Asdf@1234");
		
		//Products add to cart
		pc.addingProductsToCart(products);
		pc.afterAddingSync();
		
		//Cart Page Flow
		CartPage cp=pc.goToCart();
		boolean result=cp.checkingCart(products);
		Assert.assertTrue(result,"The actual list is not matched with expected");
		PreOrderPage po=cp.checkingOut();
		String errorMssg=po.preOrderErrorValidation();
		Assert.assertEquals(errorMssg,"Please Enter Full Shipping Information");
	}
}
