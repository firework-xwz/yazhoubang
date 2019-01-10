package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Hospital;
import com.student.yazhoubang.damain.Msg;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    WorkDao workDao;
    @Autowired
    CureDao cureDao;

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

    @RequestMapping("/PatientHome/DoctorList/{h_id}")
    public String DoctorList(@PathVariable(value = "h_id")String h_id,Model model, HttpSession httpSession){
        System.out.println("---doctorlist---");

        List<String> d_idList = new ArrayList<>();
        List<Doctor> doctorList = new ArrayList<>();

        try{
            d_idList = workDao.selectDByH(h_id);
            System.out.println(d_idList.size());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        for(int i=0;i<d_idList.size();i++){
            try{
                String td_id = d_idList.get(i);
                System.out.println(td_id);
                Doctor doctor = doctorDao.selectDoctorById(td_id);
                System.out.println(doctor.getName());
                doctorList.add(doctor);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(doctorList.size());
        model.addAttribute("doctorList",doctorList);
        return "PHList";
    }

    @RequestMapping("/PatientHome/ConfirmApplyDoctor")
    @ResponseBody
    public Msg ConfirmApplyDoctor(HttpSession httpSession,@RequestParam(value = "d_id")String d_id){
        String p_id = (String)httpSession.getAttribute("id");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s_time = df.format(new Date());
        Msg msg = new Msg();
        try{
            //如果曾经是该患者的医生
            if(cureDao.selectStatus(p_id,d_id)==null){
                cureDao.addCure(d_id,p_id,s_time,null,0);
            }
            else{
                cureDao.resetPatient(p_id,d_id);
            }
            msg.setMessage("SUCCESS!!!");
        }
        catch(Exception e){
            e.printStackTrace();
            msg.setMessage("FAIL!!!");
        }
        return msg;
    }
}
