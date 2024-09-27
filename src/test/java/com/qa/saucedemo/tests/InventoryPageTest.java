package com.qa.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.pages.InventoryPage;

public class InventoryPageTest extends BaseTest{
	
	@BeforeClass
	public void infoPageSetup() {
		inventoryPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
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
		String productName= inventoryPage.getProductItemName();
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
		String productName= inventoryPage.getProductItemPrice();
		Assert.assertEquals(productName, actualproductItemPrice);
	}
	
	
	//Cart size
	@DataProvider
	public Object[][] getactualproductItemCartSize() {
		return new Object[][] {
			{"1"}
		};
	}
	@Test(dataProvider = "getactualproductItemCartSize", priority = 3)
	public void productItemCartSizeTest(String actualproductItemCartSize) {
		String cartSize= inventoryPage.getCartSize();
		Assert.assertEquals(cartSize, actualproductItemCartSize);
	}
	
	@Test(priority = 4)
	public void goToCartTest() {
		cartPage = inventoryPage.doGoToCart();
		Assert.assertEquals(cartPage.getCartPageTitle(), AppConstants.CART_PAGE_TITLE);
	}

}
