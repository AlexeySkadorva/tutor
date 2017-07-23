package by.bsu.tutor.controller.administration;

import by.bsu.tutor.models.entity.administration.Statistic;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.service.administration.BookKeepingService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.administration.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/administration")
public class BookKeepingController {

    @Autowired ClientService clientService;
    @Autowired private BookKeepingService bookKeepingService;
    @Autowired private StatisticService statisticService;
    @Autowired private ClientTutorRelationService clientTutorRelationService;

    @RequestMapping(value = "/bookkeeping")
    public String getTutorList(Model model){
        model.addAttribute("bookkeeping", bookKeepingService.getAll());
        return "administration/bookkeeping/list";
    }

    @RequestMapping(value = "/clients/{id}/bookkeeping")
    public String getTutorList(@PathVariable Long id, Model model){
        List<ClientTutorRelation> clientTutorRelations = clientTutorRelationService.getByClientId(id);
        List<Statistic> statistics = new ArrayList<>();
        for(ClientTutorRelation clientTutorRelation : clientTutorRelations) {
            statistics.addAll(statisticService.getByRelationId(clientTutorRelation.getId()));
        }

        model.addAttribute("statistic", statistics);
        return "administration/bookkeeping/bookkeeping";
    }

}
