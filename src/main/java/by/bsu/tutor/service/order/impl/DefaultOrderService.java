package by.bsu.tutor.service.order.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderStatus;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.OrderRepository;
import by.bsu.tutor.repositories.OrderStatusRepository;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.order.OrderService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Service
public class DefaultOrderService extends DefaultCrudService<Order, OrderRepository> implements OrderService {

    @Autowired private TutorService tutorService;
    @Autowired private ClientService clientService;
    @Autowired private OrderStatusRepository orderStatusRepository;


    public DefaultOrderService(@NotNull OrderRepository repository) {
        super(repository);
    }

    @Override
    public Order save(@NotNull Long tutorId, @NotNull User user) throws LogicException {
        Order order = new Order();
        order.setTutor(tutorService.get(tutorId));
        order.setClient(clientService.get(1L));
        order.setOrderStatus(orderStatusRepository.findByCode(OrderStatus.Code.NEW));
        order.setCreatedDate(new Date());

        return super.save(order);
    }

}
