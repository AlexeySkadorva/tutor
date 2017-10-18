package by.bsu.tutor.service.order.impl;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderStatus;
import by.bsu.tutor.models.entity.tutor.TutorInvoice;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.OrderRepository;
import by.bsu.tutor.repositories.OrderStatusRepository;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.order.OrderService;
import by.bsu.tutor.service.tutor.TutorService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
public class DefaultOrderService extends DefaultCrudService<Order, OrderRepository> implements OrderService {

    @Autowired private TutorService tutorService;
    @Autowired private ClientService clientService;
    @Autowired private OrderStatusRepository orderStatusRepository;


    public DefaultOrderService(@NotNull OrderRepository repository) {
        super(repository);
    }

    @Override
    public Order save(@NotNull Order order) {
        order.setOrderStatus(orderStatusRepository.findByCode(OrderStatus.Code.NEW));
        return super.save(order);
    }

    @Override
    public List<Order> getByTutorId(Long tutorId) {
        return repository.findByTutorId(tutorId);
    }

    @Override
    public Order approveOrder(Long id) throws LogicException {
        Order order = super.get(id);
        order.setOrderStatus(orderStatusRepository.findByCode(OrderStatus.Code.APPROVED));

        return super.save(order);
    }
}
