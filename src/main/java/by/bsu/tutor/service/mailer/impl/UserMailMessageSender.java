package by.bsu.tutor.service.mailer.impl;

import by.bsu.tutor.models.dto.MailMessageDto;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.properties.GmailProperties;
import by.bsu.tutor.service.mailer.MailMessageSenderService;

/**
 * @author Alexey Skadorva
 */
public class UserMailMessageSender extends MailMessageSenderService<User> {

    private static final String MESSAGE_SUBJECT = "Добро пожаловать в нашу семью";
    private static final String MESSAGE_CONTENT = " Вы зарегистрировались на нашем ресурсе. Мы рады Вас приветствовать и желаем Вам больших успехов в обучении. Для того, чтобы начать занятия зайдите на наш ресурс и выберите себе подходящего репетитора.";


    public UserMailMessageSender(GmailProperties gmailProperties) {
        super(gmailProperties);
    }


    @Override
    protected MailMessageDto buildMailDto(User user) {
        String email = user.getEmail();
        String content = "Уважаемый " + user.getFullName() + MESSAGE_CONTENT;

        return new MailMessageDto(email, MESSAGE_SUBJECT, content);
    }
}
