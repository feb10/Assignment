package com.qa.saucedemo.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.util.ElementUtil;

public class CartPageTest extends BaseTest{
	
	@BeforeClass
	public void infoPageSetup() {
		inventoryPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		inventoryPage.getCartSize();
		cartPage=inventoryPage.doGoToCart();
		
	}
	
	
	//Item name
	@DataProvider
	public Object[][] getactualproductItemName() {
		return new Object[][] {
			{"Sauce Labs Backpack"}
		};
	}
	@Test(dataProvider = "getactualproductItemName",priority = 1)
	public void productItemNameTest(String actualproductItemName) {
		String productName= cartPage.getProductItemName();
		Assert.assertEquals(productName, actualproductItemName);
	}
	
	
	//Item Price
	@DataProvider
	public Object[][] getactualproductItemPrice() {
		return new Object[][] {
			{"$29.99"}
		};
	}
	@Test(dataProvider = "getactualproductItemPrice", priority = 2)
	public void productItemPriceTest(String actualproductItemPrice) {
		String productName= cartPage.getProductItemPrice();
		Assert.assertEquals(productName, actualproductItemPrice);
	}
	
	@Test(priority = 3)
	public void doLogoutTest() {
		loginPage = cartPage.getLogout();
		Assert.assertEquals(loginPage.getLoginPageTitle(), AppConstants.LOGIN_PAGE_TITLE);
	}
	

}
