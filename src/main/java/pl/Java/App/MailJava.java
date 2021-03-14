package pl.Java.App;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.mail.Session;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class MailJava {
    public static int sendMail(String recipient, String email, String pass, String title, String text) throws Exception {

        System.out.println("Przygotowywanie maila");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        final String myAccountGmail = email;
        final String password = pass;

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountGmail, password);
            }
        });

        Message message = prepareNewMessage(session, myAccountGmail, recipient, title, text);

        try {
            Transport.send(message);
            System.out.println("Udało się wysłać mail");
            return 1;
        }
        catch (Exception e){
            System.out.println("Niepowodzenie");
            return 0;
        }
    }

    private static Message prepareNewMessage(Session session, String myAccountEmail, String recipient, String title, String text){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(title);
            message.setText(text);
            return message;
        }
        catch(Exception e){
            Logger.getLogger(MailJava.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}