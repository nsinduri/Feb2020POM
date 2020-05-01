package com.qa.hubspot.PagesTest;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ExcelUtils;

public class ContactsPageTest {

	WebDriver driver;
	BasePage basepage;
	Properties prop;
	LoginPage lp;
	HomePage hp;
	ContactsPage cp;
	
	@BeforeTest
	public void setup()
	{
		basepage = new BasePage();
		prop = basepage.init_Properties();
		driver = basepage.init_driver(prop);
		lp = new LoginPage(driver);
		hp = lp.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		cp = hp.gotoContactsPage();
	}
	
	@Test
	public void verifyContactPageTitle()
	{
		String title = cp.verifyContactPageTitle();
		System.out.println("title of the contacts page is " + title);
		Assert.assertEquals("Contacts", Constants.CONTACT_PAGE_TITLE , "title did not match");
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object data[][]= ExcelUtils.getTestData(Constants.TESTDATA_SHEET_NAME);
		return data;
	}
	
	
	
	@Test(dataProvider ="getData")
	public void createContact(String email, String firstname, String lastname, String jobtitle)
	{
		String Name = cp.CreateContact(email,firstname,lastname,jobtitle);
		System.out.println(Name);
		Assert.assertEquals(Name,firstname+" "+lastname);
	}
	
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
}
