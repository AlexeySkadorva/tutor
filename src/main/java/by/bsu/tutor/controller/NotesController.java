package by.bsu.tutor.controller;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.note.SystemNote;
import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.repositories.ClientTutorRelationRepository;
import by.bsu.tutor.service.administration.SystemNoteService;
import by.bsu.tutor.service.client.ClientTutorRelationService;
import by.bsu.tutor.service.tutor.TutorNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationService;
import java.io.IOException;

@Controller
@RequestMapping(value = "/notes")
public class NotesController {

    @Autowired private SystemNoteService noteService;
    @Autowired private TutorNoteService tutorNoteService;
    @Autowired private ClientTutorRelationService clientTutorRelationService;

    @RequestMapping
    public String notePage(Model model) throws IOException {
        model.addAttribute("notes", noteService.getAll());
        model.addAttribute("note", new SystemNote());
        return "notes";
    }

    @GetMapping("/relations/{id}")
    public String p(@PathVariable Long id, Model model) throws IOException, LogicException {
        TutorNote tutorNote = new TutorNote();
        tutorNote.setClientTutorRelation(clientTutorRelationService.get(id));

        model.addAttribute("note", tutorNote);
        return "note";
    }

    @RequestMapping(value = "/relations/{id}", method = RequestMethod.POST)
    public String save(@PathVariable Long id, @ModelAttribute(value = "note") TutorNote note) throws IOException, LogicException {
        tutorNoteService.save(note);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveNote(@ModelAttribute(value = "note") SystemNote note) throws IOException {
        noteService.saveNoteForClient(note);
        return "redirect:/notes";
    }

}
