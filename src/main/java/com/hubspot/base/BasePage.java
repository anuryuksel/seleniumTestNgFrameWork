package com.hubspot.base;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public static String flash;
	
	
	public WebDriver initialize_driver(Properties prop){
		
	//	String browser = "chrome";
		String browser = prop.getProperty("browser");
		String headless = prop.getProperty("headless"); // headless
		flash = prop.getProperty("elementflash");
		
		if(browser.equalsIgnoreCase("chrome")){
//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", "/Users/anur/Documents/drivers/chromedriver");
			if(headless.equalsIgnoreCase("yes")){
				ChromeOptions co= new ChromeOptions();
				co.addArguments("--headless");
				driver= new ChromeDriver(co);
			}else{
			driver = new ChromeDriver();
			}
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			if (headless.equalsIgnoreCase("yes")) {
				FirefoxOptions fo= new FirefoxOptions();
				fo.addArguments("--headless");
				driver  = new FirefoxDriver(fo);
			}else{
			driver = new FirefoxDriver();
			}
		}
		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		return driver;
	}
	
	public Properties initialize_properties(){
		prop = new Properties();
		try {
			FileInputStream ip= new FileInputStream("/Users/anur/Documents/workspace/IlhanHocaTestNG"
					+ "/src/main/java/config/hubspot/config/config.properties");
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}
	
	public void quitbrowser(){
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println("some exception occured while quiting browser");
		}
	}
	public void closeBrowser(){
		try {
			driver.close();
		} catch (Exception e) {
			System.out.println("Some exception occured while closing browser");
		}
	}
	
}
