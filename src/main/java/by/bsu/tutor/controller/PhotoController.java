package by.bsu.tutor.controller;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.service.administration.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PhotoController {

    @Autowired private UserService userService;


    @PostMapping(value = "administration/users/{id}/photo/upload")
    public String uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException, LogicException {
        userService.addPhotoToUser(file, id);
        return "main";
    }

    @RequestMapping(value = "users/{id}/photo/download")
    @ResponseBody
    public byte[] downloadFile(@PathVariable Long id) throws IOException, LogicException {
        return userService.getUserPhoto(id);
    }

}
