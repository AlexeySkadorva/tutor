package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.administration.HistoryLesson;

import java.util.List;

public interface HistoryLessonRepository extends BaseRepository<HistoryLesson> {

    List<HistoryLesson> findByRelationId(Long id);

}
