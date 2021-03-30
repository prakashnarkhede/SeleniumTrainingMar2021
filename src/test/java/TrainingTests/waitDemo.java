package TrainingTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class waitDemo {
WebDriver driver;
	
	@BeforeMethod
	public void setupMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://demo.automationtalks.com/WebDriverWait%20Demo/practice/wait.html");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void waitDemoTest() throws InterruptedException {
		//generate alert
//		driver.findElement(By.id("alert")).click();
//		//webdriver wait
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		wait.until(ExpectedConditions.alertIsPresent());	
//		Alert al = driver.switchTo().alert();
//		al.accept();
		
		//generate button after 10 sec forcefully
		driver.findElement(By.id("display-other-button")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 7);
		
		WebElement button = driver.findElement(By.id("hidden"));

		wait.until(ExpectedConditions.visibilityOf(button));
		button.click();
		
	}
}
