package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.client.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {
}
