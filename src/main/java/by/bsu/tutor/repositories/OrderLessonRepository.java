package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.order.OrderLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLessonRepository extends JpaRepository<OrderLesson, Long> {
}
