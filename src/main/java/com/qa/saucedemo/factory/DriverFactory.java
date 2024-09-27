package com.qa.saucedemo.factory;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.saucedemo.errors.AppError;
import com.qa.saucedemo.exceptions.BrowserExceptions;
import com.qa.saucedemo.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver intitDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("Browser Name is " + browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.trim().toLowerCase()) {
		case "chrome":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote - grid execution:
				init_remoteDriver("chrome");
			} else {
				// run it on local:
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}

			break;

		default:
			System.out.println("Pls enter correct browser");
			throw new BrowserExceptions("No Browser Found......" + browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	private void init_remoteDriver(String browserName) {

		System.out.println("Running tests on Remote GRID on browser: " + browserName);

		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":

				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
				break;

			default:
				System.out.println("plz pass thr right supported browser on GRID....");
				break;
			}

		}

		catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		// envName=qa,stage,prod,uat,dev
		// mvn clean install -Denv="qa"
		FileInputStream ip = null;
		prop = new Properties();

		String envName = System.getProperty("env");
		System.out.println("Running tests on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("No env is given...hence running it on QA env...");
				ip = new FileInputStream("./src/test/resources/config/config.sd1.properties");
			} else {

				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.sd1.properties");
					break;
				

				default:
					System.out.println("plz pass the right env name... " + envName);
					throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + " : " + envName);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {

		String path = System.getProperty("user.dir") + "\\screenshot\\" + methodName + ".png";
		try {
			File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			path = System.getProperty("user.dir") + "\\screenshot\\" + methodName + ".png";
			System.out.println(path);
			System.out.println(srcFile.toString());

			File destination = new File(path);

			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error encountered: " + e.getMessage());
		}

		return path;
	}
}