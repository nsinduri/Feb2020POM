package com.qa.hubspot.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

WebDriver driver;
	
	public JavaScriptUtils(WebDriver driver)
	{
		this.driver= driver;
	}
	
	/**
	 * To get the title of the method using JavaScript
	 * @return
	 */
	public String getTitlebyJS()
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String title = js.executeScript("return document.title").toString();
		return title;
	}
	
	/**
	 * To get the entire text in the webpage
	 * @return
	 */
	public String getPageInnerText()
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		String text = js.executeScript("return document.documentElement.innerText").toString();
		return text;
	}

	/**
	 * To get Browser Information available on the system
	 * @return
	 */
	public String getBrowserInfo()
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String BrowserInfo = js.executeScript("return navigator.userAgent").toString();
		return BrowserInfo;
	}
	
	/**
	 * To refresh the browser using JavaScript 
	 */
	public void refreshBrowserbyJS()
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("history.go(0)");
	}

	/**
	 * To click on a element using JS
	 * @param element
	 */
	public void clickElementByJS(WebElement element)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].click();", element);
		
	}
	
	/**
	 * To draw a border around a webelement
	 * @param element
	 */
	public void drawBorder(WebElement element)
	{
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		js.executeScript("arguments[0].style.border = '3px solid red'" ,element);
	}

	/**
	 * To flash/highlight the element
	 * @param element
	 */
	public void flash(WebElement element)
	{
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 20; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	
	/**
	 * To generate alert message using Java Script
	 * @param message
	 */
	public void generateAlert(String message)
	{
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript(" alert(' "+message+" ')");
		
	}
	
	/**
	 * To scroll down the webpage
	 */
	public void scrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	/**
	 * To scroll up the webPage
	 */
	public void scrollPageUp() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	/**
	 * Send Keys using JavaScript
	 * @param id
	 * @param value
	 */
	public void sendKeysUsingJSWithId(String id, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}
	

	
	
	
}
