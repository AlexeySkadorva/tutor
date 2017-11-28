package by.bsu.tutor.service.client.impl;

import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.client.ClientParent;
import by.bsu.tutor.models.entity.client.ClientType;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.ClientParentRepository;
import by.bsu.tutor.repositories.ClientRepository;
import by.bsu.tutor.service.user.UserService;
import by.bsu.tutor.service.base.impl.DefaultCrudService;
import by.bsu.tutor.service.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class DefaultClientService extends DefaultCrudService<Client, ClientRepository> implements ClientService {

    private final UserService userService;
    private final ClientParentRepository clientParentRepository;


    public DefaultClientService(@NotNull ClientRepository repository, UserService userService,
                                ClientParentRepository clientParentRepository) {
        super(repository);
        this.userService = userService;
        this.clientParentRepository = clientParentRepository;
    }

    @Override
    @NotNull
    public Client getByUser(@NotNull User user) {
        return repository.findByUser(user);
    }

    @Override
    @NotNull
    public Client save(@NotNull Client client) {
        User user = userService.save(client.getUser());
        client.setUser(user);
        Client savedClient = super.save(client);
        if (ClientType.Code.PRESCHOOLER.equals(client.getClientType().getCode())) {
            ClientParent clientParent = client.getClientParent();
            clientParent.setClientId(savedClient.getId());
            clientParentRepository.save(clientParent);
        }
        return savedClient;
    }

}
