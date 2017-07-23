package by.bsu.tutor.controller.client;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.administration.HistoryLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/{id}")
    public String getClient(@PathVariable Long id, Model model) throws LogicException {
        List<ClientTutorRelation> relations = clientTutorRelationService.getByClientId(id);
        model.addAttribute("client", clientService.get(id));
        List<HistoryLesson> historyLessons = historyLessonService.getByRelationId(relations.get(0).getId());
        if(!CollectionUtils.isEmpty(historyLessons)) {
            model.addAttribute("lastLesson", historyLessons.get(historyLessons.size() - 1));
        }
        return "client";
    }

}
