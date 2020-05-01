package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.Base.BasePage;
import com.qa.hubspot.utils.Constants;
import com.qa.hubspot.utils.ElementUtility;
import com.qa.hubspot.utils.TimeUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtility eu;
	
	//1.Create the By Locators
	
	By username = By.id("username");
	By password = By.id("password");
	By LoginButton = By.id("loginBtn");
	By SignUpLink = By.linkText("Sign up");
	By ForgotPassword = By.linkText("Forgot my password");
	
	//2.Create Constructor
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eu = new ElementUtility(driver);
	}
	
	//3.Create PageActions/Methods
	
	public String getmyTitle()
	{
		return eu.waitforTitletobePresent(10, Constants.LOGIN_PAGE_TITLE);
	}

	public Boolean checkSignUpLink()
	{
		Boolean flag = eu.verifySignUpLink(10, SignUpLink);
		return flag;
	}
	
	public HomePage doLogin(String Username, String Password)
	{
		eu.doSendKeys(username, Username);
		eu.doSendKeys(password, Password);
		eu.doClick(LoginButton);
				
		TimeUtil.MedtWait();
		return new HomePage(driver);
	}
	
	
}
