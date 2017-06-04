package practice;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendMail {

	public static void main(String[] args) throws EmailException {
		
		String message = "Hello UserName, \nThis is a test mail from Selenium with test results\n\n Have a great day!";
		
		//System.out.println("Test Start");
		
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("max.petrenko.qa@gmail.com", "svoiludi"));
		email.setSSLOnConnect(true);
		email.setFrom("selenium@gmail.com");
		email.setSubject("Selenium TestMail");
		email.setMsg(message);
		email.addTo("erste1max@gmail.com");
		email.send();
		System.out.println("Email was send");

	}

}
