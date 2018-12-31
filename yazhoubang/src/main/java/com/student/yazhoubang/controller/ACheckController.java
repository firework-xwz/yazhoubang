package com.student.yazhoubang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ACheckController {
    @RequestMapping("/ACheck")
    public String ACheck(Model model){
        return "ACheck";
    }
}
