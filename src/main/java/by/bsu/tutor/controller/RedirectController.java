package by.bsu.tutor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {

    @RequestMapping(value = "price")
    public String getPrice() {
        return "price";
    }

    @RequestMapping(value = "photo")
    public String getTutorsList() {
        return "photo";
    }


    @RequestMapping(value = "tutors/info")
    public String getTutorsInfo() {
        return "tutor/info";
    }

}
