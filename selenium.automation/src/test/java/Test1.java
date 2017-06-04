

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {


	WebDriver driver;
	String url = "http://www.redbox.com/";
	String searchString = "Zootopia";
	By searchIcon = new By.ByCssSelector("#searchdigital0_SearchIcon");
	By searchBox = new By.ByCssSelector("#searchdigital0_SearchBox");
	By numberResults = new By.ByCssSelector(".title-box-odopod a");
	By VideoDuration = new By.ByCssSelector(".subhead-text.body-3");
	By languages = new By.ByXPath("//*[@id='titledetailscore0_Widget']/div[3]/div/div[2]/div[3]/div/div[2]/div[1]/div[3]/div/div"); 
	By locator = new By.ByCssSelector("#searchresultscore0_Breadcrumbs > span");

	String javaScript = ".digital-details-subhead div:contains('min')";
	@Before
	public void setup(){
		System.out.println("Starting driver...");
		int browser = 3;

		switch (browser){
		case 1:
			driver = new ChromeDriver();
			break;

		case 2:
			driver = new FirefoxDriver();
			break;

		case 3:
			driver = new EdgeDriver();
			break;
			
		case 4:
			driver = new InternetExplorerDriver();
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


	@Test
	public void test() {

		driver.get(url);
		WebDriverWait wdWait = new WebDriverWait(driver,10);
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(searchIcon));

		WebElement weSearchIcon = driver.findElement(searchIcon);
		weSearchIcon.click();
		WebElement weSearchBox = driver.findElement(searchBox);
		weSearchBox.sendKeys(searchString + Keys.ENTER);

		//	wdWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> searchResults = driver.findElements(numberResults);
		int elementsCount = searchResults.size();
		int expected = 1;
		Assert.assertEquals("More than one result ", expected, elementsCount);

//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		searchResults.get(0).click();

		List<WebElement> movieData = driver.findElements(VideoDuration);
		String duration = movieData.get(0).getText().replaceAll("[^0-9:]","");

		Assert.assertEquals("Duration is different from expected ", "1:48", duration);
		//		JavascriptExecutor jse = (JavascriptExecutor)driver;
		//		String duration = (String)jse.executeScript("$('.digital-details-subhead div:contains('min')').text().replace(' min','')");
		//
		//		String textDuration = (String) jse.executeScript("$('.digital-details-subhead div:contains('min')').text()");

		//		String duration = (String)textDuration.getText().replaceAll("[^0-9:]","");

		List<WebElement> languagesData = driver.findElements(languages);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Selenium got duration of the movie as: "+duration);

		for (int i =0; i<languagesData.size();i++){
			System.out.println("Subtitles are avaible in: "+languagesData.get(i).getText());
		}
	}

}
