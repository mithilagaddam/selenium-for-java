package com.herokuapp.theinternet;


import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
	@Test
	public void loginTest() throws Exception {
		System.out.println(" starting login test");
		// create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.29.166:4444/wd/hub"),DesiredCapabilities.chrome());
		
		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();
		
		String url= "http://the-internet.herokuapp.com/login";
		// open test page
		driver.get(url);
		System.out.println("Page is opened");
		
		// sleep for 3 seconds
		sleep(2000);
		
		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(1500);
		
		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(3000);
		
		// click the login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		sleep(5000);
		
		// verifications
		
		// new url
		String expectedurl="http://the-internet.herokuapp.com/secure";
		String actualurl = driver.getCurrentUrl();
		Assert.assertEquals(actualurl, expectedurl,"Actual page url is not the same as expected url"); // if both are not same then that error occurs
		
		// logout button is visible
		WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
		Assert.assertTrue(logoutButton.isDisplayed(), "Logout button not visible");
		
		// successful login message
		WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
		String expectedMessage = "You logged into a secure area";
		String actualMessage = successMessage.getText();
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain expected message.\nActual message: "+actualMessage+
				"\nExpected Message: "+expectedMessage);
		
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
