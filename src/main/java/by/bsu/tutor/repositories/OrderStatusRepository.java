package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

    OrderStatus findByCode(OrderStatus.Code code);
}
