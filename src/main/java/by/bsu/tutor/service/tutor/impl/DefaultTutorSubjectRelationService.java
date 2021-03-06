package by.bsu.tutor.service.tutor.impl;

import by.bsu.tutor.models.entity.relation.TutorSubjectRelation;
import by.bsu.tutor.repositories.TutorSubjectRelationRepository;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.tutor.TutorSubjectRelationService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

public class DefaultTutorSubjectRelationService extends DefaultCrudService<TutorSubjectRelation, TutorSubjectRelationRepository>
        implements TutorSubjectRelationService {

    public DefaultTutorSubjectRelationService(@NotNull TutorSubjectRelationRepository repository) {
        super(repository);
    }

}
