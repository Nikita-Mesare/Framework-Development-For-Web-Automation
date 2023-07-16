package com.saucelabs.action;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucelabs.base.BaseClass;

//ActionClass has reusable methods which are used to perform different actions on web elements

public class ActionClass extends BaseClass {
	
	//This will perform click action on web element
	public static void clickOnWebElement(WebElement ele, long wateTime) 
	{
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(wateTime));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.click();
	}
	
	//This will perform sendkeys action on text field
	public static void sendKeysOnWebElement(WebElement ele, String text)
	{
		ele.click();
		ele.clear();
		ele.sendKeys(text);
	}
	
	//This will perform select action from drop down web element
	public static void doSelectByVisibleText(WebElement ele, String text) 
	{
		Select select = new Select(ele);
		select.selectByVisibleText(text);
	}
	
	//This will perform mouse hover action
	public static void mouseHoverOnWebElement(WebElement ele)
	{
		Actions act = new Actions(getDriver());
		act.moveToElement(ele).click().build().perform();
	}
	
	//This will perform right click action
	public static void rightClickOnWebElement(WebElement ele)
	{
		Actions act = new Actions(getDriver());
		act.contextClick(ele).click().build().perform();
	}

}
