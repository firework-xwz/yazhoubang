package com.student.yazhoubang.damain;

import java.util.Date;

public class Chat {

    private String p_id;
    private String d_id;
    private Date chat_time;
    private String content;
    private String sender;

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getD_id() {
        return d_id;
    }

    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public Date getChat_time() {
        return chat_time;
    }

    public void setChat_time(Date chat_time) {
        this.chat_time = chat_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
