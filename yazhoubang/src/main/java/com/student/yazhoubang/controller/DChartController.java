package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.CureDao;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.PatientDao;
import com.student.yazhoubang.dao.WriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DChartController {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private CureDao cureDao;
    @Autowired
    private WriteDao writeDao;
    @Autowired
    PatientDao patientDao;

    @RequestMapping("DChart")
    public String DChart(Model model, HttpSession httpSession){
        System.out.println("---DIndex---");
        String d_id = (String)httpSession.getAttribute("id");
        System.out.println(d_id);

        try{
            //治疗的患者数
            int num_patient = cureDao.selectPByD(d_id).size();
            System.out.println(num_patient);
            model.addAttribute("num_patient",num_patient);
            String dname = doctorDao.selectDoctorById(d_id).getName();
            model.addAttribute("dname",dname);
            int status = doctorDao.selectDoctorById(d_id).getStatus();
            model.addAttribute("status",status);
            List<Patient>patientList=new ArrayList<>();
            List<String>p_ids=cureDao.selectPByD(d_id);
            for(int i=0;i<p_ids.size();i++){
                Patient patient=patientDao.selectPatientById(p_ids.get(i));
                if(patient!=null){
                    patientList.add(patient);
                }
            }
            model.addAttribute("patientList",patientList);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try{
            //已诊断病例数
            int num_chart = writeDao.selectCByD(d_id).size();
            System.out.println(num_chart);
            model.addAttribute("num_chart",num_chart);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return "DChart";
    }
}
