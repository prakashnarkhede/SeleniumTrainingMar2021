package TrainingTests;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElementsDemo {

	WebDriver driver;
	
	@BeforeMethod
	public void setupMethod() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://demo.automationtalks.com/DemoQAapplication/DemoQa/demoqa.com/droppable/index.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	//@Test
	public void testRadioButton_simpleWay() {
		System.out.println("Testing radio button test");

		//select radio button
		String radioButtonToBeSelected = "Female";

		if(radioButtonToBeSelected.equalsIgnoreCase("Male")) {
			driver.findElement(By.xpath("//input[@name='optradio' and @value='Male']")).click();
		} else if (radioButtonToBeSelected.equalsIgnoreCase("Female")) {
			driver.findElement(By.xpath("//input[@name='optradio' and @value='Female']")).click();
		}
		WebElement buttonCheck =  driver.findElement(By.id("buttoncheck"));
		buttonCheck.click();
		WebElement selectedButton = driver.findElement(By.xpath("//button[@id='buttoncheck']/parent::p/following-sibling::p"));
		String str = selectedButton.getText();
		//Radio button 'FeMale' is checked
		String expectedString = "Radio button '"+radioButtonToBeSelected+"' is checked";
		Assert.assertEquals(str, expectedString);
	}

	//@Test
	public void testRadioButton_BetterWay() {
		System.out.println("Testing radio button test");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.automationtalks.com/SE-ESY/www.AutomationTalks.com/test/basic-radiobutton-demo.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		//select radio button
		String radioButtonToBeSelected = "Female";

		List<WebElement> webList = driver.findElements(By.xpath("//input[@name='optradio']/parent::label"));
			
			for (WebElement we : webList) {
				String currentRadioText = we.getText();
				if(currentRadioText.equalsIgnoreCase(radioButtonToBeSelected)) {
					we.click();
				}
			}
			
			WebElement buttonCheck =  driver.findElement(By.id("buttoncheck"));
			buttonCheck.click();
			WebElement selectedButton = driver.findElement(By.xpath("//button[@id='buttoncheck']/parent::p/following-sibling::p"));
			String str = selectedButton.getText();
			//Radio button 'FeMale' is checked
			String expectedString = "Radio button '"+radioButtonToBeSelected+"' is checked";
			Assert.assertEquals(str, expectedString);

			}
	
	//check if "Get checked value" button is present or not
//	@Test
	public void checkIfPresent() {
		System.out.println("Testing radio button test");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.automationtalks.com/SE-ESY/www.AutomationTalks.com/test/basic-radiobutton-demo.html");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//webelement for "Get checked value" button
		WebElement button =  driver.findElement(By.xpath("//button[@id='buttoncheck']"));
		boolean isButtonPresent = button.isDisplayed();
		System.out.println("isButtonPresent: "+isButtonPresent);

	}
	
	
	//@Test
	public void checkSingleCheckBox() {	
	//single checkbox click
	//	driver.findElement(By.id("isAgeSelected")).click();
		String checkBoxToClick = "Option 1;Option 2;Option 3";
		String[] checkBoxToClick_arr =  checkBoxToClick.split(";");
		System.out.println("checkBoxToClick: "+checkBoxToClick);
		System.out.println("checkBoxToClick_arr: "+checkBoxToClick_arr);
		
		for(int i = 0; i < checkBoxToClick_arr.length; i++) {
			String  ToCheck = checkBoxToClick_arr[i];
			List<WebElement> webelementCheckList = driver.findElements(By.xpath("//div[text()='Multiple Checkbox Demo']/following-sibling::div//label"));
			for (WebElement we : webelementCheckList) {
				String check = we.getText();
				if(check.equalsIgnoreCase(ToCheck)) {
					we.click();
				}
			}
		}
	}
	
	
//	@Test
	public void dropDownDemo() {
		//http://demo.automationtalks.com/DemoQAapplication/DemoQa/demoqa.com/selectmenu/index.html
		//Simple dropdown -- Coded in Select tag
		WebElement selectProgLang_dd = driver.findElement(By.id("programming-languages"));
		Select os = new Select(selectProgLang_dd);
		System.out.println("Is multselect dropdown? "+ os.isMultiple());
		System.out.println("All available options are: "+ os.getOptions());
		os.selectByVisibleText("PHP");
		
		System.out.println("First selected option is: "+os.getFirstSelectedOption());
		//os.selectByValue("PHP");
		//os.selectByIndex(5);
		
		//multiselect dropdown
		WebElement multiselectDD = driver.findElement(By.id("selenium_suite"));
		Select multi_dd = new Select(multiselectDD);
		//verify if dropdown is multiselect
		System.out.println("Is multselect dropdown? "+ multi_dd.isMultiple());
		System.out.println("All available options are: "+ multi_dd.getOptions());

		multi_dd.selectByVisibleText("Selenium Grid");
		multi_dd.selectByVisibleText("Limitations");
		
		System.out.println("All selected option is: "+multi_dd.getAllSelectedOptions());

		//use the  say way to get data from string (semi colon seperate) for multiple selects
		
		
		//Jquery dropdown
		//click on dropdown so that popup is visible
		driver.findElement(By.id("salutation-button")).click();
		
		//get all webelemenets from popmenu & store in list
		String valueToBeSelected = "Other";
		WebElement dd_ele_toBeSelected = driver.findElement(By.xpath("//ul[@id='salutation-menu']//li/div[text()='"+valueToBeSelected+"']"));
		dd_ele_toBeSelected.click();
		
		//iterate list, get the text our of webelement, if text  = expexted, then click	 -- to try
//		String indexes = "1;3;5";
//		String[] s  = indexes.split(";");
//		for (String indexValue : s) {
//			//indexValue ==> String
//			multi_dd.selectByIndex(Integer.valueOf(indexValue));
//			
//		}
		
		
	}
	
	//@Test
	public void handleAlerts() throws InterruptedException {
		//http://demo.automationtalks.com/DemoQAapplication/DemoQa/demoqa.com/AlertMessage/index.html
		
		//generate alert for demo purpose
		driver.findElement(By.xpath("//button[text()='Generate Confirm Box']")).click();
		Thread.sleep(3000);
		//handle alert
		Alert al = driver.switchTo().alert();
		System.out.println("Alert text is: "+al.getText());
	//	al.accept();
		al.dismiss();
	//	al.sendKeys("");
		
		//get text from alert
		//accept alert
		//or
		//dissmis alert
		//enter text to alerts
		
		
	}
	
	
	//@Test
	public void testFrames() {
		//http://demo.automationtalks.com/DemoQAapplication/DemoQa/demoqa.com/frames/index.html
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Selenium");
		
		//click on checkbox
		//jump to frame
		driver.switchTo().frame("frame3");
		driver.findElement(By.id("a")).click();
		
		//jump back to main page
		driver.switchTo().parentFrame();
		//verify title
		String heading = driver.findElement(By.xpath("//h1[@class='entry-title']")).getText();
		Assert.assertEquals(heading, "DemoHeading");
	}
	
	
	//@Test
	public void TableHandlingDemo() {
		//http://demo.automationtalks.com/SE-ESY/www.AutomationTalks.com/test/table-sort-search-demo.html
		String empName = "C. Kelly";
		////table[@id='example']/tbody/tr/td[text()='C. Kelly']/following-sibling::td[1] --> designation
		////table[@id='example']/tbody/tr/td[text()='C. Kelly']/following-sibling::td[5] --> salary
		
		//verify employees designation
		String e_desg = driver.findElement(By.xpath("//table[@id='example']/tbody/tr/td[text()='"+empName+"']/following-sibling::td[1]")).getText();
		Assert.assertEquals(e_desg, "Senior Javascript Developer");
		
		//verify employees salary
		String e_salary = driver.findElement(By.xpath("//table[@id='example']/tbody/tr/td[text()='"+empName+"']/following-sibling::td[5]")).getText();
		Assert.assertEquals(e_salary, "$433,060/y");
		
		
		//total number of rows in table
		List<WebElement> row_WebElement_List = driver.findElements(By.xpath("//table[@id='example']/tbody/tr"));
		int rowCount = row_WebElement_List.size();
		System.out.println("rowCount: "+rowCount);
		
		
		//total col count
		List<WebElement> col_WebElement_List = driver.findElements(By.xpath("//table[@id='example']/thead/tr/th"));
		int colCount = col_WebElement_List.size();
		System.out.println("colCount: "+colCount);
		
	}
	
	//@Test
		public void testTakesScreenshot() throws IOException {
			//http://demo.automationtalks.com/DemoQAapplication/DemoQa/demoqa.com/frames/index.html
			driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys("Selenium");
			
			//take screenshot
			File f1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File newObject = new File("C:\\Users\\prakash.narkhede\\OneDrive - Wolters Kluwer\\Desktop\\selenium\\demo.png");
			FileUtils.copyFile(f1, newObject);		 
	}
		
		@Test(enabled = true)
		public void testActions() {
			//http://demo.automationtalks.com/DemoQAapplication/DemoQa/demoqa.com/frames/index.html
			
			WebElement ele_htmlCOntractFOrm = driver.findElement(By.linkText("HTML contact form"));
			
			Actions act = new Actions(driver);
		//	act.click(ele_htmlCOntractFOrm).perform();	
			//act.contextClick(ele_htmlCOntractFOrm).build().perform();	
			
			//drag and drop
			WebElement srcEle = driver.findElement(By.id("draggable"));
			WebElement destEle = driver.findElement(By.id("droppable"));
			act.dragAndDrop(srcEle, destEle).perform();
			
		}

}
