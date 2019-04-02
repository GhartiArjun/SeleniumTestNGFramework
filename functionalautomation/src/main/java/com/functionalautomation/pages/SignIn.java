package com.functionalautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignIn {

	private WebDriver driver;

	@FindBy(xpath = "//a[contains (text(),'Sign in')]")
	WebElement sigInButton;

	@FindBy(xpath = "//input[contains (@id,'email') and @name ='email']")
	WebElement email;
	
	
	@FindBy(xpath = "//input[contains (@id,'passwd')]")
	WebElement pass;

	public SignIn(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void clickOnLoginButon() {
		(sigInButton).click();

	}

	public void setUsername(String userName) {
		email.sendKeys(userName);

	}

	public void setPassword(String password) {
		pass.sendKeys(password);

	}

}
