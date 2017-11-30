package by.bsu.tutor.service.mailer;

import by.bsu.tutor.models.dto.MailMessageDto;
import by.bsu.tutor.properties.GmailProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.NotNull;
import java.util.Properties;


public abstract class MailMessageSenderService<T> {

    private final static String MAIL_CHARSET = "charset=UTF-8";
    private final static String MAIL_FORMAT = "text/html; ";

    protected final GmailProperties gmailProperties;


    public MailMessageSenderService(GmailProperties gmailProperties) {
        this.gmailProperties = gmailProperties;
    }

    @Async
    public void send(@NotNull T contact) {
//        Session mailSession = getMailSession();
//
//        MailMessageDto mailDto = buildMailDto(contact);
//        MimeMessage mailMessage = getGenerateMailMessage(mailSession, mailDto);
//
//        sendMailMessage(mailSession, mailMessage);
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
        return Session.getDefaultInstance(mailServerProperties, null);
    }

    private MimeMessage getGenerateMailMessage(Session mailSession, MailMessageDto mailDto) {
        MimeMessage mailMessage = null;
        try {
            mailMessage = new MimeMessage(mailSession);
            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailDto.getEmail()));
            mailMessage.setSubject(mailDto.getSubject());
            mailMessage.setContent(mailDto.getContent(), MAIL_FORMAT + MAIL_CHARSET);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return mailMessage;
    }

    protected abstract MailMessageDto buildMailDto(T content);

    private Properties getMailServerProperties() {
        Properties mailServerProperties = System.getProperties();
        mailServerProperties.put(gmailProperties.getPortProperties(), gmailProperties.getPortValue());
        mailServerProperties.put(gmailProperties.getAuthProperties(), gmailProperties.getAuthValue());
        mailServerProperties.put(gmailProperties.getEnableProperties(), gmailProperties.getEnableValue());
        return mailServerProperties;
    }

}
