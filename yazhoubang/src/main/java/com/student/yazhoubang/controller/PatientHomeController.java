package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Hospital;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.HospitalDao;
import com.student.yazhoubang.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

@Controller
public class PatientHomeController {
    @Autowired
    PatientDao patientDao;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    HospitalDao hospitalDao;

    @RequestMapping("/PatientHome")
    public String PatientHome(Model model, HttpSession httpSession){
        String p_id=(String)httpSession.getAttribute("id");
        List<String> d_ids=patientDao.selectD_idByP_id(p_id);
        List<Doctor>doctors=new ArrayList<Doctor>();
        for(String d_id:d_ids){
            Doctor doctor=doctorDao.selectDoctorById(d_id);
            doctors.add(doctor);
        }
        String Dphone=patientDao.selectDphone(p_id);
        Patient patient =patientDao.selectPatientById(p_id);
//        System.out.println(patient.getName());
        model.addAttribute("patientName",patient.getName());
        if(patient.getSex()==0){
            model.addAttribute("patientSex","男");
        }
        else
            model.addAttribute("patientSex","女");
        model.addAttribute("birthday", patientDao.selectBirthdaybyId(p_id));
        model.addAttribute("doctors",doctors);
//        if(doctorinformation!=null&&doctorinformation.size()>0) {
//            model.addAttribute("doctorName", doctorinformation.get(0));
//            model.addAttribute("doctorPhone", Dphone);
//        }

        //显示医院列表
        try{
            List<Hospital> hospitalList = hospitalDao.getAll();
            model.addAttribute("hospitalList", hospitalList);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "PatientHome";
    }
}
