package by.bsu.tutor.controller.administration;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.Role;
import by.bsu.tutor.models.enums.Gender;
import by.bsu.tutor.repositories.LessonTypeRepository;
import by.bsu.tutor.repositories.RoleRepository;
import by.bsu.tutor.repositories.SubjectRepository;
import by.bsu.tutor.service.administration.UserService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.order.OrderService;
import by.bsu.tutor.service.tutor.TutorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Api(description = "Controller for administration logic", produces = "application/json")

@RequestMapping("/administration")
@Controller
public class AdministrationController {

    @Autowired private UserService userService;
    @Autowired private TutorService tutorService;
    @Autowired private ClientService clientService;
    @Autowired private OrderService orderService;


    @RequestMapping
    public Object getAdministration() {
        return "administration/administration";
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String sadveTutor(@ModelAttribute(value = "tutor") Tutor tutor, Model model) {

        return "calendar";
    }

    @RequestMapping(value = "/tutors")
    public String getTutorList(Model model){
        model.addAttribute("tutors", tutorService.getAll());
        return "administration/tutor/list";
    }

    @RequestMapping(value = "/clients")
    public String getClientList(Model model){
        model.addAttribute("clients", clientService.getAll());
        return "administration/client/list";
    }

    @RequestMapping(value = "/orders")
    public String getOrders(Model model){
        model.addAttribute("orders", orderService.getAll());
        return "administration/order/list";
    }

    @RequestMapping(value = "/users")
    public Object getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "administration/user/list";
    }

}
