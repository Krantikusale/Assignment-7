package assignment_7;
package assignment7;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Task1 {
	WebDriver driver;
	@Test(description = "Verify required error message is shown for both username and password",priority = 1,dataProvider = "data-provider",dataProviderClass = DP.class)
	public void requiredfields(String browser) {
		//WebDriver driver = new ChromeDriver();
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		//open the website
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//maximize the window
		driver.manage().window().maximize();
		
		//wait for page to load completely
		driver.manage().timeouts().pageLoadTimeout( Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
		
		WebElement loginButton = driver.findElement(By.xpath("//button"));
		loginButton.click();
		
		WebElement usernameErrorTest = driver.findElement(By.xpath("//input[@name='username']//parent::div//following::span[1]"));
		Assert.assertTrue(usernameErrorTest.getText().equals("Required"));
		
		WebElement username = driver.findElement(By.xpath("//input[@name='username']//parent::div"));
		System.out.println("Boarder value is::"+username.getCssValue("border-radius"));
		
		WebElement password = driver.findElement(By.xpath("//input[@name='password']//parent::div"));
		System.out.println("Boarder value is::"+password.getCssValue("border-radius"));
		
		driver.quit();
		

	}
	
	
	@Test(description = "Verify required error message is shown for when password is empty",priority = 2,dataProvider = "data-provider",dataProviderClass = DP.class)
	public void passwordIsRequired(String browser) {
		//WebDriver driver = new ChromeDriver();
				if(browser.equals("chrome")) {
					driver = new ChromeDriver();
				}else if(browser.equals("firefox")) {
					driver = new FirefoxDriver();
				}
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		//open the website
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//maximize the window
		driver.manage().window().maximize();
		
		//wait for page to laod completely
		driver.manage().timeouts().pageLoadTimeout( Duration.ofSeconds(40));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//button")).click();
		
		WebElement passwordErrorMessage = driver.findElement(By.xpath("//input[@name='password']//parent::div//following::span[1]"));
		Assert.assertTrue(passwordErrorMessage.getText().equals("Required"));
		
		driver.quit();
		
		
	}
	
	@Test(description = "Verify error message with invalid credentials",priority = 3,dataProvider = "data-provider",dataProviderClass = DP.class)
	public void invalidCredentials(String browser) {
		//WebDriver driver = new ChromeDriver();
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		//open the website
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//maximize the window
		driver.manage().window().maximize();
		
		//wait for page to laod completely
		driver.manage().timeouts().pageLoadTimeout( Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Mukesh");
		
		driver.findElement(By.xpath("//button")).click();
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='alert']")));
		WebElement errorMessage = driver.findElement(By.xpath("//p[text()='Invalid credentials']"));
		Assert.assertTrue(errorMessage.getText().contains("Invalid credentials"));
		
		driver.quit();
		
		
	}
	
	@Test(description = "Verify login and logout",priority = 4,dataProvider = "data-provider",dataProviderClass = DP.class)
	public void verifyLoginLogout(String browser) {
		//WebDriver driver = new ChromeDriver();
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		//open the website
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		//maximize the window
		driver.manage().window().maximize();
		
		//wait for page to laod completely
		driver.manage().timeouts().pageLoadTimeout( Duration.ofSeconds(30));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='username']")));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button")).click();
		
		Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='oxd-userdropdown-tab']")));
		driver.findElement(By.xpath("//p[contains(text(),'Paul Collings')]")).click();
		//wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//ul[@role='menu']"), 1));
		driver.findElement(By.xpath("//a[contains(@href,'logout')]")).click();
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));
		driver.quit();
		
		
		
	}
  

}