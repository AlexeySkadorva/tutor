package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.relation.ClientTutorLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientTutorLessonRepository extends JpaRepository<ClientTutorLesson, Long> {
}
