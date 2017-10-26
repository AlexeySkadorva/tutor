package by.bsu.tutor.controller;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.Role;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.ClientParentRepository;
import by.bsu.tutor.repositories.RoleRepository;
import by.bsu.tutor.service.administration.UserService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.tutor.TutorEvaluationService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequestMapping("/account")
@Controller
public class AccountController {

    @Autowired private UserService userService;
    @Autowired private TutorService tutorService;
    @Autowired private ClientService clientService;
    @Autowired private TutorEvaluationService tutorEvaluationService;
    @Autowired private ClientParentRepository clientParentRepository;
    @Autowired private ClientTutorRelationService clientTutorRelationService;


    @GetMapping
    public String getTutor(Model model, @AuthenticationPrincipal UserDetails user) throws LogicException {

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if(roles.get(0).contains(Role.Code.CLIENT.name())) {
            User user1 = userService.findByLogin(user.getUsername());
            Client client=clientService.getByUser(user1);
            model.addAttribute("clientParent", clientParentRepository.findByClient(client));
            model.addAttribute("relations", clientTutorRelationService.getByClientId(client.getId()));

            model.addAttribute("client", client);
          return "/client";
        }
        User user1 = userService.findByLogin(user.getUsername());
        Tutor tutor=tutorService.getByUser(user1);
        model.addAttribute("evaluation", tutorEvaluationService.getMiddleEvaluation(tutor));

        model.addAttribute("tutor", tutor);
        return "/tutor";
    }

}
