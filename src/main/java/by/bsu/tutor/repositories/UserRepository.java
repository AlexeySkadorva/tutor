package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.user.User;

public interface UserRepository extends BaseRepository<User> {

    User findByLogin(String login);

}

