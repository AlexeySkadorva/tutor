package by.bsu.tutor.controller.order;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.administration.HistoryLesson;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.tutor.TutorEvaluation;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.service.administration.UserService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.order.OrderService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private TutorService tutorService;
    @Autowired private UserService userService;
    @Autowired private ClientService clientService;


    @GetMapping(value = "/tutors/{id}/order")
    public String saveOrder(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails user) throws LogicException {
        return "/order/new";
    }

    @PostMapping(value = "/tutors/{id}/order")
    public String addOrder(@PathVariable Long id, String message,
                           @AuthenticationPrincipal UserDetails user) throws LogicException {
        Tutor tutor = tutorService.get(id);
        Client client = clientService.getByUser(userService.findByLogin(user.getUsername()));
        Order order = new Order(tutor, client, message);

        orderService.save(order);
        return "/order/send";
    }

    @GetMapping(value = "/tutors/{id}/orders")
    public String getOrder(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetails user) throws LogicException {
        List<Order> orders = orderService.getByTutorId(id);
        model.addAttribute("orders", orders);
        return "/order/list";
    }

    @GetMapping(value = "/orders/{id}/approve")
    public String approveOrder(@PathVariable Long id, @AuthenticationPrincipal UserDetails user) throws LogicException {
        orderService.approveOrder(id);
        return "/order/approved";
    }

}
