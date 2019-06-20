package by.bsu.tutor.repositories;


import by.bsu.tutor.models.Message;

import java.util.List;

public interface MessageRepository extends BaseRepository<Message> {

    List<Message> findAllByOrderByDateAsc();

}
