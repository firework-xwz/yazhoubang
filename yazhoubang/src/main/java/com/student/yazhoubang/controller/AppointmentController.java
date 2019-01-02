package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.dao.AppointmentDao;
import com.student.yazhoubang.damain.Appointment;
import com.student.yazhoubang.dao.PatientDao;
import com.student.yazhoubang.utils.AppointmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppointmentController {
    @Autowired
    PatientDao patientDao;
    @Autowired
    private AppointmentDao appointmentDao;
    @RequestMapping("/PCalendar")
    public String appointment(Model model, HttpSession httpSession){
        String p_id=(String)httpSession.getAttribute("id");
        String[] doctorinformation=patientDao.selectDoctorBypId(p_id);
        Patient patient =patientDao.selectPatientById(p_id);
        model.addAttribute("patientName",patient.getName());
        if(patient.getSex()==0){
            model.addAttribute("patientSex","男");
        }
        else
            model.addAttribute("patientSex","女");
        model.addAttribute("birthday", patientDao.selectBirthdaybyId(p_id));
        if(doctorinformation!=null&&doctorinformation.length>0) {
            model.addAttribute("doctorName", doctorinformation[0]);
            model.addAttribute("doctorPhone", doctorinformation[1]);
        }
        return "PCalendar";
    }
    @PostMapping("/PCalendar")
    @ResponseBody
    public Map appointmentData(@RequestBody AppointmentUtils appointmentUtils, HttpSession httpSession){
        String p_id=(String)httpSession.getAttribute("id");
        List list=new ArrayList();
        Map map=new HashMap();
        if (appointmentUtils.getType().equals("select")){
            Appointment appointment=appointmentDao.selectAppointemntById(p_id);
            if(appointment!=null){
                if(appointment.getS_time().before(new Timestamp(System.currentTimeMillis()))){
                    System.out.println(appointmentDao.delectAppointment(p_id));
                    map.put("status","empty");
                    map.put("type",appointmentUtils.getType());
                }
                else {
                    map.put("start", appointment.getS_time());
                    map.put("status", "success");
                    map.put("appointmentStatus",appointment.getStatus());
                    map.put("type",appointmentUtils.getType());
                }
            }
            else
            {
                map.put("status","empty");
                map.put("type",appointmentUtils.getType());
            }
        }
        else if (appointmentUtils.getType().equals("delete")){
            System.out.println(appointmentDao.delectAppointment(p_id));
            map.put("status", "success");
            map.put("type",appointmentUtils.getType());
        }
        else if(appointmentUtils.getType().equals("update")){
            System.out.println(appointmentDao.updateAppointment(appointmentUtils.getS_time(),p_id));
            map.put("status", "success");
            map.put("type",appointmentUtils.getType());
        }
        else if(appointmentUtils.getType().equals("insert")){
            String d_id=appointmentDao.selectD_id(p_id);
            if(d_id!=null) {
                Appointment appointment = new Appointment();
                appointment.setP_id(p_id);
                appointment.setD_id(d_id);
                appointment.setS_time(appointmentUtils.getS_time());
                appointment.setStatus(0);
                System.out.println(appointmentDao.insertAppointment(appointment));
                map.put("status", "success");
                map.put("type", appointmentUtils.getType());
            }
            else
                map.put("status","noD_id");
        }
       return map;
    }
}
