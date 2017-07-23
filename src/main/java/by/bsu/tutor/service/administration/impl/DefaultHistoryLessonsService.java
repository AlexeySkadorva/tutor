package by.bsu.tutor.service.administration.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.repositories.HistoryLessonRepository;
import by.bsu.tutor.service.administration.HistoryLessonService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DefaultHistoryLessonsService extends DefaultCrudService<HistoryLesson, HistoryLessonRepository>
        implements HistoryLessonService {

    private ClientTutorRelationService clientTutorRelationService;


    @Autowired
    public DefaultHistoryLessonsService(@NotNull HistoryLessonRepository repository,
                                        ClientTutorRelationService clientTutorRelationService) {
        super(repository);
        this.clientTutorRelationService = clientTutorRelationService;
    }

    @Override
    public List<HistoryLesson> getByRelationId(@NotNull Long id) {
        return repository.findByRelationId(id);
    }

    @Override
    public HistoryLesson save(@NotNull Long id, @NotNull HistoryLesson historyLesson) throws LogicException {
        historyLesson.setRelation(clientTutorRelationService.get(id));
        return super.save(historyLesson);
    }

}
