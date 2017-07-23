package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.relation.ClientTutorRelation;

import java.util.List;

public interface ClientTutorRelationRepository extends BaseRepository<ClientTutorRelation> {

    List<ClientTutorRelation> findByTutorId(Long id);

    List<ClientTutorRelation> findByClientId(Long id);

}
