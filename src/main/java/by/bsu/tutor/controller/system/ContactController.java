package by.bsu.tutor.controller.system;

import by.bsu.tutor.models.dto.ContactDto;
import by.bsu.tutor.service.mailer.MailMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@Controller
@RequestMapping(value = "/contacts")
public class ContactController {

    @Autowired private MailMessageSenderService<ContactDto> messageSenderService;


    @GetMapping
    public String contacts(Model model) {
        model.addAttribute("contact", new ContactDto());
        return "contacts";
    }

    @PostMapping
    public void contacts(@ModelAttribute(value = "contact") ContactDto contactDto) throws MessagingException {
        messageSenderService.send(contactDto);
    }

}
