package CS585.Webhook;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.zeroturnaround.zip.ZipUtil;

/**
 * @author Roshan Rathod
 * 
 * Converts or creates a ZIP file of the folder containing mutation test results
 * 
 * Sends an email to the committer with the ZIP folder as an attachment
 *  
 */

public class SendEmail {




	public static void To(String CommitersEmail, String folder){

		//test reports are always stored in target/pit-reports folder
		String path = folder+"\\target\\pit-reports";

		//Output zip file name same as folder name
		String testsOutputZip = path+".zip";

		//ZIP the folder pit-reports to pit-reports.zip
		ZipUtil.pack(new File(path), new File(testsOutputZip));

		
		/*	Send email to the committer using GMAIL
		 *  To send email using your gmail, goto Settings>> account setting >> other google account settings
		 *  TURN ON Access for less secure app, under Signing in
		*/
	
		final String fromEmail = "github.webhook@gmail.com"; 
		final String password = "webhook1234"; 
		final String toEmail = CommitersEmail; 

		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port

		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		
		try{
			MimeMessage msg = new MimeMessage(session);
			
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("github.webhook@gmail.com", "Roshan Rathod"));

			msg.setReplyTo(InternetAddress.parse("roshan.rathod@gmail.com", false));

			msg.setSubject("Mutation Tests Results", "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			messageBodyPart.setText("Hey! You made some changes to the repository. So we ran mutation tests on all the classes");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is the ZIP attachment of the tests results
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(testsOutputZip);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("MutationTests.zip");
			
			messageBodyPart.setHeader("Content-ID", "ZIP_id");
			multipart.addBodyPart(messageBodyPart);

			//third part for displaying image in the email body
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent("<h3>Attached Mutation Test Results</h3>", "text/html");
			multipart.addBodyPart(messageBodyPart);

			//Set the multipart message to the email message
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			
			System.out.println("Email Sent Successfully with Zipped test results!!");
			
		}catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

