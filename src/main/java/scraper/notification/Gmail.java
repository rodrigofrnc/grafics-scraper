package scraper.notification;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Gmail {

    private final String emailAddressFrom;
    private final String secretText;

    public Gmail(String emailAddressFrom, String secretText) {
        this.emailAddressFrom = emailAddressFrom;
        this.secretText = secretText;
    }

    public void send(String emailAddressTo, String subject, String body) {
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailAddressFrom, secretText);
            }
        });

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailAddressFrom));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddressTo));
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setSubject("Habemus Gráficas na: " + subject);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        try {
            message.setText(body, "utf-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
