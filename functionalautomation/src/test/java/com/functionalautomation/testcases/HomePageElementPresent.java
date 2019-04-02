package com.functionalautomation.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.functionalautomation.base.Base;
import com.functionalautomation.pages.HomePage;

public class HomePageElementPresent extends Base {
	
	@BeforeTest
	public void testSetUp() {
		System.out.println("setting up browser and driver");
		Base  base = new Base();
		base.getBrowser("chrome");
		
	}
	
	@Test
	public void elementPresentVerification(){
		
		System.out.println("Testing in process" +getClass().getName());
		HomePage home = new HomePage(driver);
		home.logInButtonPresent();
		getScreenShots(getClass().getName());
		
	}
	
	@AfterTest
	public void tearDown() {
		System.out.println("finishig test, clossing app");
		driver.close();
		driver.quit();
		
	}

}
