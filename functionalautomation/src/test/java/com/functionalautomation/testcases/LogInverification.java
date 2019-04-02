package com.functionalautomation.testcases;

import java.io.IOException;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.functionalautomation.base.Base;
import com.functionalautomation.excle.ExcleMain;
import com.functionalautomation.pages.SignIn;

public class LogInverification extends Base{
	
	@BeforeTest
	public void testSetup() {
		getBrowser("chrome");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test(dataProvider="loginData")
	public void loginVerification(String userName, String password) {
		SignIn signIn = new SignIn(driver);
		signIn.clickOnLoginButon();
		signIn.setUsername(userName);
		signIn.setPassword(password);
		
	}
	
	@Test @DataProvider(name ="loginData")
	public static Object logInData() throws IOException {
		ExcleMain data = new ExcleMain();
		return data.printExcleData("Sheet1");
	}
	
	
	@AfterTest
	public void tearDown() {
		//driver.close();
		
	}
	

}
