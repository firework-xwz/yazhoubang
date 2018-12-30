package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.dao.DoctorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AMsgDoctorController {
    @Autowired
    private DoctorDao doctorDao;

    @RequestMapping("AMsgDoctor")
    public String showDoctorList(Model model){
        System.out.println("---doctorlist---");
        List<Doctor> doctorList = doctorDao.getAll();
        model.addAttribute("doctorList",doctorList);
        return "AMsgDoctor";
    }
}
