package by.bsu.tutor.service.tutor;

import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.service.base.CrudService;
import com.sun.istack.internal.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface TutorNoteService extends CrudService<TutorNote> {

    @Nullable List<TutorNote> getNotesForTutor(@NotNull Tutor tutor);
}
