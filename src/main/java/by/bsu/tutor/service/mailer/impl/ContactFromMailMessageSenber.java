package by.bsu.tutor.service.mailer.impl;

import by.bsu.tutor.models.dto.ContactDto;
import by.bsu.tutor.models.dto.MailMessageDto;
import by.bsu.tutor.properties.GmailProperties;
import by.bsu.tutor.service.mailer.MailMessageSenderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ContactFromMailMessageSenber extends MailMessageSenderService<ContactDto> {

    private final static String MAIL_SUBJECT = "Контактная форма:";
    private final static String EMAIL_VALUE = "Email:";


    public ContactFromMailMessageSenber(GmailProperties gmailProperties) {
        super(gmailProperties);
    }

    @Override
    protected MailMessageDto buildMailDto(ContactDto contactDto) {
        String email = gmailProperties.getEmail();
        String subject = String.join(StringUtils.SPACE, MAIL_SUBJECT, contactDto.getName());

        String content = String.join(StringUtils.SPACE, contactDto.getMessage(), EMAIL_VALUE, contactDto.getEmail());

        return new MailMessageDto(email, subject, content);
    }
}
