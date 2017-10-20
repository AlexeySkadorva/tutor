package by.bsu.tutor.service.tutor.impl;

import by.bsu.tutor.models.dto.SearchForm;
import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.tutor.TutorInvoice;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.TutorRepository;
import by.bsu.tutor.service.administration.UserService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.tutor.TutorInvoiceService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    public DefaultTutorService(@NotNull TutorRepository repository, TutorRepository tutorRepository,
                               UserService userService, TutorInvoiceService tutorInvoiceService) {
        super(repository);
        this.tutorRepository = tutorRepository;
        this.userService = userService;
        this.tutorInvoiceService = tutorInvoiceService;
    }

    @Override
    public Tutor save(@NotNull Tutor tutor) {
        User user = userService.save(tutor.getUser());
        tutor.setUser(user);
        Tutor savesTutor = super.save(tutor);

        tutorInvoiceService.save(new TutorInvoice(tutor));

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
    public long getCountBySubject(Subject subject) {
        return tutorRepository.findBySubjects(Collections.singletonList(subject)).size();
    }

}
