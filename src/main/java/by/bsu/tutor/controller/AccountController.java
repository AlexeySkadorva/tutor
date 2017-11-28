package by.bsu.tutor.controller;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.Role;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.ClientParentRepository;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.order.OrderService;
import by.bsu.tutor.service.tutor.TutorEvaluationService;
import by.bsu.tutor.service.tutor.TutorService;
import by.bsu.tutor.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/account")
@Controller
public class AccountController {

    @Autowired
    private UserService userService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TutorEvaluationService tutorEvaluationService;
    @Autowired
    private ClientParentRepository clientParentRepository;
    @Autowired
    private ClientTutorRelationService clientTutorRelationService;
    @Autowired
    private OrderService orderService;


    @GetMapping
    public String getAccountInfo(Model model, @AuthenticationPrincipal UserDetails user) throws LogicException {
        model.addAttribute("account", true);

       String roleName = getRoleName(user);

       User currentUser = userService.findByEmail(user.getUsername());

        if (Role.Code.CLIENT.name().equals(roleName)) {
            Client client = clientService.getByUser(currentUser);
            model.addAttribute("client", client);
            model.addAttribute("clientParent", clientParentRepository.findByClientId(client.getId()));
            model.addAttribute("relations", clientTutorRelationService.getByClientId(client.getId()));
            return "/client";
        }

        if (Role.Code.TUTOR.name().equals(roleName)) {
            Tutor tutor = tutorService.getByUser(currentUser);
            model.addAttribute("tutor", tutor);
            model.addAttribute("evaluation", tutorEvaluationService.getMiddleEvaluation(tutor));
            model.addAttribute("hasNewOrder", !CollectionUtils.isEmpty(orderService.getNewByTutorId(tutor.getId())));
            return "/tutor";
        }
        return "redirect:/";
    }

    private String getRoleName(UserDetails user) {
        for (GrantedAuthority authority : user.getAuthorities()) {
            return authority.getAuthority();
        }
        return StringUtils.EMPTY;
    }

}
