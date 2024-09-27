package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.logger.Log;
import com.qa.saucedemo.util.ElementUtil;
import com.qa.saucedemo.util.TimeUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By userName = By.id("user-name");
	private By password = By.id("password");
	private By loginButton = By.id("login-button");
	
	public LoginPage (WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		//System.out.println("login page title : " + title);
		Log.info("Login page title : " + title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL, TimeUtil.DEFAULT_MEDIUM_TIME);
		Log.info("login page url : " + url);
		return url;
	}
	
	public InventoryPage doLogin(String username, String pwd) {
		Log.info("user creds: " + username + " : " + pwd);
		eleUtil.waitForElementVisible(userName, TimeUtil.DEFAULT_LONG_TIME).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		TimeUtil.mediumTime();
		return new InventoryPage(driver);
	}
	

}
