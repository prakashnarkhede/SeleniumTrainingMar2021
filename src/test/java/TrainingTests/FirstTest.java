package TrainingTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTest {

	public static void main(String[] args) throws InterruptedException {
		//open chrome browser
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		//navigate to URL
		driver.get("http://sampleapp.tricentis.com/101/index.php");
		//maximize
		driver.manage().window().maximize();
		//synchronization setting
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//get the title
		String Actualtitle = driver.getTitle();
		System.out.println("Actual title is: "+Actualtitle);
		
		if(Actualtitle == "Tricentis Vehicle Insurance") {
			System.out.println("Test is passed");
		} else {
			System.out.println("Test is failed");
		}
		
		//verify if title is correct
		//Assertion in TestNG
		
		
		//click on Motorcycle Link
		driver.findElementById("nav_motorcycle").click();
		driver.findElementById("cylindercapacity").sendKeys("100");
		driver.close();

	}

}
