package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.logger.Log;
import com.qa.saucedemo.util.ElementUtil;
import com.qa.saucedemo.util.TimeUtil;

public class CartPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productItemNameCart = By.xpath("//div[@class=\"cart_item\"][1]//div[@class=\"inventory_item_name\"]");
	private By productItemPriceCart = By.xpath("//div[@class=\"cart_item\"][1]//div[@class=\"inventory_item_price\"]");
	private By menuButton = By.id("react-burger-menu-btn");
	private By logoutButton = By.id("logout_sidebar_link");
	
	public CartPage (WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	public String getCartPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.CART_PAGE_TITLE, 5);
		Log.info("Acc page title : " + title);
		return title;
	}
	
	public String getProductItemName() {
		String itemName = eleUtil.doGetElementText(productItemNameCart);
		Log.info(itemName);
		return itemName;
	}
	
	public String getProductItemPrice() {
		String itemPrice = eleUtil.doGetElementText(productItemPriceCart);
		Log.info(itemPrice);
		return itemPrice;
	}
	
	public LoginPage getLogout() {
		TimeUtil.mediumTime();
		eleUtil.doClick(menuButton);
		TimeUtil.mediumTime();
		eleUtil.doClick(logoutButton);
		TimeUtil.mediumTime();
		return new LoginPage(driver);
	}

}
