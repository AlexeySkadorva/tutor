package by.bsu.tutor.controller.tutor;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.dto.SearchForm;
import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.Role;
import by.bsu.tutor.models.enums.Gender;
import by.bsu.tutor.repositories.LessonTypeRepository;
import by.bsu.tutor.repositories.RoleRepository;
import by.bsu.tutor.repositories.SubjectRepository;
import by.bsu.tutor.service.relation.TutorEvaluationService;
import by.bsu.tutor.service.relation.TutorNoteService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "tutors")
public class TutorController {

    @Autowired private TutorService tutorService;
    @Autowired private TutorNoteService tutorNoteService;
    @Autowired private TutorEvaluationService tutorEvaluationService;

    @Autowired private RoleRepository roleRepository;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private LessonTypeRepository lessonTypeRepository;

    @GetMapping(value = "/new")
    public Object getNewUser(Model model) {
        model.addAttribute("lessonTypes", lessonTypeRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("tutor", new Tutor());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("genders", Gender.values());
        return "tutor/new";
    }

    @GetMapping(value = "/{id}/edit")
    public Object р(@PathVariable Long id, Model model) throws LogicException {
        model.addAttribute("lessonTypes", lessonTypeRepository.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("tutor", tutorService.get(id));
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("genders", Gender.values());
        return "tutor/edit";
    }


    @PostMapping(value = "/{id}")
    public Object s(@PathVariable Long id, @ModelAttribute(value = "tutor") Tutor tutor) throws LogicException {
        tutor.setId(id);
        tutorService.save(tutor);
        return "redirect:/account";
    }

    @PostMapping
    public String saveTutor(@ModelAttribute(value = "tutor") Tutor tutor, Model model) {
        tutor.getUser().setRole(roleRepository.findByCode(Role.Code.TUTOR));

        tutorService.save(tutor);
        model.addAttribute("userId", tutor.getUser().getId());
        return "user/photo";
    }

    @PostMapping(value = "/{id}/note")
    public String saveNote(@ModelAttribute(value = "tutor") TutorNote tutorNote, Model model) {
        tutorNoteService.save(tutorNote);
        return "main";
    }

    @GetMapping(value = "/{id}")
    public String getTutor(@PathVariable Long id, Model model) throws LogicException {
        Tutor tutor = tutorService.get(id);
        model.addAttribute("tutor", tutor);
        model.addAttribute("note", new TutorNote());

        model.addAttribute("evaluation", tutorEvaluationService.getMiddleEvaluation(tutor));
        return "tutor/tutor";
    }

    @GetMapping
    public String getTutors(SearchForm searchForm, Model model) {
        Iterable<Tutor> tutors = tutorService.getBySearchForm(searchForm);
        searchForm.adjust(tutorService.getCount());
        model.addAttribute("tutors", tutors);
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("subjects", subjectRepository.findAll());
        return "tutor/list";
    }

    @GetMapping(value = "/subjects/{id}")
    public String getTutorsList(SearchForm searchForm, @PathVariable Integer id, Model model) {
        Subject subject = subjectRepository.getOne(id);
        Iterable<Tutor> tutors = tutorService.getBySubject(subject, searchForm);
        searchForm.adjust(tutorService.getCountBySubject(subject));
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("subject", subject);
        model.addAttribute("tutors", tutors);
        model.addAttribute("subjects", subjectRepository.findAll());

        return "tutor/list";
    }

}
