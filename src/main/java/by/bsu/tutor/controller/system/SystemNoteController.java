package by.bsu.tutor.controller.system;

import by.bsu.tutor.models.entity.note.SystemNote;
import by.bsu.tutor.service.administration.SystemNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping(value = "/notes")
public class SystemNoteController {

    @Autowired private SystemNoteService noteService;


    @RequestMapping
    public String notePage(Model model) throws IOException {
        model.addAttribute("notes", noteService.getAll());
        model.addAttribute("note", new SystemNote());
        return "note/notes";
    }

    @PostMapping
    public String saveNote(@ModelAttribute(value = "note") SystemNote note) throws IOException {
        noteService.saveNoteForClient(note);
        return "redirect:/notes";
    }

}
