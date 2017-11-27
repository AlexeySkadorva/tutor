package by.bsu.tutor.service.administration.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.repositories.HistoryLessonRepository;
import by.bsu.tutor.service.administration.HistoryLessonService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.mailer.MailMessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DefaultHistoryLessonsService extends DefaultCrudService<HistoryLesson, HistoryLessonRepository>
        implements HistoryLessonService {

    private final ClientTutorRelationService clientTutorRelationService;
    private final MailMessageSenderService<HistoryLesson> historyLessonMailMessageSenderService;


    @Autowired
    public DefaultHistoryLessonsService(@NotNull HistoryLessonRepository repository,
                                        ClientTutorRelationService clientTutorRelationService,
                                        MailMessageSenderService<HistoryLesson> historyLessonMailMessageSenderService) {
        super(repository);
        this.clientTutorRelationService = clientTutorRelationService;
        this.historyLessonMailMessageSenderService = historyLessonMailMessageSenderService;
    }

    @Override
    public List<HistoryLesson> getByRelationId(@NotNull Long id) {
        return repository.findByRelationId(id);
    }

    @Override
    public HistoryLesson save(Long id, HistoryLesson historyLesson) throws LogicException {
        historyLesson.setRelationId(id);
        return super.save(historyLesson);
    }

    @Override
    public HistoryLesson save(@NotNull HistoryLesson historyLesson) {
        historyLessonMailMessageSenderService.send(historyLesson);
        return super.save(historyLesson);
    }

}
