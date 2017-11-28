package by.bsu.tutor.service.relation.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.tutor.TutorInvoice;
import by.bsu.tutor.repositories.TutorInvoiceRepository;
import by.bsu.tutor.service.relation.HistoryLessonService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.relation.ClientTutorRelationService;
import by.bsu.tutor.service.relation.TutorInvoiceService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alexey Skadorva
 */
@Service
public class DefaultTutorInvoiceService extends DefaultCrudService<TutorInvoice, TutorInvoiceRepository> implements TutorInvoiceService {

    private static final byte DEFAULT_RATE = 15;

    private final ClientTutorRelationService clientTutorRelationService;
    private final HistoryLessonService historyLessonService;


    public DefaultTutorInvoiceService(@NotNull TutorInvoiceRepository repository, ClientTutorRelationService
            clientTutorRelationService, HistoryLessonService historyLessonService) {
        super(repository);
        this.historyLessonService = historyLessonService;
        this.clientTutorRelationService = clientTutorRelationService;
    }

    @Override
    @NotNull
    public TutorInvoice save(@NotNull TutorInvoice invoice) {
        invoice.setRate(DEFAULT_RATE);

        return repository.save(invoice);
    }

    @Override
    @NotNull
    public TutorInvoice updateInvoices(@NotNull Long tutorId) throws LogicException {
        TutorInvoice tutorInvoice = super.get(tutorId);

        List<ClientTutorRelation> clientTutorRelations = clientTutorRelationService.getByTutorId(tutorId);
        int sum = 0;
        for (ClientTutorRelation relation : clientTutorRelations) {
            int countOfLessons = (int) historyLessonService.getByRelationId(relation.getId()).stream()
                    .filter(lesson -> !lesson.getIsPaid())
                    .count();
            sum += (relation.getTutor().getPrice() * countOfLessons * tutorInvoice.getRate()) / 100;
        }
        tutorInvoice.setAmount(sum - tutorInvoice.getPaid());
        return super.save(tutorInvoice);
    }


    @Override
    public TutorInvoice getByTutorId(@NotNull Long tutorId) {
        return repository.findByTutorId(tutorId);
    }

}
