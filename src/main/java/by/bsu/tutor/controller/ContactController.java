package by.bsu.tutor.controller;

import by.bsu.tutor.models.dto.ContactDto;
import by.bsu.tutor.service.mailer.MailMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;

@Controller
@RequestMapping(value = "/contacts")
public class ContactController {

    private final MailMessageSenderService<ContactDto> messageSenderService;

    @Autowired
    public ContactController(MailMessageSenderService<ContactDto> messageSenderService) {
        this.messageSenderService = messageSenderService;
    }


    @RequestMapping
    public String contacts(Model model){
        model.addAttribute("contact", new ContactDto());
        return "contacts";
    }

    @RequestMapping(method = RequestMethod.POST)
    public void contacts(@ModelAttribute(value = "contact") ContactDto contactDto) throws MessagingException {
        messageSenderService.send(contactDto);
    }

}
