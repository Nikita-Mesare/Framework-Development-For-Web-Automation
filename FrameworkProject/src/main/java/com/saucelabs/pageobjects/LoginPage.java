package com.saucelabs.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucelabs.action.ActionClass;
import com.saucelabs.base.BaseClass;

public class LoginPage extends BaseClass {
	
	//Constructor to initialize the instance of WebDriver
	public LoginPage() {
			
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath="//input[@id='user-name']")
	static WebElement userName;
	
	@FindBy(xpath="//input[@id='password']")
	static WebElement password;
	
	@FindBy(xpath="//input[@id='login-button']")
	static WebElement LoginBtn;
	
	//Action method to perform login
	public void performLogin(String uname, String pwd)
	{
		ActionClass.sendKeysOnWebElement(userName,uname);
		ActionClass.sendKeysOnWebElement(password, pwd);
		ActionClass.clickOnWebElement(LoginBtn, 5);
		
	}
	
	
}
