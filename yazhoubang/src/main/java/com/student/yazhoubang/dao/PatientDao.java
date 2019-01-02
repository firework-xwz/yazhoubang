package com.student.yazhoubang.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.student.yazhoubang.damain.Patient;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.List;

@Mapper
public interface PatientDao {

    @Insert("insert into patient (p_id,name,birthday,sex,phone_num,password,role)values(#{p_id},#{name},#{birthday},#{sex},#{phone_num},#{password},#{role})")
    public Integer addPatient(Patient patient);
    @Delete("delete from patient where p_id=#{p_id}")
    public Integer deletePatientById(String p_id);
    @Select("select * from patient where p_id=#{p_id}")
    public Patient selectPatientById(String p_id);
    @Select("select * from patient")
    public List<Patient> getAll();
    @Select("select p_id from patient")
    public String[] getAllId();
    @Select("select name,phone_num from yazhoubang.doctor natural join yazhoubang.cure where p_id=#{p_id}")
    public List<String>selectDoctorBypId(String p_id);
    @Select("select phone_num from yazhoubang.doctor natural join yazhoubang.cure where p_id=#{p_id}")
    public String selectDphone(String p_id);
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Select("select birthday from patient where p_id=#{p_id}")
    public Date selectBirthdaybyId(String p_id);
}
