package com.student.yazhoubang.controller;

import com.student.yazhoubang.dao.HospitalDao;
import com.student.yazhoubang.damain.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AMsgHospitalController {
    @Autowired
    private HospitalDao hospitalDao;

    @RequestMapping("/AMsgHospital")
    public String showHospitalList(Model model) {
        System.out.println("---Hospitallist---");
        List<Hospital> hospitalList = hospitalDao.getAll();
        model.addAttribute("hospitalList", hospitalList);
        return "AMsgHospital";
    }
}
