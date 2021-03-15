import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Salesforce {

	public static void main(String[] args) throws InterruptedException, AWTException {
		// Start ChromeDriver server
		WebDriverManager.chromedriver().setup();

		// Chrome options to disable notification
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		// Launch the Chrome browser
		ChromeDriver driver = new ChromeDriver(options);

		// Load the url(Salesforce)
		driver.get("https://login.salesforce.com");

		// Maximize the browser
		driver.manage().window().maximize();

		// Implicitly Wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Login Credentials
		driver.findElementById("username").sendKeys("nupela@testleaf.com");
		driver.findElementById("password").sendKeys("Bootcamp$123");
		driver.findElementById("Login").click();

		Thread.sleep(3000);
		// Click App Launcher Icon on the left
		driver.findElementByXPath("//div[@class='slds-icon-waffle']").click();

		// click on viewAll
		driver.findElementByXPath("//button[text()='View All']").click();

		// Click on Service Console
		driver.findElementByXPath("//p[text()='Service Console']").click();
		// click on dropdown next to Dashboard
		Thread.sleep(5000);
		driver.findElementByXPath("//button[@title='Show Navigation Menu']").click();
		Actions builder = new Actions(driver);
		// click on files
		driver.findElementByXPath("//span[text()='Files']").click();

		// Click on upload files
		WebElement uploadButton = driver.findElementByXPath("//div[text()='Upload Files']");
		builder.moveToElement(uploadButton).click().perform();

		Thread.sleep(10000);

		// upload files
		StringSelection stringSelection = new StringSelection("C:\\Users\\dell\\Desktop\\Day3 java Questions.txt");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		// Paste it using Robot class
		Robot robot = new Robot();

		// Enter to confirm it is uploaded
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(5000);
		// finish download
		WebElement doneClick = driver.findElementByXPath("//span[text()='Done']");
		builder.moveToElement(doneClick).click().perform();
		Thread.sleep(3000);

		// Click on DropDown for the newly uploaded file
		driver.findElementByXPath("//div[@data-aura-class='forceVirtualAction']/a").click();
		Thread.sleep(3000);

		// select View File Details
		driver.findElementByXPath("//a[@title='View File Details']").click();

		// Verify the file name and extension of the newly uploaded file
		String fileName = driver.findElementByXPath("//div[text()='File']/following::span").getText();
		System.out.println(fileName);
		String fileExtension = driver.findElementByXPath("//span[@title='File Extension']/following::span").getText();
		System.out.println(fileExtension);
		Assert.assertEquals(fileName, "Day3 java Questions");
		Assert.assertEquals(fileExtension, "txt");

		// Close the file window tab
		driver.findElementByXPath(
				"//div[@class='close slds-col--bump-left slds-p-left--none slds-context-bar__icon-action ']").click();

		// Click on the latest modified item link
		driver.findElementByXPath("(//div[@class='slds-truncate']//span)[1]").click();

		// Click on Share
		driver.findElementByXPath("//button[@title='Share']").click();

		// Click on search user
		driver.findElementByXPath("//input[@title='Search People']").click();

		// select the Derrick Dsouza
		driver.findElementByXPath("//div[text()='Derrick Dsouza']").click();

		// Verify the Error message "Can't share file with the file owner." is displayed
		String errorMessage = driver.findElementByXPath("//li[@class='form-element__help']").getText();

		Assert.assertEquals(errorMessage, "Can't share file with the file owner.");

		// Cancel share by clicking on Cancel button
		driver.findElementByXPath("(//span[text()='Cancel'])[2]").click();

		// Close the item dialog
		driver.findElementByXPath("//button[@title='Close']").click();

		// Click on DropDown for the newly uploaded file
		driver.findElementByXPath("//li[@class='oneActionsDropDown']//a").click();
		// select Delete
		driver.findElementByXPath("//a[@title='Delete']").click();
		// Confirm Delete
		driver.findElementByXPath("//span[text()='Delete']").click();
		Thread.sleep(3000);
		//Verify the success message displayed for the delete
		String text = driver.findElementByXPath("//span[@class='toastMessage slds-text-heading--small forceActionsText']").getText();
		System.out.println(text);
	}

}
