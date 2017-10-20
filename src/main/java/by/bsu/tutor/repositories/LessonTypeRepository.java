package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.order.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexey Skadorva
 */
public interface LessonTypeRepository extends JpaRepository<LessonType, Integer> {
}
