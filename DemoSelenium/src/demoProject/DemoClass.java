package demoProject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoClass {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\abayes\\Documents\\selenium-java-3.141.59\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		String url =  "https://accounts.google.com/signin";
		driver.get(url);
		driver.findElement(By.id("identifierId")).sendKeys("abayes@apostek.com", Keys.ENTER);
		Thread.sleep(3000);
		//driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
		driver.findElement(By.xpath("//input[@class='whsOnd zHQkBf']")).sendKeys("abayes@kil", Keys.ENTER);
		//driver.findElement(By.xpath("//span[@class='RveJvd snByac']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='gb']/div[1]/div[1]/div[2]/div[5]/div[1]/a/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='gb_71']")).click();
		driver.findElement(By.id("gb_71")).click();
	}

}
