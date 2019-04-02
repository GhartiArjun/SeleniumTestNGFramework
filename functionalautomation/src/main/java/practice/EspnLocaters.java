package practice;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EspnLocaters {
	private static WebDriver driver;
	private static ArrayList<String> tabs;

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://www.webdriveruniversity.com/Actions/index.html");
		// driver.manage().window().maximize();
		
		  driver.findElement(By.xpath("//a[@id ='contact-us']")).click(); 
		  tabs = new ArrayList<String>(driver.getWindowHandles());
		  driver.switchTo().window(tabs.get(1));
		  driver.findElement(By.xpath("//input[@name ='first_name']")).sendKeys("Arjun");
		  driver.findElement(By.xpath("//input[@name ='last_name']")).sendKeys("Bhujel");
		   driver.findElement(By.xpath("//input[@name ='email']")).sendKeys("bhujel@gmail.com"); 
		   driver.findElement(By.name("message")). sendKeys("Hello there ! my name is Arjun."); 
		   driver.findElement(By.xpath("//div[@id = 'form_buttons']/input[@type='submit']")).click();
		  driver.switchTo().window(tabs.get(0));
		 

		
		  driver.findElement(By.id("login-portal")).click();
		  driver.findElement(By.id("text")).click();
		  driver.findElement(By.xpath("//input[@id='text']")).sendKeys("arjung");
		  driver.findElement(By.id("password")).click();
		  driver.findElement(By.id("password")).sendKeys("arjung1");
		  driver.findElement(By.id("login-button")).click();
		  driver.switchTo().alert().accept(); //driver.quit();
		 
		
		  driver.get("http://www.webdriveruniversity.com/");
		  driver.findElement(By.xpath("//div[@class = 'container']/a[1]")).click();
		  driver.findElement(By.xpath("//span[text()='Soccer']")).click();
		  driver.findElement(By.xpath("//span[text()='Scores']//parent::a")).click();
		  driver.findElement(By.xpath("//div[@class ='calendar-container']/a")).click()
		  ; int today = Integer.parseInt((driver.findElement(By.
		  xpath("//td[@class ='today active day']")).getText()));
		  System.out.println(today);
		 

		Select cource = new Select(driver.findElement(By.id("dropdowm-menu-1")));
		cource.selectByVisibleText("C#");
		Select ide = new Select(driver.findElement(By.id("dropdowm-menu-2")));
		ide.selectByVisibleText("Maven");
		
		Actions action = new Actions(driver);
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		action.dragAndDrop(source, target).build().perform();
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class ='dropdown hover']"))).build().perform();
		driver.findElement(By.xpath("//a[text() ='Link 1']")).click();
		driver.switchTo().alert().accept();
		
		
		
		}
}
