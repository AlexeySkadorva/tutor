package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.models.entity.tutor.Tutor;

import java.util.List;

public interface TutorNoteRepository extends BaseRepository<TutorNote> {

    List<TutorNote> findByTutor(Tutor tutor);

}

