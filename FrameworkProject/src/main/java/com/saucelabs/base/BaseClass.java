package com.saucelabs.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.saucelabs.utility.ExtentManager;

//BaseClass is used to load config.properties file and to initialize the WebDriver

public class BaseClass {
	
	public Properties prop;
	public static WebDriver driver;
	String browserName;
	
	@BeforeSuite
	public void beforeSuite() {
		ExtentManager.setExtent();
	}
	
	@BeforeClass
	public void launchApp() 
	{
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	    browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			
			setDriver(new ChromeDriver(options));
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			setDriver(new FirefoxDriver());
		} else if (browserName.equalsIgnoreCase("Edge")) {
			setDriver(new EdgeDriver());
		}
		
		getDriver().manage().window().maximize();  //Maximize the screen
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().getImplicitWaitTimeout();
		//Launch the application and get the value of URL from properties file
		getDriver().get(prop.getProperty("url"));
	}
	
	@AfterClass
	public void tearDown() {
		getDriver().quit();
	}

	public static WebDriver  getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		BaseClass.driver = driver;
	}
	
	@AfterSuite
	public void afterSuite() {
		ExtentManager.endReport();
	}

}
