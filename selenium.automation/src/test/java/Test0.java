import org.apache.commons.mail.EmailException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.Util;

public class Test0 {
		
		String url = "https://www.amazon.com";
		String search1 = "Lego";
		String search2 = "hula hoop";
		String search3 = "texas";
		String screenshotName = "Amazon";
		
		
		By searchField = new By.ByCssSelector("#twotabsearchtextbox");
//		By item = new By.ByCssSelector("#result_3 > div");
//		By addButton = new By.ByCssSelector("#add-to-cart-button");
		By cartCount = new By.ByCssSelector("#nav-cart-count");
		By cart = new By.ByCssSelector("#nav-cart");
		By price = new By.ByClassName("sc-price-sign");
		By delete = new By.ByClassName("sc-action-delete");
		WebDriver driver;
		
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
		
		@After
		public void close(){
			System.out.println("Quitting driver...");
			driver.quit();
		}
		
		
	  @Test
	  public void AmazonTestYana() {
		  
		  driver.get(url);
		  
		  WebDriverWait wdWait = new WebDriverWait(driver, 15);
//		  wdWait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
		  
		  Util.DoSearch(driver,search1);
		  Util.DoSearch(driver,search2);
		  Util.DoSearch(driver,search3);

//		  driver.findElement(searchField).sendKeys(search2 + Keys.ENTER);
//		  List<WebElement> searchResults2 = driver.findElements(item);
//		  searchResults2.get(0).click();
//		  driver.findElement(addButton).click();
		  
		 
		  
//		  driver.findElement(searchField).sendKeys(search3 + Keys.ENTER);
//		  List<WebElement> searchResults3 = driver.findElements(item);
//		  searchResults3.get(0).click();
//		  driver.findElement(addButton).click();
		  
		  int numberItems = Integer.parseInt(driver.findElement(cartCount).getText());
		  Assert.assertEquals(numberItems, 3);
		  System.out.println("Number of added items: "+numberItems);
		  
		  driver.findElement(cart).click();
		  
		 // driver.findElement(cart).click();
		  
		  String orderPrice = driver.findElement(price).getText();
		  
		  Util.takeScreenShot(driver, screenshotName);
		  try {
			Util.sendMail(screenshotName, orderPrice);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  driver.findElement(delete).click();
		  wdWait.until(ExpectedConditions.visibilityOfElementLocated(delete));
		  try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  driver.findElement(delete).click();
		  wdWait.until(ExpectedConditions.visibilityOfElementLocated(delete));
		  try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  driver.findElement(delete).click();
		  try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  String items = driver.findElement(cartCount).getText();
		  int numberItems1 = Integer.parseInt(items);
		  Assert.assertEquals(numberItems1, 0);
		  
		  
		  Util.takeScreenShot(driver, "Amazon2");
		  try {
			Util.sendMail("Amazon2", items);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	  
//		  public void DoSearch(String search){
//			  
//		  driver.findElement(searchField).sendKeys(search + Keys.ENTER);
//		  List<WebElement> searchResult = driver.findElements(item);
//		  searchResult.get(0).click();
//		  driver.findElement(addButton).click();
//		  
	//  }
		  
	}}