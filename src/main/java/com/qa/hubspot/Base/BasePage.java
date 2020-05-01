package com.qa.hubspot.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.hubspot.utils.OptionsUtilities;
import com.qa.hubspot.utils.TimeUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	OptionsUtilities optionUtilities;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver(){
		return tlDriver.get();
	}
	
	
	/**
	 * This method is used to initialize driver based on the browser
	 * @param browser
	 * @return driver
	 */
	public WebDriver init_driver (Properties prop)
	
	{	
		String browser = prop.getProperty("Browser");
		System.out.println("Browser name is " + browser);
		
		optionUtilities = new OptionsUtilities(prop);
		
		if(browser.equalsIgnoreCase("Chrome"))
		{
		WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver(optionUtilities.ChromeOptions());
		tlDriver.set(new ChromeDriver(optionUtilities.ChromeOptions()));
		}
		
		else if (browser.equalsIgnoreCase("Firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionUtilities.FirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionUtilities.FirefoxOptions()));
		}
	
		else if (browser.equalsIgnoreCase("Safari"))
		{
			WebDriverManager.getInstance(SafariDriver.class).setup();
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
		}
		
		else
		{
			System.out.println(browser + "Please pass the correct browser name");
		}
	
		getDriver().get(prop.getProperty("url"));
		TimeUtil.MedtWait();
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().fullscreen();

		return getDriver();
}
	
	
	/**
	 * This method is used to load the properties from config file
	 * @return prop
	 */
	public Properties init_Properties()
	{
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return prop;
	}
		
	public static String getScreenshot()
	{
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/Screenshots/"+System.currentTimeMillis();
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
		
	}
	
	
	

