package com.student.yazhoubang.controller;

import com.student.yazhoubang.damain.Chat;
import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Patient;
import com.student.yazhoubang.damain.UserList;
import com.student.yazhoubang.dao.ChatDao;
import com.student.yazhoubang.dao.DoctorDao;
import com.student.yazhoubang.dao.PatientDao;
import com.student.yazhoubang.dao.UserListDao;
import com.student.yazhoubang.utils.ChatSender;
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
    @Autowired
    UserListDao userListDao;

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
                }    //0是病人，1是医生
                ChatInfo.add(map);
            }
        }
        System.out.println(ChatInfo.size());
        model.addAttribute("ChatInfo",ChatInfo);
        model.addAttribute("name",doctor.getName());
        model.addAttribute("id",doctor.getD_id());
        return "ChatBox";
    }

    @RequestMapping("/doctor/chat/{id}")
    public String chatInDoctor(@PathVariable("id")String id,HttpSession httpSession,Model model){
        String p_id=id;
        String d_id=(String)httpSession.getAttribute("id");
        Patient patient=patientDao.selectPatientById(p_id);
        List<Chat>chats=chatDao.selectChatByP_idAndD_id(p_id,d_id);
        List<Map>chatInfo=new ArrayList<>();
        if(chats.size()>0){
            for(int i=0;i<chats.size();i++){
                Map map=new HashMap();
                map.put("chat",chats.get(i));
                if(chats.get(i).getSender().equals(d_id)){
                    map.put("status",1);
                    map.put("sender",doctorDao.selectDoctorById(d_id));
                }
                else if(chats.get(i).getSender().equals(p_id)){
                    map.put("status",0);
                    map.put("sender",patientDao.selectPatientById(p_id));
                }
                chatInfo.add(map);
            }
        }
        System.out.println(chatInfo.size());
        model.addAttribute("ChatInfo",chatInfo);
        model.addAttribute("name",patient.getName());
        model.addAttribute("id",patient.getP_id());
        return "ChatBox";
    }


    @PostMapping("/addChatContent")
    @ResponseBody
    public ChatSender addChatContentInPatient(@RequestParam(value = "id")String id, @RequestParam(value = "content")String content, @RequestParam(value = "chat_time")Date chat_time, HttpSession httpSession){
        if(userListDao.selectUserById(id).getUser_role()==1){
            System.out.println("patient input");
            String p_id=(String)httpSession.getAttribute("id");
            String d_id=id;
            Patient patient=patientDao.selectPatientById(p_id);
            ChatSender chatSender=new ChatSender();
            chatSender.setName(patient.getName());
            chatSender.setRole(patient.getRole());
            try{
                System.out.println(chatDao.insertChatByPatient(p_id,d_id,chat_time,content,p_id));
            }catch (Exception e){
                e.printStackTrace();
            }
            return chatSender;
        }
        else{
            System.out.println("doctor input");
            String p_id=id;
            String d_id=(String)httpSession.getAttribute("id");
            Doctor doctor=doctorDao.selectDoctorById(d_id);
            ChatSender chatSender=new ChatSender();
            chatSender.setName(doctor.getName());
            chatSender.setRole(doctor.getRole());
            try {
                System.out.println(chatDao.insertChatByPatient(p_id,d_id,chat_time,content,d_id));
            }catch (Exception e){
                e.printStackTrace();
            }
            return chatSender;
        }
    }
}
