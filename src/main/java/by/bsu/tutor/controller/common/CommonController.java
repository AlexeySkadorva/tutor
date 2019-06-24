package by.bsu.tutor.controller.common;

import by.bsu.tutor.models.entity.LessonDuration;
import by.bsu.tutor.models.entity.order.LessonType;
import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.user.Role;
import by.bsu.tutor.repositories.LessonDurationRepository;
import by.bsu.tutor.repositories.LessonTypeRepository;
import by.bsu.tutor.repositories.RoleRepository;
import by.bsu.tutor.repositories.SubjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CommonController {

    private final RoleRepository roleRepository;
    private final SubjectRepository subjectRepository;
    private final LessonTypeRepository lessonTypeRepository;
    private final LessonDurationRepository lessonDurationRepository;

    public CommonController(RoleRepository roleRepository, SubjectRepository subjectRepository, LessonTypeRepository
            lessonTypeRepository, LessonDurationRepository lessonDurationRepository) {
        this.roleRepository = roleRepository;
        this.subjectRepository = subjectRepository;
        this.lessonTypeRepository = lessonTypeRepository;
        this.lessonDurationRepository = lessonDurationRepository;
    }

    @GetMapping(value = "/lessonTypes")
    public List<LessonType> getAllLessonTypes() {
        return lessonTypeRepository.findAll();
    }

    @GetMapping(value = "/subjects")
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @GetMapping(value = "/roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping(value = "/lessonDurations")
    public List<LessonDuration> getLessonDurations() {
        return lessonDurationRepository.findAll();
    }

}
