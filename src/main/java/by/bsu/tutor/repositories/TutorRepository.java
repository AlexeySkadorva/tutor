package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.base.City;
import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.User;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface TutorRepository extends BaseRepository<Tutor> {

 //   @NotNull
  //  List<Tutor> findBySubjectsAndCity(List<Subject> subjects, List<City> city, Pageable pageable);
    List<Tutor> findBySubjects(List<Subject> subjects, Pageable pageable);
 //   List<Tutor> findBySubjectsAndCity(List<Subject> subjects, City city, Pageable pageable);

   // List<Tutor> findBySubjectsAndCity(List<Subject> subjects, List<City> city);

    @NotNull
    Tutor findByUser(User user);

    @NotNull
    List<Tutor> findBySubjects(List<Subject> subjects);

}
