package practice;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailWithAttachment {

	public static void main(String[] args) throws EmailException{
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("./Screenshots/Amazon.png");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Screenshot done by Selenium");
		attachment.setName("Wiki");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("max.petrenko.qa@gmail.com", "svoiludi"));
		email.setSSLOnConnect(true);
		email.addTo("y.yantselovska@gmail.com", "Selenium Test");
		email.setFrom("me@apache.org", "Selenium Test");
		email.setSubject("The picture from Selenium");
		email.setMsg("Here is the screenshot done by Selenium");

		// add the attachment
		email.attach(attachment);

		// send the email
		email.send();


	}
}
