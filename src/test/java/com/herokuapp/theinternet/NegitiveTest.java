package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegitiveTest {

	@Test
	public void incorrectUsernameTest() {
		System.out.println(" starting incorrect username test");
		// create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();
		
		String url= "http://the-internet.herokuapp.com/login";
		// open test page
		driver.get(url);
		System.out.println("Page is opened");
		
		// enter incorrect username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("incorrectUsername");
//		username.sendKeys("tomsmith"); // to test failure of this test
		
		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		
		// click the login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
		// verifications
		WebElement errorMessage = driver.findElement(By.id("flash")); // this id can be known by inspecting the webpage
		String expectedErrorMessage = "Your username is invalid!";
		String actualErrorMessage = errorMessage.getText();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), 
				"Actual error message does not contain expected.\nActual: "
				+actualErrorMessage+"\nExpected:"+expectedErrorMessage);
		
		// close browser
		driver.quit();
	}
	
	@Test
	public void incorrectPasswordTest() {
		System.out.println(" starting incorrect password test");
		// create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();
		
		String url= "http://the-internet.herokuapp.com/login";
		// open test page
		driver.get(url);
		System.out.println("Page is opened");
		
		// enter incorrect username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		
		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("incorrectPassword");
		
		// click the login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		
		// verifications
		WebElement errorMessage = driver.findElement(By.id("flash")); // this id can be known by inspecting the webpage
		String expectedErrorMessage = "Your password is invalid!";
		String actualErrorMessage = errorMessage.getText();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), 
				"Actual error message does not contain expected.\nActual: "
				+actualErrorMessage+"\nExpected:"+expectedErrorMessage);
		
		// close browser
		driver.quit();
	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
