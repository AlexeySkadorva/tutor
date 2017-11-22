package by.bsu.tutor.service.order.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderStatus;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.OrderRepository;
import by.bsu.tutor.repositories.OrderStatusRepository;
import by.bsu.tutor.service.administration.MessageSenderService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DefaultOrderService extends DefaultCrudService<Order, OrderRepository> implements OrderService {

    private final OrderStatusRepository orderStatusRepository;
    @Autowired private MessageSenderService messageSenderService;


    @Autowired
    public DefaultOrderService(@NotNull OrderRepository repository, OrderStatusRepository orderStatusRepository) {
        super(repository);
        this.orderStatusRepository = orderStatusRepository;
    }

    @Override
    public Order save(@NotNull Order order) {
        messageSenderService.sendToUser(order, order.getTutor().getUser());
        return super.save(order);
    }

    @Override
    public List<Order> getNewByTutorId(@NotNull Long tutorId) {
        return repository.findByTutorIdAndOrderStatusCode(tutorId, OrderStatus.Code.NEW);
    }

    @Override
    public List<Order> getByTutorId(@NotNull Long tutorId) {
        return repository.findByTutorId(tutorId);
    }

    @Override
    public Order updateOrderStatus(@NotNull Long id, @NotNull OrderStatus.Code status) throws LogicException {
        Order order = super.get(id);
        order.setOrderStatus(orderStatusRepository.findByCode(status));

        return super.save(order);
    }

}
