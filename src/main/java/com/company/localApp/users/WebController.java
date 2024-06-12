package com.company.localApp.users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class WebController {

    @GetMapping
    public String welcome(Model model) {
        model.addAttribute("pageTitle", "welcome");
        model.addAttribute("styles", "welcome");
        model.addAttribute("pageContent", "welcome");
        return "layout";
    }
}
