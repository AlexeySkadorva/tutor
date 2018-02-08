package by.bsu.tutor.controller.relation;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.models.entity.relation.ClientTutorLesson;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.tutor.TutorSubject;
import by.bsu.tutor.repositories.ClientTutorLessonRepository;
import by.bsu.tutor.service.relation.ClientTutorRelationService;
import by.bsu.tutor.service.relation.HistoryLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;

@Controller
public class HistoryLessonController extends ClientTutorRelationController {

    @Autowired private HistoryLessonService historyLessonService;
    @Autowired private ClientTutorRelationService clientTutorRelationService;
    @Autowired private ClientTutorLessonRepository clientTutorLessonRepository;


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
        ClientTutorLesson clientTutorLesson = clientTutorLessonRepository.getOne(1l);
        List<TutorSubject> tutorSubjectList = clientTutorLesson.getRelation().getTutor().getTutorSubjects();


        TutorSubject  tutorSubject = tutorSubjectList.stream().filter(t->t.getSubject().getCode().equals(clientTutorLesson.getSubject().getCode())).findFirst().get();

        int price = tutorSubject.getTutorSubjectDurations().stream().filter(t->t.getDuration().getCode().equals(clientTutorLesson.getDuration().getCode())).findFirst().get().getPrice();

        historyLesson.setPrice(price);
        historyLesson.setDuty((double) ((double)clientTutorLesson.getRelation().getTutor().getDuty() * price / 100));
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
