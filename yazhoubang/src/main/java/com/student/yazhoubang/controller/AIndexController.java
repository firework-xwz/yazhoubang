package com.student.yazhoubang.controller;

import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.HospitalDao;
import com.student.yazhoubang.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AIndexController {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private HospitalDao hospitalDao;

    @RequestMapping("AIndex")
    public String AIndex(Model model){
        return "AIndex";
    }
}
