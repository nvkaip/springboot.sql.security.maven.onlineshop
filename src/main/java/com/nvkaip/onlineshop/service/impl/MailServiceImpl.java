package com.service.impl;

import com.entity.Code;
import com.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
    @Override
    public void sendConfirmCode(Code code) {
        final String username = "11owlkid11@gmail.com";
        final String password = "12345678kid";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(code.getUser().getEmail())
            );
            message.setSubject("Validation code");
            message.setText("Your validation code"
                    + "\n\n" + code.getValue());
            Transport.send(message);
            logger.info("Message with validation code: " + code.getValue()
                    + ", was sent to " + code.getUser().getEmail());
        } catch (MessagingException e) {
            logger.error("Emailing confirmation code failed", e);
        }
    }
}
