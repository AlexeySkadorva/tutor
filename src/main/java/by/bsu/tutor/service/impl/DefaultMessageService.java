package by.bsu.tutor.service.impl;


import by.bsu.tutor.models.Message;
import by.bsu.tutor.repositories.MessageRepository;
import by.bsu.tutor.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

import static by.bsu.tutor.config.WebSocketConfig.MESSAGE_PREFIX;


@Transactional(readOnly = true)
@Service
public class DefaultMessageService implements MessageService {

    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;


    @Autowired
    public DefaultMessageService(MessageRepository messageRepository,
                                 SimpMessagingTemplate simpMessagingTemplate) {
        this.messageRepository = messageRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public List<Message> getAll() {
        return messageRepository.findAllByOrderByDateAsc();
    }

    @Transactional
    @Override
    public Message save(Message message) {
        Message persistMessage = messageRepository.save(message);
        simpMessagingTemplate.convertAndSend(MESSAGE_PREFIX + "/newMessage/" + message.getUser().getId() +
                "/" + message.getUserTo().getId(), persistMessage);
        return persistMessage;
    }

}
