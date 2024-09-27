package com.qa.saucedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.saucedemo.base.BaseTest;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.errors.AppError;

public class LoginPageTest extends BaseTest {
	
	@Test()
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE, AppError.TITLE_NOT_FOUND);
	}
	
	@Test()
	public void loginPageURLTest() {
		String actURL = loginPage.getLoginPageURL();
		Assert.assertEquals(actURL,AppConstants.LOGIN_PAGE_URL, AppError.URL_NOT_FOUND);
	}
	
	@Test()
	public void loginTest() {
		inventoryPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(inventoryPage.getInventoryPageTitle(), AppConstants.INVENTORY_PAGE_TITLE);
	}
	

}
