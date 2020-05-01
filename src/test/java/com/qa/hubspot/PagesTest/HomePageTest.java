package com.qa.hubspot.PagesTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;

public class HomePageTest {

	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage lp;
	HomePage hp;
		
	@BeforeTest
	public void setup()
	{
		basepage = new BasePage();
		prop = basepage.init_Properties();
		driver = basepage.init_driver(prop);
		lp = new LoginPage(driver);
		hp = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		
	}
	
	@Test(priority=1)
	public void verifyHomePageTitle()
	{
		String HomePageTitle = hp.getHomePageTitle();
		System.out.println("Title of the Home page is " + HomePageTitle );
		Assert.assertEquals(HomePageTitle, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyAccountName()
	{
		String AccountName = hp.verifyAccountName();
		System.out.println("Account Name is " + AccountName);
		Assert.assertEquals(AccountName, prop.getProperty("accountname"));
	}
	
	@Test(priority=3)
	public void verifyHomePageHeader()
	{
		String HomePageHeader = hp.verifyHomePageHeader();
		System.out.println("Home Page Header is" + HomePageHeader);
		Assert.assertEquals(HomePageHeader, Constants.HOME_PAGE_HEADER);
	}
		
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	
	
}
