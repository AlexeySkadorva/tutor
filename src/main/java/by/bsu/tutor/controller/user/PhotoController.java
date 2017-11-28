package by.bsu.tutor.controller.user;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/users/{id}/photo/")
public class PhotoController {

    @Autowired private UserService userService;


    @PostMapping(value = "/upload")
    public String uploadFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException, LogicException {
        userService.addPhotoToUser(file, id);
        return "main";
    }

    @GetMapping(value = "/download")
    @ResponseBody
    public byte[] downloadFile(@PathVariable Long id) throws IOException, LogicException {
        return userService.getUserPhoto(id);
    }

}
