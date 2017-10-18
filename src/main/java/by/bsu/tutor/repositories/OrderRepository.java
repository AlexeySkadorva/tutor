package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.tutor.TutorInvoice;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    @NotNull List<Order> findByTutorId(@NotNull Long id);

}
