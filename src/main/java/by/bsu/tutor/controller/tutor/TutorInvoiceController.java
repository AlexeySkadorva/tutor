package by.bsu.tutor.controller.tutor;

import by.bsu.tutor.service.tutor.TutorInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Alexey Skadorva
 */
@RequestMapping("/administration")
@Controller
public class TutorInvoiceController {

    private final TutorInvoiceService tutorInvoiceService;


    @Autowired
    public TutorInvoiceController(TutorInvoiceService tutorInvoiceService) {
        this.tutorInvoiceService = tutorInvoiceService;
    }

    @GetMapping(value = "/invoices")
    public String getInvoices(Model model) {
        model.addAttribute("invoices", tutorInvoiceService.getAll());
        return "administration/invoices/list";
    }

}
