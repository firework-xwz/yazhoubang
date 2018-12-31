package com.student.yazhoubang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AIndex {

    @RequestMapping("AIndex")
    public String AIndex(Model model){
        return "AIndex";
    }
}
