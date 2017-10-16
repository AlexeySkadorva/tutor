package by.bsu.tutor.controller.client;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.user.User;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "clients")
public class ClientController {

    @Autowired private ClientService clientService;
    @Autowired private HistoryLessonService historyLessonService;
    @Autowired private ClientTutorRelationService clientTutorRelationService;

    @RequestMapping
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "clients";
    }

    //@PreAuthorize("hasRole('TUTOR')")
    //@PreAuthorize("hasRole('ROLE_Репетитор')")
    @RequestMapping(value = "/{id}")
    public String getClient(@PathVariable Long id, Model model, Principal principal) throws LogicException {
        List<ClientTutorRelation> relations = clientTutorRelationService.getByClientId(id);
        model.addAttribute("client", clientService.get(id));
        List<HistoryLesson> historyLessons = historyLessonService.getByRelationId(relations.get(0).getId());
        if(!CollectionUtils.isEmpty(historyLessons)) {
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
