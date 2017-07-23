package by.bsu.tutor.controller;

import by.bsu.tutor.models.entity.note.SystemNote;
import by.bsu.tutor.service.administration.SystemNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping(value = "/notes")
public class NotesController {

    @Autowired
    private SystemNoteService noteService;

    @RequestMapping
    public String notePage(Model model) throws IOException {
        model.addAttribute("notes", noteService.getAll());
        model.addAttribute("note", new SystemNote());
        return "notes";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveNote(@ModelAttribute(value = "note") SystemNote note) throws IOException {
        noteService.saveNoteForClient(note);
        return "redirect:/notes";
    }

}
