package com.student.yazhoubang.damain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Table(name = "chart")
@Entity
public class PatientChart {
    @Id
    private int c_id;
    @Column(nullable = false)
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;
    @Column(nullable = false)
    private String mobility;
    @Column(nullable = false)
    private String implant;
    @Column(nullable = false)
    private String FI;
    @Column(nullable = false)
    private String GI;
    @Column(nullable = false)
    private String BI_B;
    @Column(nullable = false)
    private String BI_L;
    @Column(nullable = false)
    private String PD_B;
    @Column(nullable = false)
    private String PD_L;
    @Column(nullable = false)
    private String CEJ_B;
    @Column(nullable = false)
    private String CEJ_L;
    @Column(nullable = false)
    private String PI_B;
    @Column(nullable = false)
    private String PI_L;

    public Date getTime() {
        return time;
    }

    public int getC_id() {
        return c_id;
    }

    public String getBI_B() {
        return BI_B;
    }

    public String getFI() {
        return FI;
    }

    public String getMobility() {
        return mobility;
    }

    public String getImplant() {
        return implant;
    }

    public String getGI() {
        return GI;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    public void setBI_B(String BI_B) {
        this.BI_B = BI_B;
    }

    public void setFI(String FI) {
        this.FI = FI;
    }

    public void setGI(String GI) {
        this.GI = GI;
    }

    public void setImplant(String implant) {
        this.implant = implant;
    }

    public String getBI_L() {
        return BI_L;
    }

    public String getCEJ_B() {
        return CEJ_B;
    }

    public void setBI_L(String BI_L) {
        this.BI_L = BI_L;
    }

    public String getCEJ_L() {
        return CEJ_L;
    }

    public String getPD_B() {
        return PD_B;
    }

    public String getPD_L() {
        return PD_L;
    }

    public String getPI_B() {
        return PI_B;
    }

    public String getPI_L() {
        return PI_L;
    }

    public void setCEJ_B(String CEJ_B) {
        this.CEJ_B = CEJ_B;
    }

    public void setPD_B(String PD_B) {
        this.PD_B = PD_B;
    }

    public void setCEJ_L(String CEJ_L) {
        this.CEJ_L = CEJ_L;
    }

    public void setPD_L(String PD_L) {
        this.PD_L = PD_L;
    }

    public void setPI_B(String PI_B) {
        this.PI_B = PI_B;
    }

    public void setPI_L(String PI_L) {
        this.PI_L = PI_L;
    }
}
