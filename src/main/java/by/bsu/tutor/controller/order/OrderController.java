package by.bsu.tutor.controller.order;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderStatus;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.repositories.LessonTypeRepository;
import by.bsu.tutor.repositories.OrderStatusRepository;
import by.bsu.tutor.service.administration.UserService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.order.OrderService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @Autowired private OrderService orderService;
    @Autowired private TutorService tutorService;
    @Autowired private UserService userService;
    @Autowired private ClientService clientService;
    @Autowired private ClientTutorRelationService clientTutorRelationService;
    @Autowired private LessonTypeRepository lessonTypeRepository;
    @Autowired private OrderStatusRepository orderStatusRepository;


    @PostMapping(value = "/orders")
    public String saveOrder(@ModelAttribute Order order) throws LogicException {
        orderService.save(order);
        return "/send";
    }

    @GetMapping(value = "/tutors/{id}/order")
    public String saveOrder(@PathVariable Long id, @AuthenticationPrincipal UserDetails user, Model model) throws LogicException {
        Tutor tutor = tutorService.get(id);
        Client client = clientService.getByUser(userService.findByLogin(user.getUsername()));
        Order order = new Order(tutor, client, orderStatusRepository.findByCode(OrderStatus.Code.NEW));

        model.addAttribute("order", order);
        model.addAttribute("lessonTypes", lessonTypeRepository.findAll());
        return "/order/new";
    }

//    @PostMapping(value = "/tutors/{id}/order")
//    public String addOrder(@PathVariable Long id, String message,
//                           @AuthenticationPrincipal UserDetails user) throws LogicException {
//        Tutor tutor = tutorService.get(id);
//        Client client = clientService.getByUser(userService.findByLogin(user.getUsername()));
//        Order order = new Order(tutor, client, message);
//
//        orderService.saveNewOrder(order);
//        return "/order/send";
//    }

    @GetMapping(value = "/tutors/{id}/orders")
    public String getOrders(@PathVariable Long id, Model model) throws LogicException {
        model.addAttribute("orders", orderService.getByTutorId(id));
        return "/order/list";
    }

    @GetMapping(value = "/orders/{id}/approve")
    public String approveOrder(@PathVariable Long id, Model model) throws LogicException {
        Order order = orderService.updateOrderStatus(id, OrderStatus.Code.APPROVED);

        ClientTutorRelation clientTutorRelation = new ClientTutorRelation(order.getClient(), order.getTutor());
        clientTutorRelationService.save(clientTutorRelation);

        model.addAttribute("client", clientTutorRelation.getClient());
        return "/order/approved";
    }

    @GetMapping(value = "/orders/{id}/decline")
    public String declineOrder(@PathVariable Long id) throws LogicException {
        orderService.updateOrderStatus(id, OrderStatus.Code.DECLINED);
        return "/order/declined";
    }

}
