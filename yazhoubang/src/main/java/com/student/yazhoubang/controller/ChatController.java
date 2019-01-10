package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Chat;
import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.dao.ChatDao;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ChatController {

    @Autowired
    ChatDao chatDao;
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    PatientDao patientDao;

    @RequestMapping("/patient/chat/{id}")
    public String chat(@PathVariable("id") String id, HttpSession httpSession, Model model){
        String d_id=id;
        String p_id=(String)httpSession.getAttribute("id");
        Doctor doctor=doctorDao.selectDoctorById(d_id);
        List<Chat>chats =chatDao.selectChatByP_idAndD_id(p_id,d_id);
        List<Map> ChatInfo = new ArrayList<>();
        if(chats.size()>0) {
            for (int i = 0; i < chats.size(); i++) {
                Map map = new HashMap();
                map.put("chat", chats.get(i));
                if (chats.get(i).getSender().equals(p_id)) {
                    map.put("status",0);
                    map.put("sender",patientDao.selectPatientById(p_id));
                }
                else if(chats.get(i).getSender().equals(d_id)){
                    map.put("status",1);
                    map.put("sender",doctorDao.selectDoctorById(d_id));
                }    //0是自己，1是别人
                ChatInfo.add(map);
            }
        }
        System.out.println(ChatInfo.size());
        model.addAttribute("ChatInfo",ChatInfo);
        model.addAttribute("name",doctor.getName());
        model.addAttribute("d_id",doctor.getD_id());
        return "ChatBox";
    }

    @PostMapping("/patient/addChatContent")
    @ResponseBody
    public Doctor addChatContentInPatient(@RequestParam(value = "d_id")String d_id, @RequestParam(value = "content")String content, @RequestParam(value = "chat_time")Date chat_time,HttpSession httpSession){
        String p_id=(String)httpSession.getAttribute("id");
        Doctor doctor=doctorDao.selectDoctorById(d_id);
        try{
            System.out.println(chatDao.insertChatByPatient(p_id,d_id,chat_time,content,p_id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctor;
    }
}
