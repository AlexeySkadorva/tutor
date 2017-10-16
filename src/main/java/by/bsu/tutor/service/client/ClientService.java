package by.bsu.tutor.service.client;

import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.service.base.CrudService;

import javax.validation.constraints.NotNull;

public interface ClientService extends CrudService<Client> {

    @NotNull Client getByUser(@NotNull User user);

}
