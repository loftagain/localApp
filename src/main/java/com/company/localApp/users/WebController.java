package com.company.localApp.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class WebController {

    @GetMapping("/")
    public String home() {
        return "front";
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("pageTitle", "welcome");
        model.addAttribute("styles", "welcome");
        model.addAttribute("pageContent", "welcome");
        return "welcome";
    }

    @GetMapping("/mentalhealth")
    public String showVideo() {
        return "mentalhealth";
    }
}