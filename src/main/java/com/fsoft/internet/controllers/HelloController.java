package com.fsoft.internet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/hello123")
    public String index(final Model model) {
        model.addAttribute("message", "hello");
        return "index";
    }
}