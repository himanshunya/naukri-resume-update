package com.sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();;
		driver.get("https://innernetworld.com");
		  System.out.println("Title: " + driver.getTitle());
		driver.close();
		
		
	}

}
