package com.qa.saucedemo.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.saucedemo.logger.Log;

public class OptionsManager {
	
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co =new ChromeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
//			System.out.println("Running Chrome in Headless Mode");
			Log.info("Running Chrome in Headless Mode");
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			Log.info("Running Chrome in Incognito Mode");
			co.addArguments("--incognito");
		}
		
		return co;
	}
	
	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "edge");
		}
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			eo.addArguments("--inprivate");
		}
		return eo;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		fo= new FirefoxOptions();
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "firefox");
		}
		if(Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			fo.addArguments("--incognito");
		}
		return fo;
	}

}
