package com.qa.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.saucedemo.constants.AppConstants;
import com.qa.saucedemo.logger.Log;
import com.qa.saucedemo.util.ElementUtil;
import com.qa.saucedemo.util.TimeUtil;

public class InventoryPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productItemName = By.xpath("//div[@class=\"inventory_item\"][1]//div[@class=\"inventory_item_name \"]");
	private By productItemPrice = By.xpath("//div[@class=\"inventory_item\"][1]//div[@class=\"inventory_item_price\"]");
	private By productAddtoCart = By.id("add-to-cart-sauce-labs-backpack");
	private By productCartSize= By.className("shopping_cart_badge");
	private By productCartButton= By.className("shopping_cart_link");
	
	public InventoryPage (WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	public String getInventoryPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.INVENTORY_PAGE_TITLE, 5);
		Log.info("Acc page title : " + title);
		return title;
	}
	
	public String getProductItemName() {
		String itemName = eleUtil.doGetElementText(productItemName);
		Log.info(itemName);
		return itemName;
	}
	
	public String getProductItemPrice() {
		String itemPrice = eleUtil.doGetElementText(productItemPrice);
		Log.info(itemPrice);
		return itemPrice;
	}
	
	public String getCartSize() {
		TimeUtil.mediumTime();
		eleUtil.doClick(productAddtoCart);
		TimeUtil.mediumTime();
		String cartSize = eleUtil.doGetElementText(productCartSize);
		Log.info(cartSize);
		return cartSize;
	}
	public CartPage doGoToCart() {
		TimeUtil.mediumTime();
		eleUtil.doClick(productCartButton);
		TimeUtil.mediumTime();
		return new CartPage(driver);
	}
	
	
	
	

}
