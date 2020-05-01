package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtility;

public class HomePage {

	WebDriver driver;
	ElementUtility eu;
	
	//1.Create the By Locators
	//2.Create the constructor
	//3.Create the page actions/methods
	
	By header = By.cssSelector("h1.private-page__title"); 
	By accountMenu = By.cssSelector("a#account-menu");
	By accountName = By.xpath("//div[@class='navAccount-accountName' and text() = 'amazon ']");
	By HomePageContact = By.xpath("//a[@id='nav-primary-contacts-branch']");
	By ContactLink_ContactMenu = By.xpath("//a[@id='nav-secondary-contacts']");
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		eu = new ElementUtility(driver);
	}
	
	
	public String getHomePageTitle()
	{
	  return eu.waitforTitletobePresent(10, Constants.HOME_PAGE_TITLE);
	}
	
	public String verifyHomePageHeader()
	{
		if(eu.findElement(header).isDisplayed())
		{
			return eu.findElement(header).getText();
		}
		return null;
}
	public String verifyAccountName()
	{
		eu.findElement(accountMenu).click();
		String AccName = eu.findElement(accountName).getText();
		return AccName;
	}
	
	public ContactsPage gotoContactsPage()
	{
		clickonContacts();
		return new ContactsPage(driver);
	}
	
	private void clickonContacts() {
		
		eu.waitforelementtobeVisible(HomePageContact, 10).click();
		eu.waitforelementtobeVisible(ContactLink_ContactMenu, 5).click();
		
		
	}
	
	
}

