package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.tutor.Tutor;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface TutorRepository extends BaseRepository<Tutor> {

    @NotNull List<Tutor> findBySubjects(List<Subject> subjects, Pageable pageable);

    @NotNull List<Tutor> findBySubjects(List<Subject> subjects);

}
