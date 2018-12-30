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
            Appointment appointment=appointmentDao.selectAppointemntById("111111111111111111");
            if(appointment!=null){
                if(appointment.getS_time().before(new Timestamp(System.currentTimeMillis()))){
                    System.out.println(appointmentDao.delectAppointment("111111111111111111"));
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
            System.out.println(appointmentDao.delectAppointment("111111111111111111"));
            map.put("status", "success");
            map.put("type",appointmentUtils.getType());
        }
        else if(appointmentUtils.getType().equals("update")){
            System.out.println(appointmentDao.updateAppointment(appointmentUtils.getS_time(),"111111111111111111"));
            map.put("status", "success");
            map.put("type",appointmentUtils.getType());
        }
        else if(appointmentUtils.getType().equals("insert")){
            String d_id=appointmentDao.selectD_id("111111111111111111");
            Appointment appointment=new Appointment();
            appointment.setP_id("111111111111111111");
            appointment.setD_id(d_id);
            appointment.setS_time(appointmentUtils.getS_time());
            appointment.setStatus(0);
            System.out.println(appointmentDao.insertAppointment(appointment));
            map.put("status", "success");
            map.put("type",appointmentUtils.getType());
        }
       return map;
    }
}
