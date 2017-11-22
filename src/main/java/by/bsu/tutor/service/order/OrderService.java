package by.bsu.tutor.service.order;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderStatus;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface OrderService extends CrudService<Order> {

    List<Order> getNewByTutorId(@NotNull Long tutorId);

    @NotNull List<Order> getByTutorId(@NotNull Long tutorId);

    @NotNull Order updateOrderStatus(@NotNull Long id, @NotNull OrderStatus.Code status) throws LogicException;

}
