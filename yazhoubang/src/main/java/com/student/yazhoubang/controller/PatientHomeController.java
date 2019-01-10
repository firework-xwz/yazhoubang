package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PatientHomeController {
    @Autowired
    PatientDao patientDao;

    @RequestMapping("/PatientHome")
    public String PatientHome(Model model, HttpSession httpSession){
        String p_id=(String)httpSession.getAttribute("id");
        List<String> doctorinformation=patientDao.selectDoctorBypId(p_id);
        String Dphone=patientDao.selectDphone(p_id);
        Patient patient =patientDao.selectPatientById(p_id);
        model.addAttribute("patientName",patient.getName());
        if(patient.getSex()==0){
            model.addAttribute("patientSex","男");
        }
        else
            model.addAttribute("patientSex","女");
        model.addAttribute("birthday", patientDao.selectBirthdaybyId(p_id));
        if(doctorinformation!=null&&doctorinformation.size()>0) {
            model.addAttribute("doctorName", doctorinformation.get(0));
            model.addAttribute("doctorPhone", Dphone);
        }
        return "PatientHome";}
}
