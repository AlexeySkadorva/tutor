package by.bsu.tutor.service.tutor;

import by.bsu.tutor.models.dto.SearchForm;
import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;

public interface TutorService extends CrudService<Tutor> {

    Iterable<Tutor> getBySubject(@NotNull Subject subject, @NotNull SearchForm searchForm);

    @NotNull long getCountBySubject(Subject subject);

}
