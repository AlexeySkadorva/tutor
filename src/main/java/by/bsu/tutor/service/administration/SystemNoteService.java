package by.bsu.tutor.service.administration;

import by.bsu.tutor.models.entity.note.SystemNote;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;

public interface SystemNoteService extends CrudService<SystemNote> {

    SystemNote saveNoteForClient(@NotNull SystemNote note);

}
