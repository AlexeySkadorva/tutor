package by.bsu.tutor.service.client.impl;

import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.repositories.ClientTutorRelationRepository;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class DefaultClinicalTutorRelationService extends DefaultCrudService<ClientTutorRelation, ClientTutorRelationRepository>
        implements ClientTutorRelationService {

    @Autowired
    public DefaultClinicalTutorRelationService(@NotNull ClientTutorRelationRepository repository) {
        super(repository);
    }

    @Override
    public List<ClientTutorRelation> getByTutorId(@NotNull Long id) {
        return repository.findByTutorId(id);
    }

    @Override
    public List<ClientTutorRelation> getByClientId(@NotNull Long id) {
        return repository.findByClientId(id);
    }

}
