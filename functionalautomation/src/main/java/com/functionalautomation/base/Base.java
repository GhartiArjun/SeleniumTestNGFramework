package com.functionalautomation.base;

import java.io.File;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public  WebDriver driver;
	private FileInputStream fileinput;
	private Properties prop;

	public String loadPropertiesFiles(String keyName){
		prop = new Properties();
		try {
		fileinput = new FileInputStream(("./src/main/java/com/functionalautomation/config/config.properties"));
		prop.load(fileinput);
		if(keyName.equalsIgnoreCase("browser")) { 
			return prop.getProperty("browser");
		}
		if(keyName.equalsIgnoreCase("url")) {
			return prop.getProperty("url");
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "can't find matching key.";
	}

	public void loadBrowser() {
		String browserName =loadPropertiesFiles("browser");
		if (System.getProperty("os.name").contains("Windows")) {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
		} else if (System.getProperty("os.name").contains("mac os")) {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
		}
	}

	public void captureScreenShots(String screenShotName) {
		
		String loc = "./src/main/java/com/functionalautomation/screenshots/";
		File image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("_MMM,d,yy HH_mm_ss");
		String finalImage = loc + screenShotName + dateFormat.format(calender.getTime()) + ".png";
		File destination = new File(finalImage);
		try {
			Files.copy(image, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
/*
	public void getBrowser(String browserName){
		Base base = new Base();
		base.loadPropertiesFiles();
		base.loadBrowser(browserName);
		driver.get(prop.getProperty("url"));

	}*/

public static void main(String[] args) {
	Base base = new Base();
	base.loadPropertiesFiles("browser");
	System.out.println(base.loadPropertiesFiles("page"));
}

}
