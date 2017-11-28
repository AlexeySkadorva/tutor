package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {

    @NotNull
    List<Order> findByTutorIdAndOrderStatusCode(Long id, OrderStatus.Code code);

    @NotNull
    List<Order> findByTutorId(Long id);

}
