package by.bsu.tutor.controller.tutor;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.client.Client;
import by.bsu.tutor.models.entity.tutor.Subject;
import by.bsu.tutor.models.entity.tutor.Tutor;
import by.bsu.tutor.models.entity.user.Role;
import by.bsu.tutor.models.entity.user.User;
import by.bsu.tutor.repositories.RoleRepository;
import by.bsu.tutor.repositories.SubjectRepository;
import by.bsu.tutor.service.client.ClientService;
import by.bsu.tutor.service.tutor.TutorService;
import by.bsu.tutor.service.administration.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/")
@RestController
public class DirectoryController {

    @Autowired private TutorService tutorService;
    @Autowired private UserService userService;
    @Autowired private ClientService clientService;

    @Autowired private RoleRepository roleRepository;
    @Autowired private SubjectRepository subjectRepository;


    @RequestMapping(value = "tutors")
    public List<Tutor> getTutors() {
        return tutorService.getAll();
    }

    @RequestMapping(value = "tutor/{id}")
    public Tutor getTutor(@PathVariable Long id) throws LogicException {
        return tutorService.get(id);
    }

    @RequestMapping(value = "users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "user/{id}")
    public User getUser(@PathVariable Long id) throws LogicException {
        return userService.get(id);
    }

    @RequestMapping(value = "clients")
    public List<Client> getClients() {
        return clientService.getAll();
    }

    @RequestMapping(value = "client/{id}")
    public Client getClient(@PathVariable Long id) throws LogicException {
        return clientService.get(id);
    }

    @RequestMapping(value = "roles")
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @RequestMapping(value = "subjects")
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }
}
