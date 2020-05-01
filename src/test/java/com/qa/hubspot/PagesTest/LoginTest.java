package com.qa.hubspot.PagesTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class LoginTest {
	
	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage lp;
	
	@BeforeTest
	public  void setup()
	{
		basepage = new BasePage();
		prop = basepage.init_Properties();
		driver = basepage.init_driver(prop);
		lp = new LoginPage(driver);
	}
	
	@Test(priority = 1)
	public void verifyPageTitleTest()
	
	{
		lp = new LoginPage(driver);
		String title = lp.getmyTitle();
		System.out.println("Title of the page is" + title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void verifySignUpLinkTest()
	{
		Assert.assertTrue(lp.checkSignUpLink());
	}
	
	
	@Test(priority = 3)
	public void doLoginTest()
	{
		lp.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	
}
