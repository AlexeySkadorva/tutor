package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.tutor.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}

