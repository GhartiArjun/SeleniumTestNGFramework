package com.functionalautomation.testcases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.Test;

import com.functionalautomation.base.Base;
import com.functionalautomation.pages.HomePage;

public class HomePageElementPresent extends Base {
	
	@Test
	public void elementPresentVerification() throws FileNotFoundException, IOException, InterruptedException {
		
		Base  base = new Base();
		base.getBrowser("chrome");
		HomePage home = new HomePage(driver);
		home.logInButtonPresent();
		
		
		
	}

}
