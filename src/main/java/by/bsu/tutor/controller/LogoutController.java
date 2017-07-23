package by.bsu.tutor.controller;

import by.bsu.tutor.util.ControllerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {
    @RequestMapping(value = "/logoutDialog")
    public String logoutDialog(Model model, HttpServletRequest request) {
        ControllerUtil.setReferrer(request, model);
        return "logout";
    }
}
