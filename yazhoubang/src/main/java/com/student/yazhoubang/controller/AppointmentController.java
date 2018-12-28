package com.student.yazhoubang.controller;

import com.student.yazhoubang.dao.AppointmentDao;
import com.student.yazhoubang.damain.Appointment;
import com.student.yazhoubang.utils.AppointmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentDao appointmentDao;
    @RequestMapping("/PCalendar")
    public String appointment(){
        return "PCalendar";
    }
    @PostMapping("/PCalendar")
    @ResponseBody
    public Map appointmentData(@RequestBody AppointmentUtils appointmentUtils){
        List list=new ArrayList();
        Map map=new HashMap();
        if (appointmentUtils.getType().equals("select")){
            Appointment appointment=appointmentDao.selectAppointemntById("1600");
            if(appointment!=null){
                if(appointment.getS_time().before(new Timestamp(System.currentTimeMillis()))){
                    System.out.println(appointmentDao.delectAppointment("1600"));
                    map.put("status","empty");
                    map.put("type",appointmentUtils.getType());
                }
                else {
                    map.put("start", appointment.getS_time());
                    map.put("status", "success");
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
            System.out.println(appointmentDao.delectAppointment("1600"));
            map.put("status", "success");
            map.put("type",appointmentUtils.getType());
        }
        else if(appointmentUtils.getType().equals("update")){
            System.out.println(appointmentDao.updateAppointment(appointmentUtils.getS_time(),"1600"));
            map.put("status", "success");
            map.put("type",appointmentUtils.getType());
        }
        else if(appointmentUtils.getType().equals("insert")){
            String d_id=appointmentDao.selectD_id("1600");
            Appointment appointment=new Appointment();
            appointment.setP_id("1600");
            appointment.setD_id(d_id);
            appointment.setS_time(appointmentUtils.getS_time());
            System.out.println(appointmentDao.insertAppointment(appointment));
            map.put("status", "success");
            map.put("type",appointmentUtils.getType());
        }
       return map;
    }
}
