package by.bsu.tutor.util;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtil {
    public static void setReferrer(HttpServletRequest request, Model model) {
        String referrer = request.getHeader("referer");
        model.addAttribute("referrer", referrer == null ? "/" : referrer);
    }
}
