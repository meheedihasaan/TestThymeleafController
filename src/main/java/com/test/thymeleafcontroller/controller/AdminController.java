package com.test.thymeleafcontroller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String getAdminView(Model model) {
        model.addAttribute("msg", "Admin! Top secret.");
        return "admin";
    }

}
