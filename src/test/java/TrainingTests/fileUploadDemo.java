package TrainingTests;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class fileUploadDemo {
WebDriver driver;
	
	@BeforeMethod
	public void setupMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://demo.automationtalks.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void windowSwithTest() throws InterruptedException, AWTException {
	//type = file
		//http://demo.automationtalks.com/DemoQAapplication/DemoQa/demoqa.com/keyboard-events/index.html
//	WebElement browseButton = driver.findElement(By.id("browseFile"));
//	browseButton.sendKeys("C:\\Users\\prakash.narkhede\\OneDrive - Wolters Kluwer\\Desktop\\image.PNG");
	
	//type!= file
		driver.findElement(By.id("nextenterinsurantdata")).click();
		
		//put path of image on clipboard --> to do cntrl-V
		String filePath = "C:\\Users\\prakash.narkhede\\OneDrive - Wolters Kluwer\\Desktop\\image.PNG";
		StringSelection str = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

		//click on open button
		driver.findElement(By.id("open")).click();
		
		
		Thread.sleep(4000);

		//robot class
		Robot rb = new Robot();
		//Press control V
		rb.keyPress(KeyEvent.VK_CONTROL);
		rb.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		rb.keyRelease(KeyEvent.VK_CONTROL);
		rb.keyRelease(KeyEvent.VK_V);
		
		//hit enter button
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);

	}
}
