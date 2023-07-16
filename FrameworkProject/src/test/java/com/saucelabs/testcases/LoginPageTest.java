package com.saucelabs.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.saucelabs.base.BaseClass;
import com.saucelabs.dataprovider.LogindataProvider;
import com.saucelabs.pageobjects.LoginPage;
import com.saucelabs.pageobjects.ProductsPage;
import com.saucelabs.utility.LogClass;

public class LoginPageTest extends BaseClass {
	
	LoginPage lp;
	ProductsPage pp;
	
	@Test(priority = 1)
	public void verifyLoginPageURL()
	{
		LogClass.startTestCase("verifyLoginPageURL");
		String actualURL = getDriver().getCurrentUrl();
		String expectedURL = "https://www.saucedemo.com/";
		LogClass.info("Verifying URL of Login page");
		Assert.assertEquals(actualURL, expectedURL);
		LogClass.endTestCase("verifyLoginPageURL");
	}
	
	@Test(priority = 2)
	public void verifyLoginPageTitle()
	{
		LogClass.startTestCase("verifyLoginPageTitle");
		String actualTitle = getDriver().getTitle();
		String expectedTitle = "Swag Labs";
		Assert.assertEquals(actualTitle, expectedTitle);
		LogClass.endTestCase("verifyLoginPageTitle");
	}
	
	@Test(priority = 3)
	public void verifyLoginPageAppLogoDisplayed()
	{
		LogClass.startTestCase("verifyLoginPageAppLogoDisplayed");
		Assert.assertTrue(getDriver().findElement(By.className("login_logo")).isDisplayed());
		LogClass.endTestCase("verifyLoginPageAppLogoDisplayed");
	}

	@Test(priority = 4, dataProvider="LoginCredentials", dataProviderClass = LogindataProvider.class)
	public void loginTest(String user,String pwd,String exp)
	{
		LogClass.startTestCase("loginTest");
		lp = new LoginPage();
		pp = new ProductsPage();
		LogClass.info("User is entering login credentials");
		lp.performLogin(user, pwd);
		String actual_URL = getDriver().getCurrentUrl();
		String expected_URL = "https://www.saucedemo.com/inventory.html";
		if(exp.equals("Valid"))
		{
			if(actual_URL.equals(expected_URL))
			{
				pp.performLogout();
				LogClass.info("Verifying login functionality if it is successfull for valid credentials");
				Assert.assertTrue(true);
			}
			else
			{
				LogClass.info("Verifying login functionality if it is unsuccessfull for valid credentials");
				Assert.assertTrue(false);
			}
		}
		else if(exp.equals("Invalid"))
		{
			if(actual_URL.equals(expected_URL))
			{
				pp.performLogout();
				LogClass.error("This test has failed");
				Assert.assertTrue(false);	
			}
			else
			{
				LogClass.info("Verifying unsuccessfull login for invalid credentials");
				Assert.assertTrue(true);
			}
		}
		LogClass.endTestCase("loginTest");
	}
	
	
	
}
