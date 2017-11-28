package by.bsu.tutor.service.relation.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.repositories.HistoryLessonRepository;
import by.bsu.tutor.service.relation.HistoryLessonService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.mailer.MailMessageSenderService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DefaultHistoryLessonsService extends DefaultCrudService<HistoryLesson, HistoryLessonRepository>
        implements HistoryLessonService {

    private final MailMessageSenderService<HistoryLesson> historyLessonMailMessageSenderService;


    public DefaultHistoryLessonsService(@NotNull HistoryLessonRepository repository,
                                        MailMessageSenderService<HistoryLesson> historyLessonMailMessageSenderService) {
        super(repository);
        this.historyLessonMailMessageSenderService = historyLessonMailMessageSenderService;
    }

    @Override
    public List<HistoryLesson> getByRelationId(@NotNull Long id) {
        return repository.findByRelationId(id);
    }

    @Override
    @NotNull
    public HistoryLesson save(Long id, HistoryLesson historyLesson) throws LogicException {
        historyLesson.setRelationId(id);
        return super.save(historyLesson);
    }

    @Override
    @NotNull
    public HistoryLesson save(@NotNull HistoryLesson historyLesson) {
        historyLessonMailMessageSenderService.send(historyLesson);
        return super.save(historyLesson);
    }

}
