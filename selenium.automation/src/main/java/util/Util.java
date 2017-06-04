package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

public static void takeScreenShot(WebDriver driver, String fileName) {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourse = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(sourse, new File("./Screenshots/"+fileName+".png"));
		} catch (IOException e) {
			System.out.println("Unable to take screenshot "+e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Screeenshot taken");
	}

public static void sendMail(String fileme, String price) throws EmailException{
	
	String message = null;
	if (!(price.equals("0"))){
	message = "Hello UserName, \nThis is a test mail from Selenium with first test results \nTotall price for all staff was: "+price+"\n Have a great day!";
	}
	else {
		message = "Hello UserName, \n This is a test mail from Selenium with second test results \n Totall price for empty order was: "+price+"\n Have a great day!";	
	}
	
	// Create the attachment
	EmailAttachment attachment = new EmailAttachment();
	attachment.setPath("./Screenshots/"+fileme+".png");
	attachment.setDisposition(EmailAttachment.ATTACHMENT);
	attachment.setDescription("Screenshot done by Selenium");
	attachment.setName(fileme);

	// Create the email message
	MultiPartEmail email = new MultiPartEmail();
	email.setHostName("smtp.gmail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("max.petrenko.qa@gmail.com", "svoiludi"));
	email.setSSLOnConnect(true);
	email.addTo("y.yantselovska@gmail.com", "Selenium Test");
	email.setFrom("me@apache.org", "Selenium Test");
	email.setSubject("The test results with picture from Selenium");
	email.setMsg(message);

	// add the attachment
	email.attach(attachment);

	// send the email
	email.send();
	System.out.println("Email was sended");
	
}

public static void DoSearch(WebDriver driver, String search){
	 WebDriverWait wdWait = new WebDriverWait(driver, 15);

	By searchField = new By.ByCssSelector("#twotabsearchtextbox");
//	By item = new By.ByClassName("s-item-container");
	
	By item = new By.ByCssSelector(".s-item-container a");
	By addButton = new By.ByCssSelector("#add-to-cart-button");
	
	wdWait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
	driver.findElement(searchField).sendKeys(search + Keys.ENTER);
	List<WebElement> searchResult = driver.findElements(item);
	searchResult.get(0).click();
	wdWait.until(ExpectedConditions.visibilityOfElementLocated(addButton));
	driver.findElement(addButton).click();

}
}
