package by.bsu.tutor.service.administration.impl;

import by.bsu.tutor.models.entity.note.SystemNote;
import by.bsu.tutor.repositories.SystemNoteRepository;
import by.bsu.tutor.service.administration.SystemNoteService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
public class DefaultSystemNoteService extends DefaultCrudService<SystemNote, SystemNoteRepository> implements SystemNoteService{

    @Autowired
    public DefaultSystemNoteService(@NotNull SystemNoteRepository repository) {
        super(repository);
    }

    @Override
    public List<SystemNote> getAll() {
        return repository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    public SystemNote saveNoteForClient(@NotNull SystemNote note){
        note.setCreatedDate(new Date());
        return save(note);
    }

}
