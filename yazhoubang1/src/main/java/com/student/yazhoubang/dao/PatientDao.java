package com.student.yazhoubang.dao;

import com.student.yazhoubang.damain.Patient;
import org.apache.ibatis.annotations.*;

import javax.persistence.criteria.CriteriaBuilder;

@Mapper
public interface PatientDao {

    @Insert("insert into patient (p_id,name,birthday,sex,phone_num,password,role)values(#{p_id},#{name},#{birthday},#{sex},#{phone_num},#{password},#{role})")
    public Integer addPatient(Patient patient);
    @Delete("delete from patient where p_id=#{p_id}")
    public Integer deletePatientById(String p_id);
    @Select("select * from patient where p_id=#{p_id}")
    public Patient selectPatientById(String p_id);
}
