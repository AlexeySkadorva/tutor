package by.bsu.tutor.service.relation;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ClientTutorRelationService extends CrudService<ClientTutorRelation> {

    @NotNull ClientTutorRelation createByOrder(@NotNull Order order) throws LogicException;

    @NotNull List<ClientTutorRelation> getByTutorId(@NotNull Long id);

    @NotNull List<ClientTutorRelation> getByClientId(@NotNull Long id);
}
