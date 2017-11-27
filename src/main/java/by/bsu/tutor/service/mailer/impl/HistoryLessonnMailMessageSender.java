package by.bsu.tutor.service.mailer.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.dto.MailMessageDto;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.properties.GmailProperties;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.mailer.MailMessageSenderService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class HistoryLessonnMailMessageSender extends MailMessageSenderService<HistoryLesson> {

    private static final String HOME_WORK_SUBJECT = "Домашнее задание: ";
    private static final String HOME_WORK = "Ваше домашнее задание: ";
    private static final String COMPLETED_MATERIALS = "Пройденный материал: ";

    private final ClientTutorRelationService clientTutorRelationService;


    public HistoryLessonnMailMessageSender(GmailProperties gmailProperties, ClientTutorRelationService clientTutorRelationService) {
        super(gmailProperties);
        this.clientTutorRelationService = clientTutorRelationService;
    }

    @Override
    @SneakyThrows
    protected MailMessageDto buildMailDto(HistoryLesson historyLesson) {
        String email = clientTutorRelationService.get(historyLesson.getRelationId()).getClient().getUser().getEmail();
        String subject = HOME_WORK_SUBJECT;

        String content = COMPLETED_MATERIALS + historyLesson.getCompletedMaterial() + "<br/>" + HOME_WORK + historyLesson.getHomework();

        return new MailMessageDto(email, subject, content);
    }

}
