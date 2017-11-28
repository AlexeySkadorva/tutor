package by.bsu.tutor.controller.client;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.user.Role;
import by.bsu.tutor.models.enums.Gender;
import by.bsu.tutor.repositories.ClientParentRepository;
import by.bsu.tutor.repositories.RoleRepository;
import by.bsu.tutor.service.administration.HistoryLessonService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "clients")
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientParentRepository clientParentRepository;
    @Autowired
    private HistoryLessonService historyLessonService;
    @Autowired
    private ClientTutorRelationService clientTutorRelationService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping(value = "/new")
    public Object getNewClient(Model model) throws LogicException {
        Client client = new Client();

        model.addAttribute("client", client);
        model.addAttribute("genders", Gender.values());
        return "client/new";
    }

    @GetMapping(value = "/{id}/edit")
    public Object h(@PathVariable Long id, Model model) throws LogicException {
        Client client = clientService.get(id);
        client.setClientParent(clientParentRepository.findByClientId(id));

        model.addAttribute("client", client);
        model.addAttribute("genders", Gender.values());
        return "client/edit";
    }

    @PostMapping(value = "/{id}")
    public String saveClient(@PathVariable Long id, @ModelAttribute(value = "client") Client client, Model model) {
        client.setId(id);
        clientService.save(client);
        return "redirect:/account";
    }

    @PostMapping
    public String saveClient(@ModelAttribute(value = "client") Client client, Model model) {
        client.getUser().setRole(roleRepository.findByCode(Role.Code.CLIENT));

        clientService.save(client);
        model.addAttribute("userId", client.getUser().getId());
        return "user/photo";
    }

    @GetMapping(value = "/{id}")
    public String getClient(@PathVariable Long id, Model model, Principal principal) throws LogicException {
        List<ClientTutorRelation> relations = clientTutorRelationService.getByClientId(id);
        model.addAttribute("client", clientService.get(id));
        if (!CollectionUtils.isEmpty(relations)) {
            List<HistoryLesson> historyLessons = historyLessonService.getByRelationId(relations.get(0).getId());
            if (!CollectionUtils.isEmpty(historyLessons))
                model.addAttribute("lastLesson", historyLessons.get(historyLessons.size() - 1));
        }
        return "client/client";
    }

}
