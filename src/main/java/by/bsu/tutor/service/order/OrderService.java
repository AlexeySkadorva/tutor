package by.bsu.tutor.service.order;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;

public interface OrderService extends CrudService<Order> {

    @NotNull Order save(@NotNull Long tutorId, @NotNull User user) throws LogicException;

}
