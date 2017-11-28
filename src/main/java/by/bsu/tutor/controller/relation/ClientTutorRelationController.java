package by.bsu.tutor.controller.relation;

import by.bsu.tutor.service.client.ClientTutorRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/relations")
public class ClientTutorRelationController {

    @Autowired protected ClientTutorRelationService clientTutorRelationService;

}
