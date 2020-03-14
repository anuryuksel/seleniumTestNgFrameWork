package com.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.hubspot.base.BasePage;
import com.hubspot.util.Constants;
import com.hubspot.util.ElementUtil;





public class LoginPage extends BasePage{

	WebDriver driver;
	ElementUtil elementUtil;
	
	
	//NonPage Factory
	//locators
	By emailId= By.id("username");
	By password = By.id("password");
	By loginBtn = By.id("loginBtn");
	By clickbtn = By.xpath("//a[@data-button-use='primary']");
	//Constructor
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	//Page Action
	public String getLoginTitle(){
		return elementUtil.waitForGetPageTitle(Constants.LOGIN_PAGE_TITLE);
		//return driver.getTitle();
	}
	
	public HomePage doLogin(String username, String pwd){
		elementUtil.doSendKeys(emailId, username);
//		driver.findElement(emailId).sendKeys(username);
		elementUtil.doSendKeys(password, pwd);
//		driver.findElement(password).sendKeys(pwd);
		elementUtil.doClick(loginBtn);
//		driver.findElement(loginBtn).click();
		
		return new HomePage(driver);
	}
	

		
	}

