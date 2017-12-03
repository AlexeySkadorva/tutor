package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.tutor.TutorSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorSubjectRepository extends JpaRepository<TutorSubject, Long> {
}
