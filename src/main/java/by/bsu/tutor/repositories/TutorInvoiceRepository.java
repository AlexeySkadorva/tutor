package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.tutor.TutorInvoice;

import javax.validation.constraints.NotNull;

/**
 * @author Alexey Skadorva
 */
public interface TutorInvoiceRepository extends BaseRepository<TutorInvoice> {

    @NotNull
    TutorInvoice findByTutorId(@NotNull Long id);

}
