package com.student.yazhoubang.damain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="hospital")
@Entity
public class Hospital {
    @Id
    private String h_id;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String phone_num;
    @Column(nullable = false)
    private String hospital_name;

    public String getH_id() {
        return h_id;
    }

    public String getPosition() {
        return position;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setH_id(String h_id) {
        this.h_id = h_id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

}
