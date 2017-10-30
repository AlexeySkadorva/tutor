package by.bsu.tutor.repositories;

import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.client.ClientParent;

public interface ClientParentRepository extends BaseRepository<ClientParent> {

    ClientParent findByClientId(Long clientId);
}
