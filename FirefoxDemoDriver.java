/**
 *   As investigating the incompatibilities between the TestNG and latest version of Eclipse may be too broad
 * for this exercise, I will use a standard Java class to drive the automation of the exercise
 * 
 * Work log - Automation Demo
 * 
 * 3/27/20 - Recovered last working files, installed IDE, Maven plugin
 * 3/28/20 - Created workflow to attempt to automate by 3/31/20
 * 3/29/20 - Incompatible IDE version vs TestNG plugin (either downgrade Eclipse or do without TestNG)
 * 3/30/20 - Working offsite, HTTPS connections not allowed - switching target websites
 */

package org.MPozIbleFeats;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Miguel A Pozos, Sr
 */
class FirefoxDemoDriver {

  /**
   * @param args
   */
  public static void main(String[] args) 
  {
	 // This section contains parameters needed in later tests
	 WebDriver driver;
	 String webApp = "http://www.cnn.com";
	 String user = "capnmayhem@yahoo.com";
	 String pw = "M@dn3ss!";
	 ArrayList<String> testsSucceeded = new ArrayList<String>();
	 ArrayList<String> testsFailed = new ArrayList<String>();
	 Integer successfulTests = 0;
	 Integer failedTests = 0;

	 // This section is specific to the driver being used
	 System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
	 FirefoxOptions options = new FirefoxOptions();
	 //options.setHeadless(true); // commented out for demo purposes - in production, headless is typically true
	 
	 // Starting the actual browser section	
	 driver = new FirefoxDriver(options);
	 WebDriverWait wait = new WebDriverWait(driver, 30);
	 driver.get(webApp);  
    
	 // 1st Test - did page load properly? Testing the expected title of a website
	 String title = driver.getTitle();
	 if (title.contains("CNN - Breaking News, Latest News and Videos"))
	 {
		// System.out.println("Title Test Successful"); // gather all successful messages, print at end
		testsSucceeded.add("Title Test");
		successfulTests++;
	 }
	 else
	 {
		// System.out.println("Title Test Failed"); // gather all failed messages, print at end
		testsFailed.add("Title Test");
		failedTests++;
	 }
	 
	 // 2nd Test - is the User Icon to log in present?  Can it be clicked? Its id is account-icon-button
	 try
	 {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-icon-button")));
	   WebElement accountButton;
	   accountButton = driver.findElement(By.id("account-icon-button"));
	   accountButton.click();
		  
	   // System.out.println("User Icon present and can be clicked"); // gather all successful messages, print at end
	   testsSucceeded.add("User Icon present, can be clicked");
	   successfulTests++;
	 }
	 catch (Exception e) 
	 {
		// System.out.println("User Icon could not be found or clicked"); // gather all failed messages, print at end
		testsFailed.add("User Icon could not be found or clicked");
		failedTests++;
	 }
	 
	 // 3rd Test - can a username and password be provided for login.  The userName's field name is loginEmail and passwd's name is loginPassword.
	 // The submit button's css finder is .kDWOQ and its xpath locator is //div[@id='login-modal-content']/form/button/span 
	 try
	 {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("loginEmail")));
	   WebElement userNameField;
	   userNameField = driver.findElement(By.name("loginEmail"));
	   userNameField.sendKeys(user);
		   
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("loginPassword")));
	   WebElement passwordField;
	   passwordField = driver.findElement(By.name("loginPassword"));
	   passwordField.sendKeys(pw);
		   
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='login-modal-content']/form/button/span")));
	   WebElement submitButton;
	   submitButton = driver.findElement(By.xpath("//div[@id='login-modal-content']/form/button/span"));
	   submitButton.click();
		  
	   // System.out.println("UserName, password fields present and can receive data, submit can be clicked"); // gather all successful messages, print at end
	   testsSucceeded.add("UserName, password fields present and can receive data, submit can be clicked");
	   successfulTests++;
	 }
	 catch (Exception e) 
	 {
	   // System.out.println("UserName, password fields could not be found or could not receive data / submit could not be clicked"); // gather all failed messages, print at end
		testsFailed.add("UserName, password fields could not be found or could not receive data / submit could not be clicked");
		failedTests++;
	 }

	 // Final section - report number of successes and failures and (potentially) close the browser
	 System.out.println("Test Suite Complete!");
	 
	 Iterator<String> successful = testsSucceeded.iterator(); 
	 String successfulTest;
	 Iterator<String> failed = testsFailed.iterator(); 
	 String failedTest;
	 
	 if (successfulTests > 0)
	 {
		System.out.println("Successful Tests - " + successfulTests.toString());
	 
		while (successful.hasNext()) 
		{
		  successfulTest = successful.next();
		  System.out.println(successfulTest);
		}
	 }
	 else {
		System.out.println("Sadly, no tests Succeeded in this Suite! :'-(");
	 }
	 if (failedTests > 0)
	 {
		System.out.println("Failed Tests: " + failedTests.toString());
		
		while (failed.hasNext()) 
		{
		  failedTest = failed.next();
		  System.out.println(failedTest);
		}
	 }
	 else
	 {
		System.out.println("Fortunately, no tests Failed in this Suite! :-)");
	 }
	 
	 driver.quit(); // may be commented out during work-in progress sessions
  }
}