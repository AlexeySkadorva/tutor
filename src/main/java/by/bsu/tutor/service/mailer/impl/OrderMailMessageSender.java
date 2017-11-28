package by.bsu.tutor.service.mailer.impl;

import by.bsu.tutor.models.dto.MailMessageDto;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderStatus;
import by.bsu.tutor.properties.GmailProperties;
import by.bsu.tutor.service.mailer.MailMessageSenderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderMailMessageSender extends MailMessageSenderService<Order> {

    private static final String MESSAGE_SUBJECT = "Вам пришла заявка";
    private static final String MESSAGE_SUBJECT_DECLINED = "Ваш заказ отклонен";
    private static final String MESSAGE_SUBJECT_APPROVED = "Ваш заказ подтвержден";
    private static final String MESSAGE_PREFIX = "Сообщение отклиента: ";
    private static final String URl_PREFIX = "Ссылка для перехода: ";
    private static final String URL_REDIRECT = "<a>localhost:8080/tutors/%s/orders</a><hr></hr>";


    public OrderMailMessageSender(GmailProperties gmailProperties) {
        super(gmailProperties);
    }

    @Override
    protected MailMessageDto buildMailDto(Order order) {
        String email = order.getTutor().getUser().getEmail();
        String content = getMessageContent(order);

        return new MailMessageDto(email, MESSAGE_SUBJECT, content);
    }

    private String getMessageContent(Order order) {
        if (OrderStatus.Code.NEW == order.getOrderStatus().getCode()) {
            return MESSAGE_PREFIX + order.getMessage() +
                    URl_PREFIX + String.format(URL_REDIRECT, order.getTutor().getId());
        }

        if (OrderStatus.Code.APPROVED == order.getOrderStatus().getCode()) {
            return MESSAGE_SUBJECT_APPROVED + order.getMessage();
        }

        if (OrderStatus.Code.DECLINED == order.getOrderStatus().getCode()) {
            return MESSAGE_SUBJECT_DECLINED + order.getMessage();
        }

        return StringUtils.EMPTY;
    }
}
