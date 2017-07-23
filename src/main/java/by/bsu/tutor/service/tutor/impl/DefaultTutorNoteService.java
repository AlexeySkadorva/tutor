package by.bsu.tutor.service.tutor.impl;

import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.repositories.TutorNoteRepository;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.tutor.TutorNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DefaultTutorNoteService extends DefaultCrudService<TutorNote, TutorNoteRepository> implements TutorNoteService {

    private TutorNoteRepository tutorNoteRepository;


    @Autowired
    public DefaultTutorNoteService(@NotNull TutorNoteRepository repository, TutorNoteRepository tutorNoteRepository) {
        super(repository);
        this.tutorNoteRepository = tutorNoteRepository;
    }

    @Override
    public List<TutorNote> getNotesForTutor(@NotNull Tutor tutor){
        return tutorNoteRepository.findByTutor(tutor);
    }
}
