package com.selenium_java_3.selenium_project;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App 
{
    public static void main( String[] args )
    {
    	
//    	WebDriverManager.chrome;
    	WebDriver driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.get("https://www.amazon.in");
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	WebElement sigin_button = driver.findElement(By.id("nav-link-accountList"));
    	sigin_button.click();
    	
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    	
    	WebElement phone_field = driver.findElement(By.id("ap_email"));
    	phone_field.sendKeys("123");
    	
    	WebElement submit_button = driver.findElement(By.id("continue"));
    	submit_button.click();
    	
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
 
    	if(driver.getPageSource().contains("Incorrect phone number")) {
    		System.out.println("Passed");
    	}else {
    		System.out.println("Failed");
    	}
    	
    	driver.quit();
 
    }
}
