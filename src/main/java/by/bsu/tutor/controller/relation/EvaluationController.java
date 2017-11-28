package by.bsu.tutor.controller.relation;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.tutor.TutorEvaluation;
import by.bsu.tutor.service.relation.TutorEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexey Skadorva
 */
public class EvaluationController extends ClientTutorRelationController {

    @Autowired private TutorEvaluationService tutorEvaluationService;


    @GetMapping(value = "/{id}/evaluation")
    public String log(@PathVariable Long id, Model model) throws LogicException {
        TutorEvaluation evaluation = new TutorEvaluation();
        evaluation.setRelation(clientTutorRelationService.get(id));
        model.addAttribute("evaluation", evaluation);
        return "relation/evaluation";
    }

    @PostMapping(value = "/evaluation")
    public String save(@ModelAttribute TutorEvaluation evaluation) {
        tutorEvaluationService.save(evaluation);
        return "redirect:/account";
    }

}
