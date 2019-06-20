package by.bsu.tutor.service;


import by.bsu.tutor.models.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAll();

    Message save(Message message);

}
