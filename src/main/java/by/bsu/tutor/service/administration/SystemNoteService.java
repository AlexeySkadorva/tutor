package by.bsu.tutor.service.administration;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.note.SystemNote;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;

public interface SystemNoteService extends CrudService<SystemNote> {

    @NotNull SystemNote saveNoteForClient(@NotNull SystemNote note) throws LogicException;

}
