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

	protected static WebDriver driver;
	private FileInputStream fileinput;
	private static Properties prop;

	public void loadPropertiesFiles(){
		prop = new Properties();
		try {
		fileinput = new FileInputStream(new File("./src/main/java/com/functionalautomation/config/config.properties"));
		prop.load(fileinput);
		// return prop.getProperty(propertyKeyName);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadBrowser(String browserName) {
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

	private void captureScreenShots(String screenShotName) {
		
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

	public void getScreenShots(String name) {
		Base base = new Base();
		base.captureScreenShots(name);

	}

	public void getBrowser(String browserName){
		Base base = new Base();
		base.loadPropertiesFiles();
		base.loadBrowser(browserName);
		driver.get(prop.getProperty("url"));

	}

	public static void main(String[] args){
		Base base = new Base();
		base.getBrowser("chrome");
		base.captureScreenShots("test");

	}

}
