package com.student.yazhoubang.damain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Table(name ="appointment" )
@Entity
public class Appointment implements Serializable {
    @Id
    private  String d_id;
    @Column(nullable=false)
    private String p_id;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp s_time;


    public void setP_id(String p_id) {
        this.p_id = p_id;
    }
    public String getP_id() {
        return p_id;
    }

    public Timestamp getS_time() {
        return s_time;
    }

    public String getD_id() {
        return d_id;
    }


    public void setD_id(String d_id) {
        this.d_id = d_id;
    }

    public void setS_time(Timestamp s_time) {
        this.s_time = s_time;
    }

}
