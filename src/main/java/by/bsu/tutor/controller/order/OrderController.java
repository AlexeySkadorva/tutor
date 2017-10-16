package by.bsu.tutor.controller.order;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.service.order.OrderService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    @Autowired private OrderService orderService;


    @GetMapping(value = "/tutors/{id}/order")
    public String saveOrder(@PathVariable Long id, @AuthenticationPrincipal User user) throws LogicException {
        user = new User();
        user.setId(1L);
        orderService.save(id, user);
        return "/main";
    }

    @GetMapping(value = "/orders")
    public String getOrders(Model model) throws LogicException {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        return "/administration/orders";
    }

}
