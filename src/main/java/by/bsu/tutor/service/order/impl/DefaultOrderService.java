package by.bsu.tutor.service.order.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderStatus;
import by.bsu.tutor.repositories.OrderRepository;
import by.bsu.tutor.repositories.OrderStatusRepository;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.mailer.MailMessageSenderService;
import by.bsu.tutor.service.order.OrderService;
import com.sun.istack.internal.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DefaultOrderService extends DefaultCrudService<Order, OrderRepository> implements OrderService {

    private final OrderStatusRepository orderStatusRepository;
    private final MailMessageSenderService<Order> messageSenderService;


    public DefaultOrderService(@NotNull OrderRepository repository, OrderStatusRepository orderStatusRepository,
                               MailMessageSenderService<Order> messageSenderService) {
        super(repository);
        this.orderStatusRepository = orderStatusRepository;
        this.messageSenderService = messageSenderService;
    }

    @Override
    @NotNull
    public Order save(@NotNull Order order) {
        messageSenderService.send(order);
        return super.save(order);
    }

    @Override
    @Nullable
    public List<Order> getNewByTutorId(@NotNull Long tutorId) {
        return repository.findByTutorIdAndOrderStatusCode(tutorId, OrderStatus.Code.NEW);
    }

    @Override
    @Nullable
    public List<Order> getByTutorId(@NotNull Long tutorId) {
        return repository.findByTutorId(tutorId);
    }

    @Override
    @NotNull
    public Order updateOrderStatus(@NotNull Long id, @NotNull OrderStatus.Code status) throws LogicException {
        Order order = super.get(id);
        order.setOrderStatus(orderStatusRepository.findByCode(status));
        messageSenderService.send(order);

        return super.save(order);
    }

}
