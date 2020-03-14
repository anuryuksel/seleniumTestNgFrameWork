package com.hubspot.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.HomePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;

public class HomePageTest {
WebDriver driver;
Properties prop;
BasePage basePage;
LoginPage loginPage;
HomePage homePage;

@BeforeMethod
public void setUp(){
	basePage = new BasePage();
	prop = basePage.initialize_properties();
	driver = basePage.initialize_driver(prop);
	loginPage = new LoginPage(driver);
	homePage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
}
@Test (priority = 1, description= "home page title")
public void verifyHomePageTitle(){
	String title = homePage.getHomePageTitle();
	Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
}

@Test(priority=2, description="homepage header")
public void verifyHomePageHeader(){
	String header= homePage.getHomePageHeader();
	System.out.println(header);
	Assert.assertEquals(header, Constants.HOME_PAGE_HEADER);
}
@Test(priority = 3)
	
public void verifyAccountName(){
	
	String accountName = homePage.verifyLoggedInAccountName();
	System.out.println(accountName);
	Assert.assertEquals(accountName, Constants.AccountName);
}
@AfterMethod
public void tearDown(){
	driver.quit();
}
}
