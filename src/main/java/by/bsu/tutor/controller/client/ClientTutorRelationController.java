package by.bsu.tutor.controller.client;

import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/administration/relations")
public class ClientTutorRelationController {

    @Autowired private ClientTutorRelationService clientTutorRelationService;
    @Autowired private ClientService clientService;
    @Autowired private TutorService tutorService;


    @RequestMapping
    public String getRelations(Model model){
        model.addAttribute("relations", clientTutorRelationService.getAll());
        return "relations";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createRelation(@ModelAttribute(value = "relation") ClientTutorRelation relation){
        clientTutorRelationService.save(relation);
        return "redirect:/administration/relations";
    }

    @RequestMapping(value = "/new")
    public String getToPage(Model model){
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("tutors", tutorService.getAll());
        model.addAttribute("relation", new ClientTutorRelation());
        return "relation";
    }

}
