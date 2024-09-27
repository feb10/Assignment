package com.qa.saucedemo.util;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.saucedemo.exceptions.ElementException;

import io.qameta.allure.Step;

//SRP: 
public class ElementUtil {

	private WebDriver driver;
	
	private final String DEFAULT_ELEMENT_TIME_OUT_MESSAGE = "Time out... Element is not found...";
	private final String DEFAULT_ALERT_TIME_OUT_MESSAGE = "Time out... Alert is not found...";

	

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	private void nullBlankCheck(String value) {
		if (value == null || value.length() == 0) {
			throw new ElementException(value + " --- value text can not be null or blank");
		}
	}

	public By getBy(String locatorType, String locatorValue) {
		By locator = null;

		switch (locatorType.toLowerCase().trim()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;
		case "classname":
			locator = By.className(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		case "css":
			locator = By.cssSelector(locatorValue);
			break;
		case "linktext":
			locator = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator = By.tagName(locatorValue);
			break;

		default:
			break;
		}

		return locator;

	}

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
	}

	public WebElement getElement(By locator) {
		WebElement element = null;

		try {
			element = driver.findElement(locator);
		} catch (NoSuchElementException e) {
			System.out.println("Element is not present on the page");
			e.printStackTrace();
			// return null;
		}

		return element;
	}

	public void doSendKeys(By locator, String value) {
		nullBlankCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(By locator, String value, int timeOut) {
		nullBlankCheck(value);
		getElement(locator).clear();
		waitForElementVisible(locator, timeOut).sendKeys(value);
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).clear();
		getElement(locatorType, locatorValue).sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doClick(By locator, int timeOut) {
		waitForElementVisible(locator, timeOut).click();
	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}


	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public WebElement waitForElementVisible(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}
		} catch (Exception e) {
			System.out.println("title is not found within : " + timeOut);
		}
		return null;

	}
	
	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
		} catch (Exception e) {
			System.out.println("url fraction is not found within : " + timeOut);
		}
		return null;

	}
	
	
	
	
}
