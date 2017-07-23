package by.bsu.tutor.controller.tutor;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.dto.SearchForm;
import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.repositories.SubjectRepository;
import by.bsu.tutor.service.tutor.TutorEvaluationService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "tutors")
public class TutorController {

    @Autowired private TutorService tutorService;
    @Autowired private TutorEvaluationService tutorEvaluationService;

    @Autowired private SubjectRepository subjectRepository;


    @RequestMapping(value = "/{id}")
    public String getTutor(@PathVariable Long id, Model model) throws LogicException {
        Tutor tutor = tutorService.get(id);
        model.addAttribute("tutor", tutor);
        model.addAttribute("evaluation", tutorEvaluationService.getMiddleEvaluation(tutor));
        return "tutor";
    }

    @RequestMapping
    public String getTutors(SearchForm searchForm, Model model){
        Iterable<Tutor> tutors = tutorService.getBySearchForm(searchForm);
        searchForm.adjust(tutorService.getCount());
        model.addAttribute("tutors", tutors);
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("subjects", subjectRepository.findAll());
        return "tutors";
    }

    @RequestMapping(value ="/subjects/{id}")
    public String getTutorsList(SearchForm searchForm, @PathVariable Integer id, Model model){
        Subject subject = subjectRepository.getOne(id);
        Iterable<Tutor> tutors = tutorService.getBySubject(subject, searchForm);
        searchForm.adjust(tutorService.getCountBySubject(subject));
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("subject", subject);
        model.addAttribute("tutors", tutors);
        model.addAttribute("subjects", subjectRepository.findAll());

        return "tutors";
    }

}
