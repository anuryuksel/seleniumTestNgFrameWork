package com.hubspot.test;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hubspot.base.BasePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;

//@Listeners(com.hubspot.listeners.ExtentReportListener.class) // you can use only one listener at the same time
@Listeners(com.hubspot.listeners.TestAllureListener.class)
public class LoginPageTest {
	
	Logger log= Logger.getLogger("LoginPageTest");
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		log.info("Browser is launching...");
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	
	@Test (priority =2,enabled=true, description="Login Test using correct username and password")
	public void loginTest1(){
		log.info("logintest1 is starting...");
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		log.info("logintest1 is ending...");
	}
	
	@Test (priority =3,enabled=true, description="Login Test using correct username and incorrectpassword")
	
	public void aloginTest2(){
		loginPage.doLogin(prop.getProperty("username"),prop.getProperty("incorrectpassword"));
	}
	
	@Test (priority =4,enabled=true, description="Login Test using incorrect username and  correct password")
		public void aloginTest3(){
		loginPage.doLogin(prop.getProperty("incorrectuser"),prop.getProperty("password"));
	}
	
	@Test(priority = 1, enabled= true, description = "Hubspot Login Get Title")
	
	public void getTitle(){
	String title =	loginPage.getLoginTitle();
	System.out.println(title);
	Assert.assertEquals(title,Constants.LOGIN_PAGE_TITLE);
	}
	
	
	@AfterMethod
	public void tearDown(){
		basePage.quitbrowser();
	}
	
}
