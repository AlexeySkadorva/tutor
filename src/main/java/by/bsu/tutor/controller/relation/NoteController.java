package by.bsu.tutor.controller.relation;

import by.bsu.tutor.exceptions.LogicException;
import by.bsu.tutor.models.entity.note.TutorNote;
import by.bsu.tutor.service.relation.TutorNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

/**
 * @author Alexey Skadorva
 */
@Controller
public class NoteController extends ClientTutorRelationController {

    @Autowired private TutorNoteService tutorNoteService;


    @PostMapping(value = "/note")
    public String save(@ModelAttribute(value = "note") TutorNote note) throws IOException, LogicException {
        tutorNoteService.save(note);
        return "redirect:/account";
    }

    @GetMapping("/{id}/note")
    public String updatePage(@PathVariable Long id, Model model) throws IOException, LogicException {
        TutorNote tutorNote = new TutorNote();
        tutorNote.setClientTutorRelation(clientTutorRelationService.get(id));

        model.addAttribute("note", tutorNote);
        return "relation/note";
    }

}
