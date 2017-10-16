package by.bsu.tutor.service.client.impl;

import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.ClientRepository;
import by.bsu.tutor.service.administration.UserService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class DefaultClientService extends DefaultCrudService<Client, ClientRepository> implements ClientService {

    private UserService userService;


    @Autowired
    public DefaultClientService(@NotNull ClientRepository repository, UserService userService) {
        super(repository);
        this.userService = userService;
    }

    @Override
    public Client getByUser(@NotNull User user) {
        return repository.findByUser(user);
    }

    @Override
    public Client save(@NotNull Client client) {
        User user = userService.save(client.getUser());
        client.setUser(user);

        return super.save(client);
    }
}
