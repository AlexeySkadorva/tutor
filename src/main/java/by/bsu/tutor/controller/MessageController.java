package by.bsu.tutor.controller;

import by.bsu.tutor.models.Message;
import by.bsu.tutor.models.dto.MessageDto;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;


    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @PutMapping
    public  Message save(@RequestBody MessageDto messageDto, @AuthenticationPrincipal User user) {
        return messageService.save(new Message(messageDto.getMessage(), messageDto.getUserTo(), messageDto.getUserFrom(), new Date()));
    }

}
