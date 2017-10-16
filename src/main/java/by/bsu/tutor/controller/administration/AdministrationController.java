package by.bsu.tutor.controller.administration;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.Role;
import by.bsu.tutor.models.enums.Gender;
import by.bsu.tutor.repositories.RoleRepository;
import by.bsu.tutor.repositories.SubjectRepository;
import by.bsu.tutor.service.administration.UserService;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/administration")
@Controller
public class AdministrationController {

    @Autowired
    private TutorService tutorService;

    @Autowired
    private ClientService clientService;

    @Autowired private UserService userService;
    @Autowired private RoleRepository roleRepository;
    @Autowired private SubjectRepository subjectRepository;

    @RequestMapping
    public Object getAdministration() {
        return "administration/administration";
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public String sadveTutor(@ModelAttribute(value = "tutor") Tutor tutor, Model model) {

        return "calendar";
    }

    @RequestMapping(value = "/tutors/new", method = RequestMethod.GET)
    public Object getNewUser(Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("tutor", new Tutor());
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("genders", Gender.values());
        return "administration/tutor/new";
    }

    @RequestMapping(value = "/tutors", method = RequestMethod.POST)
    public String saveTutor(@ModelAttribute(value = "tutor") Tutor tutor, Model model) {
        tutor.getUser().setRole(roleRepository.findByCode(Role.Code.TUTOR.name()));

        tutorService.save(tutor);
        model.addAttribute("userId", tutor.getUser().getId());
        return "photo";
    }

    @RequestMapping(value = "/clients/new")
    public Object getNewClient(Model model) throws LogicException {
        Client client = new Client();

        model.addAttribute("client", client);
        model.addAttribute("genders", Gender.values());
        model.addAttribute("tutors", tutorService.getAll());
        return "administration/client/new";
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public String saveClient(@ModelAttribute(value = "client") Client client, Model model) {
        client.getUser().setRole(roleRepository.findByCode("CLIENT"));

        clientService.save(client);
        model.addAttribute("userId", client.getUser().getId());
        return "photo";
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

    @RequestMapping(value = "/users")
    public Object getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "administration/user/list";
    }

}
