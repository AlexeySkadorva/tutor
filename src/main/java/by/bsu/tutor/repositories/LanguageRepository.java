package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.tutor.Language;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexey Skadorva
 */
public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
