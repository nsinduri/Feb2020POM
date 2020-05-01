package com.qa.hubspot.utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtility {

WebDriver driver;
	
	public ElementUtility(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public WebElement findElement(By Locator)
	{
		WebElement element = driver.findElement(Locator);
		return element;
	}
	
	public void doSendKeys(By Locator, String element)
	{
		findElement(Locator).sendKeys(element);
	}
	
	public void doClick(By Locator)
	{
		findElement(Locator).click();
	}
	
	/**
	 * This method is used to send keys using Actions Class
	 * @param Locator
	 * @param Value
	 */
	public void doActionSendKeys(By Locator, String Value)
			{
				Actions action = new Actions(driver);
				WebElement Username = findElement(Locator);
				action.sendKeys(Username, Value).perform();;
				
			}
	
	
	/**
	 * This method is used to perform Click using Actions class
	 * @param Locator
	 */
	public void doActionClick(By Locator)
	{
		Actions action = new Actions(driver);
		WebElement LoginButton = findElement(Locator);
		action.click(LoginButton).perform();
		
	}

	
	
	
	/**
	 * This method is used to select value from dropdown with default select methods.
	 * @param Locator
	 * @param value
	 */
	public void SelectDropDown(By Locator, String value)
	{

		Select select = new Select(findElement(Locator));
		select.selectByVisibleText(value);
			
	}

	public void SelectDropDown(By Locator, int index)
	{

		Select select = new Select(findElement(Locator));
		select.selectByIndex(index);			
	}

	
	public void DropDownWithByLocator1 (WebDriver driver, By Locator, String Value)
	
	{
		ElementUtility eu = new  ElementUtility(driver);
		
		Select dayslist = new Select(eu.findElement(Locator));
		List <WebElement> dayOptions = dayslist.getOptions();
		
		System.out.println("size of the days options list "  + dayOptions.size() );
		for(int i=0; i<dayOptions.size();i++)
		{
			System.out.println(dayOptions.get(i).getText());
			
			if (dayOptions.get(i).getText().equals(Value))
			{
				dayOptions.get(i).click();
				break;
			}
		}
}
	public List<String > getDropDownValues (WebDriver driver, By Locator)
	
	{
		ElementUtility eu = new  ElementUtility(driver);
		
		Select dayslist = new Select(eu.findElement(Locator));
		 List <WebElement> dayOptions = dayslist.getOptions();
		 List <String> ar = new ArrayList <String>();
		//System.out.println("size of the days options list "  + dayOptions.size() );
		for(int i=0; i<dayOptions.size();i++)
		{
			ar.add((dayOptions.get(i).getText()));
		}

	return ar;
	}
	
	//Custom Wait Utility functions
	
	/**
	 * This method is used to give explicit timeouts based on presence of elements.
	 * @param Locator
	 * @param timeout
	 * @return WebElement
	 */
	public WebElement waitforelementtobePresent(By Locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locator));
		return findElement(Locator);
	}
	
	
	/**
	 * This method is used to give timeouts based on presence of title
	 * @param Locator
	 * @param timeout
	 * @param Title
	 * @return title
	 */
	public String waitforTitletobePresent(int timeout, String Title)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.titleIs(Title));
		return driver.getTitle();
	}
	
	/**
	 * This method is used for the element to be visible on the page to be clicked
	 * @param Locator
	 * @param timeout
	 * @return WebElement
	 */
	public WebElement waitforelementtobeClickable(By Locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(Locator));
		return findElement(Locator);
	}
	
	/**
	 * This method is used to give explicit timeouts till the element is visible on the page(not only in DOM)
	 * @param Locator
	 * @param timeout
	 * @return WebElement
	 */
	public WebElement waitforelementtobeVisible(By Locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
		return findElement(Locator);
	}
	
	
	/**
	 * This method gives timeout till the alert is displayed
	 * @param timeout
	 * @return
	 */
	public boolean waitforAlerttobeDisplayed(int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.alertIsPresent());
		return true;
	}
	
	public Boolean verifySignUpLink(int timeout, By Locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator)).isDisplayed();
				
	}
	
	
}
