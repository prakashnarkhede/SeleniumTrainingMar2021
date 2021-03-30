package TrainingTests;

import static org.testng.Assert.assertEquals;

import java.util.Set;
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

public class windowSwitch {
WebDriver driver;
	
	@BeforeMethod
	public void setupMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://demo.automationtalks.com/DemoQAapplication/DemoQa/demoqa.com/automation-practice-switch-windows/index.html");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void windowSwithTest() throws InterruptedException {
		//
		driver.findElement(By.xpath("//button[text()='New Browser Tab']")).click();
		
		//find window id ==> find for both, mainwindow & child window
		String parentWindow = driver.getWindowHandle();
		System.out.println("parentWindow: "+parentWindow);
		
		Set<String> windowsSet = driver.getWindowHandles();
		System.out.println("windowsSet: "+windowsSet);
		
		String childWindow = "";
		for (String window : windowsSet) {
			System.out.println("window: "+window);
			if(window != parentWindow) {
				//then this is your child window
				childWindow = window;
			}
		}
		
		//swith to new window
		driver.switchTo().window(childWindow);
		driver.findElement(By.name("q")).sendKeys("Demo Search");
		driver.findElement(By.name("btnK")).click();

	//	driver.close(); // at this point, it will close only child window
		
		//parent window
		driver.switchTo().window(parentWindow);
		assertEquals(driver.findElement(By.xpath("//h1")).getText(), "Automation Practice Switch Windows");
		
		//driver.close(); //will close current window where driver is focused, that is, parent window
		Thread.sleep(3000);
		driver.quit(); //close all windows launched by currnet driver instance
	}
}
