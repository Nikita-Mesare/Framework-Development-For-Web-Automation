package com.saucelabs.testcases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.saucelabs.base.BaseClass;
import com.saucelabs.pageobjects.LoginPage;
import com.saucelabs.pageobjects.ProductsPage;
import com.saucelabs.utility.LogClass;

public class ProductsPageTest extends BaseClass{
	
	LoginPage lp;
	ProductsPage pp;
	
	@Test(priority = 1)
	public void verifyProductsPageURL()
	{
		LogClass.startTestCase("verifyProductsPageURL");
		lp = new LoginPage();
		lp.performLogin("standard_user", "secret_sauce");
		String actualURL = getDriver().getCurrentUrl();
		String expectedURL = "https://www.saucedemo.com/inventory.html";
		LogClass.info("Verifying URL of Products page");
		Assert.assertEquals(actualURL, expectedURL);
		LogClass.endTestCase("verifyProductsPageURL");
	}
	
	@Test(priority = 2)
	public void verifyProductsPageTitle()
	{
		LogClass.startTestCase("verifyProductsPageTitle");
		//lp = new LoginPage();
		//lp.performLogin("standard_user", "secret_sauce");
		String actualTitle = getDriver().getTitle(); // no need to perform login sice it is executed after above test 
		String expectedTitle = "Swag Labs";
		Assert.assertEquals(actualTitle, expectedTitle);
		LogClass.endTestCase("verifyProductsPageTitle");
	}
	
	@Test(priority = 3)
	public void verifyProductsPageAppLogoDisplayed()
	{
		LogClass.startTestCase("verifyProductsPageAppLogoDisplayed");
		Assert.assertTrue(getDriver().findElement(By.className("app_logo")).isDisplayed());
		LogClass.endTestCase("verifyProductsPageAppLogoDisplayed");
	}
	
	@Test(priority = 4)
	public void verifyLogoutFunction()
	{
		LogClass.startTestCase("verifyLogoutFunction");
		pp = new ProductsPage();
		pp.performLogout();
		LogClass.info("Verifying logout functionality");
		Assert.assertTrue(getDriver().findElement(By.className("login_logo")).isDisplayed());
		LogClass.endTestCase("verifyLogoutFunction");
	}
	
	@Test(priority = 5)
	public void verifyPriceFilterAscOrder() throws InterruptedException
	{
		LogClass.startTestCase("verifyPriceFilterAscOrder");
		pp = new ProductsPage();
		lp = new LoginPage();
		lp.performLogin("standard_user", "secret_sauce");
		Thread.sleep(3000);
		List<WebElement> unfilteredPrices = getDriver().findElements(By.className("inventory_item_price")); //Capture all price text web elements
		List<Double> unfilteredPriceList = new ArrayList<Double>();
		for (WebElement price : unfilteredPrices)
		{
			unfilteredPriceList.add(Double.valueOf(price.getText().replace("$", ""))); //remove dollar symbol and convert text sting into double
		}
		LogClass.info("Applying low to high price filter on products");
		pp.performDropDownSelectionAsc(); // Apply price filter low to high
		Thread.sleep(3000);
		List<WebElement> filteredPrices = getDriver().findElements(By.className("inventory_item_price")); //Capture all price text web elements from filtered list
		List<Double> filteredPriceList = new ArrayList<Double>();
		for (WebElement price : filteredPrices)
		{
			filteredPriceList.add(Double.valueOf(price.getText().replace("$", "")));  //again remove dollar symbol and convert text sting into double, now this is sorted list
		}
		Collections.sort(unfilteredPriceList); //Sort the unfiltered list which was captured before applying filter through UI through java logic
		LogClass.info("Verifying filter function for low to high price");
		Assert.assertEquals(unfilteredPriceList, filteredPriceList); //Compare both the lists
		pp.performLogout();
		LogClass.endTestCase("verifyPriceFilterAscOrder");
	}
	
	@Test(priority = 6)
	public void verifyPriceFilterDescOrder() throws InterruptedException
	{
		LogClass.startTestCase("verifyPriceFilterDescOrder");
		pp = new ProductsPage();
		lp = new LoginPage();
		lp.performLogin("standard_user", "secret_sauce");
		Thread.sleep(3000); //Applying wait just to see original order of prices - not recommended for real time
		List<WebElement> unfilteredPrices = getDriver().findElements(By.className("inventory_item_price"));
		List<Double> unfilteredPriceList = new ArrayList<Double>();
		for (WebElement price : unfilteredPrices)
		{
			unfilteredPriceList.add(Double.valueOf(price.getText().replace("$", "")));
		}
		LogClass.info("Applying high to low price filter on products");
		pp.performDropDownSelectionDesc();
		Thread.sleep(3000); //Applying wait just to see order of prices after applying filter - not recommended for real time
		List<WebElement> filteredPrices = getDriver().findElements(By.className("inventory_item_price"));
		List<Double> filteredPriceList = new ArrayList<Double>();
		for (WebElement price : filteredPrices)
		{
			filteredPriceList.add(Double.valueOf(price.getText().replace("$", "")));
		}
		Collections.sort(unfilteredPriceList);
		Collections.reverse(unfilteredPriceList);
		LogClass.info("Verifying filter function for high to low price");
		Assert.assertEquals(unfilteredPriceList, filteredPriceList);
		LogClass.endTestCase("verifyPriceFilterDescOrder");
	}

}
