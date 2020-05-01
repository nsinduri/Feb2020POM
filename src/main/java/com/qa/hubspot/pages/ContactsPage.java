package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtility;
import com.qa.hubspot.utils.JavaScriptUtils;

public class ContactsPage {

	WebDriver driver;
	ElementUtility eu;
	JavaScriptUtils ju;
	
	public ContactsPage(WebDriver driver)
	{
		this.driver = driver;
		eu = new ElementUtility(driver);
		ju = new JavaScriptUtils(driver);
	}
	
	By HomePageContact = By.xpath("//a[@id='nav-primary-contacts-branch']");
	By ContactLink_ContactMenu = By.xpath("//a[@id='nav-secondary-contacts']");
	
	By PrimaryCreateContact = By.xpath("(//span[text()='Create contact'])[1]");
	By SecondaryCreateContact = By.xpath("(//span[text()='Create contact'])[2]");
	
	By Email = By.xpath("//input[@data-field='email']");
	By Firstname = By.xpath("//input[@data-field='firstname']");
	By Lastname = By.xpath("//input[@data-field='lastname']");
	By Jobtitle = By.xpath("//input[@data-field='jobtitle']");
	By GotoContacts = By.xpath("(//i18n-string[text()='Contacts'])[2]");
	
	public String verifyContactPageTitle()
	{
		String title = eu.waitforTitletobePresent(10, Constants.CONTACT_PAGE_TITLE);
		return title;
	}
	
	public String CreateContact(String email,String firstname,String lastname,String jobtitle)
	{
		
		eu.waitforelementtobeClickable(PrimaryCreateContact, 10);
		eu.doClick(PrimaryCreateContact);
		eu.waitforelementtobeVisible(Email, 10).sendKeys(email);
		eu.doSendKeys(Firstname, firstname);
		eu.doSendKeys(Lastname, lastname);
		eu.doSendKeys(Jobtitle, jobtitle);
		eu.waitforelementtobeClickable(SecondaryCreateContact, 10);
		ju.clickElementByJS(eu.findElement(SecondaryCreateContact));
		
		String fullname = firstname+" "+lastname;
		String NameXpath = "(//span[text()='"+fullname+"'])[2]";
		String ContactName = eu.waitforelementtobePresent(By.xpath(NameXpath), 10).getText().trim();
		
		eu.waitforelementtobeClickable(GotoContacts, 10);
		eu.doClick(GotoContacts);
		return ContactName;
		
	}
	
	
}
