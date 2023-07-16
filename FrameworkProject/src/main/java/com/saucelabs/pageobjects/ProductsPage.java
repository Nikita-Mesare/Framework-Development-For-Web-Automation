package com.saucelabs.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.saucelabs.action.ActionClass;
import com.saucelabs.base.BaseClass;

public class ProductsPage extends BaseClass{
	
	public ProductsPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@FindBy(xpath="//select[@class='product_sort_container']")
	static WebElement filterDropdown;
	
	@FindBy(xpath="//button[@id='react-burger-menu-btn']")
	static WebElement BurgerMenuBtn;
	
	@FindBy(xpath="//a[@id='logout_sidebar_link']")
	static WebElement Logout;
	
	//This will perform logout action 
	public void performLogout() 
	{
		ActionClass.clickOnWebElement(BurgerMenuBtn, 5);
		ActionClass.clickOnWebElement(Logout, 5);
	}
	
	public void performDropDownSelectionAsc()
	{
		ActionClass.doSelectByVisibleText(filterDropdown,"Price (low to high)");
	}
	
	public void performDropDownSelectionDesc()
	{
		ActionClass.doSelectByVisibleText(filterDropdown,"Price (high to low)");
	}

}
