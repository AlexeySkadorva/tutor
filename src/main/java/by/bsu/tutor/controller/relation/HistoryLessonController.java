package by.bsu.tutor.controller.relation;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.service.relation.HistoryLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HistoryLessonController extends ClientTutorRelationController {

    @Autowired private HistoryLessonService historyLessonService;


    @GetMapping(value = "/{id}/history/list")
    public String getHistory(@PathVariable Long id, Model model) {
        model.addAttribute("history", historyLessonService.getByRelationId(id));
        return "relation/history/list";
    }

    @GetMapping(value = "/{relationId}/history")
    public String newPage(@PathVariable Long relationId, Model model) throws LogicException {
        model.addAttribute("history", new HistoryLesson(relationId));
        return "relation/history/new";
    }

    @PostMapping(value = "/history")
    public String save(@ModelAttribute(value = "history") HistoryLesson historyLesson) throws LogicException {
        historyLessonService.save(historyLesson);
        return "redirect:/account";
    }

    @GetMapping(value = "/history/{id}")
    public String updatePage(Model model, @PathVariable Long id) throws LogicException {
        model.addAttribute("history", historyLessonService.get(id));
        return "relation/history/edit";
    }

    @PostMapping(value = "/history/{id}")
    public String update(@ModelAttribute(value = "history") HistoryLesson historyLesson, @PathVariable Long id) throws LogicException {
        HistoryLesson history = historyLessonService.get(id);
        history.setCompletedMaterial(historyLesson.getCompletedMaterial());
        history.setHomework(historyLesson.getHomework());
        history.setRating(historyLesson.getRating());

        historyLessonService.save(history);
        return "redirect:/account";
    }

}
