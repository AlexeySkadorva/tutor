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

    @Autowired private ClientTutorRelationService clientTutorRelationService;
    @Autowired private HistoryLessonService historyLessonService;

    @RequestMapping(value = "/relations/{relationId}/history")
    public String log(@PathVariable Long relationId, Model model) throws LogicException {
        model.addAttribute("history", new HistoryLesson(relationId));
        return "history/new";
    }

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public String save(@ModelAttribute(value = "history") HistoryLesson historyLesson) throws LogicException {
        historyLessonService.save(historyLesson);
        return "redirect:/account";
    }

    @RequestMapping(value = "/history/{id}", method = RequestMethod.POST)
    public String log(@ModelAttribute(value = "history") HistoryLesson historyLesson,@PathVariable Long id) throws LogicException {
            HistoryLesson history = historyLessonService.get(historyLesson.getId());
        history.setCompletedMaterial(historyLesson.getCompletedMaterial());
        history.setHomework(historyLesson.getHomework());
        history.setRating(historyLesson.getRating());

            historyLessonService.save(history);
            return "redirect:/account";
    }

    @RequestMapping(value = "/history")
    public String log(Model model) {
        model.addAttribute("history", historyLessonService.getAll());
        return "history/list";
    }

    @RequestMapping(value = "/history/{id}")
    public String log(Model model, @PathVariable Long id) throws LogicException {
        model.addAttribute("history", historyLessonService.get(id));
        return "history/edit";
    }

    @RequestMapping(value = "/relations/{id}/history/list")
    public String getHistory(@PathVariable Long id, Model model){
        model.addAttribute("history", historyLessonService.getByRelationId(id));
        return "history/list";
    }

}
