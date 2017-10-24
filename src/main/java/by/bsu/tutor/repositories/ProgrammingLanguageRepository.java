package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.tutor.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexey Skadorva
 */
public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Integer> {
}
