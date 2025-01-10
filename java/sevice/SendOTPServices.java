package sevice;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


public class SendOTPServices {

		public static void sendEmail(String email, String getOTP) {
    	String to = email;
    	 String from = "scodes586@gmail.com";
    	
        String host = "smtp.gmail.com";

        // Set up mail server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true); // Use STARTTLS
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", host);

        // Create a session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "xrpeymetgmykgfch"); // Replace with the App Password
            }
        });

        session.setDebug(true); // Enable debugging

        try {
            // Create a message
            MimeMessage msg = new MimeMessage(session);

            // From
            msg.setFrom(new InternetAddress(from));

            // To
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Subject
            msg.setSubject("OTP for email verification");

            // Message Body
            msg.setText("You'r One time password is : " + getOTP);

            // Send the message
            Transport.send(msg);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
