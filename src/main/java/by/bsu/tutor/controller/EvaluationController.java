package by.bsu.tutor.controller;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.tutor.TutorEvaluation;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.tutor.TutorEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EvaluationController {

    @Autowired private ClientTutorRelationService relationService;
    @Autowired private TutorEvaluationService tutorEvaluationService;

    @GetMapping(value = "/relations/{id}/evaluation")
    public String log(@PathVariable Long id, Model model) throws LogicException {
        TutorEvaluation evaluation = new TutorEvaluation();
        evaluation.setRelation(relationService.get(id));
        model.addAttribute("evaluation", evaluation);
        return "evaluation";
    }

    @PostMapping(value = "/evaluation")
    public String save(@ModelAttribute TutorEvaluation evaluation) {
        tutorEvaluationService.save(evaluation);
        return "main";
    }

}
