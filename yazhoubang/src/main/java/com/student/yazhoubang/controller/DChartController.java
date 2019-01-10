package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Msg;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.CureDao;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.PatientDao;
import com.student.yazhoubang.dao.WriteDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
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
    private PatientDao patientDao;

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

        System.out.println("---转诊信息---");
        //转诊信息
        List<Patient> patientWait = new LinkedList<>();
        List<String> pWait = new LinkedList<>();
        try{
            pWait = cureDao.selectPWByD(d_id);
            System.out.println(pWait.size());
            for(int i=0;i<pWait.size();i++){
                patientWait.add(patientDao.selectPatientById(pWait.get(i)));
            }
            model.addAttribute("patientWait",patientWait);
            System.out.println(patientWait.size());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "DChart";
    }

    @RequestMapping("/DChart/Refuse")
    @ResponseBody
    public Msg Refuse(HttpSession httpSession, Model model, @RequestParam(value = "p_id")String p_id){
        Msg msg = new Msg();
        String d_id = (String)httpSession.getAttribute("id");
        try{
            cureDao.refusePatient(p_id,d_id);
            msg.setMessage("SUCCESS");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("/DChart/Accept")
    @ResponseBody
    public Msg Accept(HttpSession httpSession, Model model, @RequestParam(value = "p_id")String p_id){
        Msg msg = new Msg();
        String d_id = (String)httpSession.getAttribute("id");
        System.out.println(p_id);
        System.out.println(d_id);
        try{
            cureDao.acceptPatient(p_id,d_id);
            msg.setMessage("SUCCESS");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}
