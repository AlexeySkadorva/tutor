package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.note.SystemNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemNoteRepository extends BaseRepository<SystemNote> {

    List<SystemNote> findAllByOrderByCreatedDateDesc();

}
