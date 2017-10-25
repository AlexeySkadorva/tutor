package by.bsu.tutor.controller.client;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.user.Role;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.models.enums.Gender;
import by.bsu.tutor.repositories.RoleRepository;
import by.bsu.tutor.service.administration.HistoryLessonService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "clients")
public class ClientController {

    @Autowired private ClientService clientService;
    @Autowired private HistoryLessonService historyLessonService;
    @Autowired private ClientTutorRelationService clientTutorRelationService;
    @Autowired private RoleRepository roleRepository;

    @GetMapping(value = "/new")
    public Object getNewClient(Model model) throws LogicException {
        Client client = new Client();

        model.addAttribute("client", client);
        model.addAttribute("genders", Gender.values());
        return "administration/client/new";
    }

    @PostMapping
    public String saveClient(@ModelAttribute(value = "client") Client client, Model model) {
        client.getUser().setRole(roleRepository.findByCode(Role.Code.CLIENT));

        clientService.save(client);
        model.addAttribute("userId", client.getUser().getId());
        return "photo";
    }

    @GetMapping
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "clients";
    }

    //@PreAuthorize("hasRole('TUTOR')")
    //@PreAuthorize("hasRole('ROLE_Репетитор')")
    @GetMapping(value = "/{id}")
    public String getClient(@PathVariable Long id, Model model, Principal principal) throws LogicException {
        List<ClientTutorRelation> relations = clientTutorRelationService.getByClientId(id);
        model.addAttribute("client", clientService.get(id));
        if(!CollectionUtils.isEmpty(relations)) {
            List<HistoryLesson> historyLessons = historyLessonService.getByRelationId(relations.get(0).getId());
            model.addAttribute("lastLesson", historyLessons.get(historyLessons.size() - 1));
        }
        return "client";
    }

//    @PreAuthorize("hasRole('ROLE_TUTOR')")
//    @RequestMapping(value = "/{id}/w")
//    public String getCleient(@PathVariable Long id, Model model, Principal principal) throws LogicException {
//        List<ClientTutorRelation> relations = clientTutorRelationService.getByClientId(id);
//        model.addAttribute("client", clientService.get(id));
//        List<HistoryLesson> historyLessons = historyLessonService.getByRelationId(relations.get(0).getId());
//        if(!CollectionUtils.isEmpty(historyLessons)) {
//            model.addAttribute("lastLesson", historyLessons.get(historyLessons.size() - 1));
//        }
//        return "client";
//    }
//
//    @PreAuthorize("hasRole('Репетитор')")
//    @RequestMapping(value = "/{id}/q")
//    public String getCleisent(@PathVariable Long id, Model model, Principal principal) throws LogicException {
//        List<ClientTutorRelation> relations = clientTutorRelationService.getByClientId(id);
//        model.addAttribute("client", clientService.get(id));
//        List<HistoryLesson> historyLessons = historyLessonService.getByRelationId(relations.get(0).getId());
//        if(!CollectionUtils.isEmpty(historyLessons)) {
//            model.addAttribute("lastLesson", historyLessons.get(historyLessons.size() - 1));
//        }
//        return "client";
//    }


}
