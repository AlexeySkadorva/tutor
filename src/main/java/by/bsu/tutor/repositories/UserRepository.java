package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends BaseRepository<User> {

    User findByLogin(String login);

}

