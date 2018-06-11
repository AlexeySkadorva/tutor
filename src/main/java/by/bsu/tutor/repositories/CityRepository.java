package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.base.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Integer> {
}
