package by.bsu.tutor.service.administration.impl;

import by.bsu.tutor.models.dto.ContactDto;
import by.bsu.tutor.properties.GmailProperties;
import by.bsu.tutor.service.administration.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.util.Properties;

@Service
public class MailMessageSenderService implements MessageSenderService {

    private final static String MAIL_SUBJECT = "Контактная форма:";
    private final static String MAIL_CHARSET = "charset=UTF-8";
    private final static String MAIL_FORMAT = "text/plain; ";
    private final static String EMAIL_VALUE = "Email:";
    private final static String WHITESPACE_VALUE = " ";

    @Autowired private GmailProperties gmailProperties;


    @Override
    public void send(@NotNull Object contact) {
        Session mailSession = getMailSession();
        MimeMessage mailMessage = getGenerateMailMessage(mailSession, (ContactDto) contact);

        sendMailMessage(mailSession, mailMessage);
    }

    private void sendMailMessage(Session mailSession, MimeMessage mailMessage) {
        try {
            Transport transport = mailSession.getTransport(gmailProperties.getTransport());
            transport.connect(gmailProperties.getHost(), gmailProperties.getEmail(), gmailProperties.getPassword());
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Session getMailSession() {
        Properties mailServerProperties = getMailServerProperties();
        Session mailSession = Session.getDefaultInstance(mailServerProperties, null);
        return mailSession;
    }

    private MimeMessage getGenerateMailMessage(Session mailSession, ContactDto contactDto) {
        MimeMessage mailMessage = null;
        try {
            mailMessage = new MimeMessage(mailSession);
            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(gmailProperties.getEmail()));
            mailMessage.setSubject(constructSubject(contactDto));

            mailMessage.setContent(constructMessage(contactDto), MAIL_FORMAT + MAIL_CHARSET);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mailMessage;
    }

    private String constructSubject(ContactDto contactDto) {
        return String.join(WHITESPACE_VALUE, MAIL_SUBJECT, contactDto.getName());
    }

    private String constructMessage(ContactDto contactDto){
        return String.join(WHITESPACE_VALUE, contactDto.getMessage(), EMAIL_VALUE, contactDto.getEmail());
    }

    private Properties getMailServerProperties() {
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put(gmailProperties.getPortProperties(), gmailProperties.getPortValue());
        mailServerProperties.put(gmailProperties.getAuthProperties(), gmailProperties.getAuthValue());
        mailServerProperties.put(gmailProperties.getEnableProperties(), gmailProperties.getEnableValue());
        return mailServerProperties;
    }

}
