package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends BaseRepository<Client> {

    Client findByUser(User user);

}