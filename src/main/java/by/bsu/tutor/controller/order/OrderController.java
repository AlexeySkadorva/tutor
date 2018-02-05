package by.bsu.tutor.controller.order;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.order.Order;
import by.bsu.tutor.models.entity.order.OrderLesson;
import by.bsu.tutor.models.entity.order.OrderStatus;
import by.bsu.tutor.models.entity.relation.ClientTutorLesson;
import by.bsu.tutor.models.entity.relation.ClientTutorRelation;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.tutor.TutorSubject;
import by.bsu.tutor.repositories.LessonTypeRepository;
import by.bsu.tutor.repositories.OrderStatusRepository;
import by.bsu.tutor.service.user.UserService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.relation.ClientTutorRelationService;
import by.bsu.tutor.service.order.OrderService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class OrderController {

    @Autowired private UserService userService;
    @Autowired private OrderService orderService;
    @Autowired private TutorService tutorService;
    @Autowired private ClientService clientService;
    @Autowired private LessonTypeRepository lessonTypeRepository;
    @Autowired private OrderStatusRepository orderStatusRepository;
    @Autowired private ClientTutorRelationService clientTutorRelationService;


    @PostMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String saveOrder(@RequestBody Order order) throws LogicException {
        orderService.createNewOrder(order);
        return "/order/send";
    }

    @GetMapping(value = "/tutors/{id}/order")
    public String saveOrder(@PathVariable Long id, @AuthenticationPrincipal UserDetails user, Model model) throws LogicException {
        if (null == user) {
            return "redirect:/clients/new";
        }
        Tutor tutor = tutorService.get(id);
        Tutor smallTutor = new Tutor();
        smallTutor.setId(id);
        List<OrderDto> orderDtos = new ArrayList<>();
        for(TutorSubject tutorSubject : tutor.getTutorSubjects()) {
            orderDtos.add(new OrderDto(tutorSubject.getSubject(), tutorSubject.getTutorSubjectDurations()));
        }
        Client client = clientService.getByUser(userService.findByEmail(user.getUsername()));
        Client smallClient = new Client();
        smallClient.setId(client.getId());
        Order order = new Order(smallTutor, smallClient, orderStatusRepository.findByCode(OrderStatus.Code.NEW));

        model.addAttribute("orderrr", order);
        model.addAttribute("orderDtos", orderDtos);
        model.addAttribute("lessonTypes", tutor.getLessonTypes());

        return "/order/new";
    }

    @GetMapping(value = "/tutors/{id}/orders")
    public String getOrders(@PathVariable Long id, Model model) throws LogicException {
        model.addAttribute("orders", orderService.getByTutorId(id));
        return "/order/list";
    }

    @GetMapping(value = "/orders/{id}/approve")
    public String approveOrder(@PathVariable Long id, Model model) throws LogicException {
        Order order = orderService.updateOrderStatus(id, OrderStatus.Code.APPROVED);
        ClientTutorRelation clientTutorRelation = clientTutorRelationService.createByOrder(order);
        model.addAttribute("client", clientTutorRelation.getClient());
        return "/order/approved";
    }

    @GetMapping(value = "/orders/{id}/decline")
    public String declineOrder(@PathVariable Long id) throws LogicException {
        orderService.updateOrderStatus(id, OrderStatus.Code.DECLINED);
        return "/order/declined";
    }

}
