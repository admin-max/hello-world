package framework;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FrameworkBase {
	
	public WebDriver driver;
	@Before
	public void setup(){
		System.out.println("Starting driver...");
		int browser = 1;
		
		switch (browser){
		case 1:
		driver = new ChromeDriver();
		break;
		
		case 2:
		driver = new FirefoxDriver();
		break;
		}	
		driver.manage().window().maximize();
	}
	//Close the browser
	@After
	public void close(){
		System.out.println("Quitting driver...");
		driver.quit();
	}
	}


