package com.sample;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NaukriResumeUpload {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://www.naukri.com/");
		System.out.println("Current URL is :" + driver.getCurrentUrl());
		System.out.println("Title is :" + driver.getTitle());

		driver.findElement(By.xpath("//*[text()='Login']")).click();

		// wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// enter username
		driver.findElement(By.xpath("//*[contains(@placeholder,'Enter your active Email ID / Username')]")).clear();
		driver.findElement(By.xpath("//*[contains(@placeholder,'Enter your active Email ID / Username')]"))
				.sendKeys("himanshunya@gmail.com");

		// enter password
		driver.findElement(By.xpath("//*[contains(@placeholder,'Enter your password')]")).clear();
		driver.findElement(By.xpath("//*[contains(@placeholder,'Enter your password')]")).sendKeys("Jaimatadi!12345");

		// click on Submit
		driver.findElement(By.xpath("//*[contains(@class,'btn-primary loginButton')]")).click();

		// Validate home Page
		System.out.println("User On HomePage :" + driver.getTitle());

		// user click on view profile button
		driver.findElement(By.xpath("//*[@class='view-profile-wrapper']/a")).click();

		System.out.println("User Is on Profile Page :" + driver.getTitle());

		// Click on Update resume
		driver.findElement(By.xpath("//*[@value='Update resume']")).click();
		// Define relative path
				String resumePath = System.getProperty("user.dir") + "/resume/Himanshu_Test_Architect_8884407702_Resume.pdf";

				// Verify if file exists
				File file = new File(resumePath);
				if (!file.exists()) {
					System.out.println("File NOT found! Check the path: " + resumePath);
					return;
				}

				WebElement uploadElement = driver.findElement(By.xpath("//input[@type='file']"));
				uploadElement.sendKeys(resumePath);
				
				WebElement successMessage = wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Resume has been successfully uploaded')]")));

				// Verify Success Message
				if (successMessage.isDisplayed()) {
					System.out.println("Resume uploaded successfully: " + successMessage.getText());
				} else {
					System.out.println("Resume upload failed.");
				}

				driver.findElement(By.xpath("//*[@alt='naukri user profile img']")).click();
				driver.findElement(By.xpath("//*[text()='Logout']")).click();

				Thread.sleep(5000);
				driver.quit();
	}

}
