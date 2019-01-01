package com.student.yazhoubang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PatientHomeController {
    @RequestMapping("/PatientHome")
    public String PatientHome(){return "PatientHome";}
}
