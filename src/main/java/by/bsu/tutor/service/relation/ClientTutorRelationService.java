package by.bsu.tutor.service.relation;

import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ClientTutorRelationService extends CrudService<ClientTutorRelation> {

    @NotNull List<ClientTutorRelation> getByTutorId(@NotNull Long id);

    @NotNull List<ClientTutorRelation> getByClientId(@NotNull Long id);
}
