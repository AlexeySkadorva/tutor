package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.administration.HistoryLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryLessonRepository extends BaseRepository<HistoryLesson> {

    List<HistoryLesson> findByRelationId(Long id);

}
