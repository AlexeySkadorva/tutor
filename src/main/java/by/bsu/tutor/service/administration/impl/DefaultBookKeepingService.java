package by.bsu.tutor.service.administration.impl;

import by.bsu.tutor.models.entity.administration.BookKeeping;
import by.bsu.tutor.repositories.BookKeepingRepository;
import by.bsu.tutor.service.administration.BookKeepingService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class DefaultBookKeepingService extends DefaultCrudService<BookKeeping, BookKeepingRepository> implements BookKeepingService {

    @Autowired
    public DefaultBookKeepingService(@NotNull BookKeepingRepository repository) {
        super(repository);
    }

}