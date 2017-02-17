package com.preview.incaseofcrisis;
import org.junit.Assert;
import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
public class ssoLoginTest {
	WebDriver driver;
	@Before
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "/Users/John/Documents/workspace/drivers/chromedriver");
		driver = new ChromeDriver();
		String baseUrl = "https://preview.incaseofcrisis.com/MyPlans/login_icoc.aspx";
		driver.get(baseUrl);
		//resize browser since Chrome is finicky about how it clicks on things 
    	driver.manage().window().setSize(new Dimension(1024,768));
	}
	@After
	public void end(){
		driver.quit();
	}
	@Test
	public void testIfUserIsSSOEnabledAndLogin(){
		String userName = "john.baldwin@rockdovesolutions.com";
		String password = "4Jb2gt!n!";
		String expectedSSOTitle = "RockDove Solutions, Inc. - Sign In";
		String expectedPortalTitle = "In Case of Crisis >> Portal";
		//SSO Login Screen
		type(By.id("txtUserEmail"), userName);
		click(By.id("btnContinue"));
		driverWait();
		assertTrue(driver.getTitle().equals(expectedSSOTitle));
		System.out.println(driver.getTitle());
		//Portal login Screen
		type(By.id("input26"), userName);
		type(By.id("input33"), password);
		click(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/form/div[2]/input"));
		driverWait();
		//assertTrue(driver.getTitle().equals(expectedPortalTitle));
		System.out.println(driver.getTitle());
	}
	
	
	
	//Method to find element by string value
	public void type(By by, String value){
		driver.findElement(by).sendKeys(value);		
	}
	//Method to click 
	public void click(By by){
		driver.findElement(by).click();
	}
	//Wait Method
	public void driverWait(){
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}
}
