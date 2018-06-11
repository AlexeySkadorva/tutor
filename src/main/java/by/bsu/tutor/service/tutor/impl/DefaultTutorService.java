package by.bsu.tutor.service.tutor.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.dto.SearchForm;
import by.bsu.tutor.models.entity.LessonDuration;
import by.bsu.tutor.models.entity.tutor.*;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.LessonDurationRepository;
import by.bsu.tutor.repositories.TutorRepository;
import by.bsu.tutor.repositories.TutorSubjectDurationRepository;
import by.bsu.tutor.repositories.TutorSubjectRepository;
import by.bsu.tutor.service.user.UserService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.relation.TutorInvoiceService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DefaultTutorService extends DefaultCrudService<Tutor, TutorRepository> implements TutorService {

    private final TutorRepository tutorRepository;
    private final UserService userService;
    private final TutorInvoiceService tutorInvoiceService;
    private final TutorSubjectRepository tutorSubjectRepository;
    private final TutorSubjectDurationRepository tutorSubjectDurationRepository;
    private final LessonDurationRepository lessonDurationRepository;


    public DefaultTutorService(@NotNull TutorRepository repository, TutorRepository tutorRepository,
                               UserService userService, TutorInvoiceService tutorInvoiceService,
                               TutorSubjectRepository tutorSubjectRepository, TutorSubjectDurationRepository tutorSubjectDurationRepository, LessonDurationRepository lessonDurationRepository) {
        super(repository);
        this.tutorRepository = tutorRepository;
        this.userService = userService;
        this.tutorInvoiceService = tutorInvoiceService;
        this.tutorSubjectRepository = tutorSubjectRepository;
        this.tutorSubjectDurationRepository = tutorSubjectDurationRepository;
        this.lessonDurationRepository = lessonDurationRepository;
    }

    @Override
    @NotNull
    public Tutor save(@NotNull Tutor tutor) throws LogicException {
        List<TutorSubject> tutorSubjects = tutor.getTutorSubjects();

        User user = userService.save(tutor.getUser());
        tutor.setUser(user);
        tutor.setTutorSubjects(null);
        Tutor savesTutor = super.save(tutor);

        List<LessonDuration> lessonDurations = lessonDurationRepository.findAll();
        for(TutorSubject tutorSubject : tutorSubjects) {
            tutorSubject.setTutorId(savesTutor.getId());
            List<TutorSubjectDuration> tutorSubjectDurations = tutorSubject.getTutorSubjectDurations();
            tutorSubject.setTutorSubjectDurations(null);
            TutorSubject savedTutorSubject = tutorSubjectRepository.save(tutorSubject);
            for(int i = 0; i < lessonDurations.size(); i++) {
                TutorSubjectDuration tutorSubjectDuration = tutorSubjectDurations.get(i);
                tutorSubjectDuration.setTutorSubjectId(savedTutorSubject.getId());
                tutorSubjectDuration.setDuration(lessonDurations.get(i));
                tutorSubjectDurationRepository.save(tutorSubjectDuration);
            }
        }

        tutorInvoiceService.save(new TutorInvoice(savesTutor));

        return savesTutor;
    }


    @Override
    public Iterable<Tutor> getBySubject(@NotNull Subject subject, @NotNull SearchForm searchForm) {
        Pageable pageSpecification;
        if (searchForm.getPageSize() == null || searchForm.getPageNumber() == null) {
            pageSpecification = new PageRequest(DEFAULT_PAGE, DEFAULT_PAGE_SIZE);
        } else {
            pageSpecification = new PageRequest(searchForm.getPageNumber() - 1, searchForm.getPageSize());
        }
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        return tutorRepository.findBySubjects(subjects, pageSpecification);
    }

    @Override
    public Tutor getByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public Long getCountBySubject(Subject subject) {
        return Long.valueOf(tutorRepository.findBySubjects(Collections.singletonList(subject)).size());
    }

}
