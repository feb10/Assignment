package com.qa.saucedemo.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.saucedemo.factory.DriverFactory;
import com.qa.saucedemo.pages.LoginPage;
import com.qa.saucedemo.pages.InventoryPage;
import com.qa.saucedemo.pages.CartPage;


public class BaseTest {
	WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	
	protected LoginPage loginPage;
	protected InventoryPage inventoryPage;
	protected CartPage cartPage;

	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName)  {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.intitDriver(prop);
		loginPage = new LoginPage(driver);
		inventoryPage = new InventoryPage(driver);
		cartPage = new CartPage(driver);
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}