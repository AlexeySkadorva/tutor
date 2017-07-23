package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.note.SystemNote;

import java.util.List;

public interface SystemNoteRepository extends BaseRepository<SystemNote> {

    List<SystemNote> findAllByOrderByCreatedDateDesc();

}
