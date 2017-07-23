package by.bsu.tutor.service.administration;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface HistoryLessonService extends CrudService<HistoryLesson> {

    List<HistoryLesson> getByRelationId(@NotNull Long id);

    HistoryLesson save(@NotNull Long id, @NotNull HistoryLesson historyLesson) throws LogicException;

}
