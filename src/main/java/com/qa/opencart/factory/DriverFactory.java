package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FreamworkException;

public class DriverFactory {
	
	OptionsManager optionsManager;

	WebDriver driver;
	Properties prop;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	public static String highlight;

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");

		System.out.println("browser name is : " + browserName);
		highlight = prop.getProperty("highlight");
		
		optionsManager = new OptionsManager(prop);
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":

			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;

		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		default:
			System.out.println("plz pass the right browser...." + browserName);
			throw new BrowserException("NO BROWSER FOUND..." + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		//enName = qa, dev, stage, prod, uat
		// 
		
		prop = new Properties();
		
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test on Enviroment :"+ envName);
		
		try {
		if(envName==null) {
			System.out.println("No ENV is given running it on QA ENV...");
			 ip = new FileInputStream("./src/test/resources/config/config.uat.properties");

		}
		else {
			switch (envName.toLowerCase().trim()) {
			case "qa": 
				 ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
				break;
			case "dev": 
				 ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
				break;
			case "stage": 
				 ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
				break;
			case "prod": 
				 ip = new FileInputStream("./src/test/resources/config/config.prod.properties");
				break;
			case "uat": 
				 ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
				break;

			default:
				System.out.println("please pass correct enviroment..." + envName);
				throw new FreamworkException(AppError.ENV_NAME_NOT_FOUND + ":"+ envName);
			}
		
		}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
		
		
		
//		try {
//			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties"); ip = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return prop;

	}

}