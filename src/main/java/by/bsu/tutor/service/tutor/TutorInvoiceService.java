package by.bsu.tutor.service.tutor;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.tutor.TutorInvoice;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;

/**
 * @author Alexey Skadorva
 */
public interface TutorInvoiceService extends CrudService<TutorInvoice> {

    TutorInvoice getByTutorId(@NotNull Long tutorId);

    void updateInvoices(@NotNull Long tutorId) throws LogicException;

}
