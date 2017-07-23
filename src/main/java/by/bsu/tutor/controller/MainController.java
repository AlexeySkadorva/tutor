package by.bsu.tutor.controller;

import by.bsu.tutor.repositories.SubjectRepository;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.administration.HistoryLessonService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired private HistoryLessonService historyLessonService;
    @Autowired private ClientService clientService;
    @Autowired private TutorService tutorService;
    @Autowired private SubjectRepository subjectRepository;


    @RequestMapping(value = "/")
    public String main(Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("countOfLessons", historyLessonService.getCount());
        model.addAttribute("countOfClients", clientService.getCount());
        model.addAttribute("countOfTutors", tutorService.getCount());
        return "main";
    }

}
