package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AMsgPatientController {
    @Autowired
    private PatientDao patientDao;

    @RequestMapping("/AMsgPatient")
    public String showPatientList(Model model) {
        System.out.println("---patientlist---");
        List<Patient> patientList = patientDao.getAll();
        model.addAttribute("patientList", patientList);
        return "AMsgPatient";
    }

}
