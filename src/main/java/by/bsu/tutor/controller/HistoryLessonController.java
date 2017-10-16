package by.bsu.tutor.controller;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.service.administration.HistoryLessonService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HistoryLessonController {

    @Autowired ClientTutorRelationService clientTutorRelationService;
    @Autowired private HistoryLessonService historyLessonService;

    @RequestMapping(value = "/tutors/{id}/history")
    public String log(@PathVariable Long id, Model model) {
        model.addAttribute("relations", clientTutorRelationService.getByTutorId(id));
        model.addAttribute("history", new HistoryLesson());
        return "history";
    }

    @RequestMapping(value = "/relations/{relationId}/history", method = RequestMethod.POST)
    public String log(@PathVariable Long relationId, @ModelAttribute(value = "history") HistoryLesson historyLesson) throws LogicException {
        historyLessonService.save(relationId, historyLesson);
        return "redirect:/main";
    }

    @RequestMapping(value = "/history")
    public String log(Model model) {
        model.addAttribute("history", historyLessonService.getAll());
        return "history/list";
    }

    @RequestMapping(value = "/relations/{id}/history")
    public String getHistory(@PathVariable Long id, Model model){
        model.addAttribute("history", historyLessonService.getByRelationId(id));
        return "history/list";
    }

}
