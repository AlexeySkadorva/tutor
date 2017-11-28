package by.bsu.tutor.controller.administration;

import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.service.relation.HistoryLessonService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.relation.ClientTutorRelationService;
import by.bsu.tutor.service.order.OrderService;
import by.bsu.tutor.service.relation.TutorInvoiceService;
import by.bsu.tutor.service.tutor.TutorService;
import by.bsu.tutor.service.user.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(description = "Controller for administration logic", produces = "application/json")

@RequestMapping("/administration")
@Controller
public class AdministrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientTutorRelationService clientTutorRelationService;
    @Autowired
    private TutorInvoiceService tutorInvoiceService;
    @Autowired
    private HistoryLessonService historyLessonService;


    @GetMapping
    public Object getAdministration() {
        return "administration/administration";
    }

    @GetMapping(value = "/calendar")
    public String getCalendar() {
        return "calendar";
    }

    @GetMapping(value = "/tutors")
    public String getTutors(Model model) {
        model.addAttribute("tutors", tutorService.getAll());
        return "administration/tutor/list";
    }

    @GetMapping(value = "/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "administration/client/list";
    }

    @GetMapping(value = "/orders")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "administration/order/list";
    }

    @GetMapping(value = "/users")
    public Object getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "administration/user/list";
    }

    @GetMapping(value = "/relations")
    public String getRelations(Model model) {
        model.addAttribute("relations", clientTutorRelationService.getAll());
        return "administration/relation/list";
    }

    @GetMapping(value = "relations/new")
    public String getToPage(Model model) {
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("tutors", tutorService.getAll());
        model.addAttribute("relation", new ClientTutorRelation());
        return "administration/relation/new";
    }

    @GetMapping(value = "/invoices")
    public String getInvoices(Model model) {
        model.addAttribute("invoices", tutorInvoiceService.getAll());
        return "administration/invoices/list";
    }

    @GetMapping(value = "/history")
    public String log(Model model) {
        model.addAttribute("history", historyLessonService.getAll());
        return "administration/relation/history/list";
    }

}
