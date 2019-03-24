package com.functionalautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	private WebDriver driver;
	
	@FindBy(id = "")
	WebElement i;
	
	@FindBy(xpath = "//*[@class ='login']")
	WebElement logInButton;
	
	@FindBy(xpath = "//div[@id ='contact-link']")
	WebElement contactUsButton;
	
	@FindBy(name = "")
	WebElement n;
	
	@FindBy(css = "")
	WebElement css;
	
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	
	}
	
	public void logInButtonPresent() {
		if (logInButton.isDisplayed()) {
			System.out.println( "element present.");
		}else System.out.println( "no such element found");
	}
	

}
