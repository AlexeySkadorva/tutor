package by.bsu.tutor.service.tutor;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.tutor.TutorInvoice;
import by.bsu.tutor.service.base.CrudService;
import com.sun.istack.internal.Nullable;

import javax.validation.constraints.NotNull;

/**
 * @author Alexey Skadorva
 */
public interface TutorInvoiceService extends CrudService<TutorInvoice> {

    @Nullable TutorInvoice getByTutorId(@NotNull Long tutorId);

    @NotNull TutorInvoice updateInvoices(@NotNull Long tutorId) throws LogicException;

}
