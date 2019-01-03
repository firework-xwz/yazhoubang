package com.student.yazhoubang.controller;

import com.student.yazhoubang.dao.CureDao;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.WriteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DChartController {
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private CureDao cureDao;
    @Autowired
    private WriteDao writeDao;

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
