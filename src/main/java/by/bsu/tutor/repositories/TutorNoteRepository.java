package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.models.entity.tutor.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TutorNoteRepository extends BaseRepository<TutorNote> {

    List<TutorNote> findByTutor(Tutor tutor);

}

