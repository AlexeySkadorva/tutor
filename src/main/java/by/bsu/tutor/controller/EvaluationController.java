package by.bsu.tutor.controller;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.tutor.TutorEvaluation;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.tutor.TutorEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EvaluationController {

    @Autowired private ClientTutorRelationService relationService;
    @Autowired private TutorEvaluationService tutorEvaluationService;

    @RequestMapping(value = "/relations/{id}/evaluation")
    public String log(@PathVariable Long id, Model model) throws LogicException {
        TutorEvaluation evaluation = new TutorEvaluation();
        evaluation.setRelation(relationService.get(id));
        model.addAttribute("evaluation", new TutorEvaluation());
        return "evaluation";
    }

    @RequestMapping(value = "/evaluation", method = RequestMethod.POST)
    public String save(@ModelAttribute TutorEvaluation evaluation) {
        tutorEvaluationService.save(evaluation);
        return "main";
    }

}
