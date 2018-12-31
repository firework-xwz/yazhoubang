package com.student.yazhoubang.dao;

import com.student.yazhoubang.damain.Doctor;
import com.student.yazhoubang.damain.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DoctorDao {

    @Insert("insert into doctor (d_id,name,birthday,sex,phone_num,password,role,status)values(#{d_id},#{name},#{birthday},#{sex},#{phone_num},#{password},#{role},#{status})")
    public Integer addDoctor(Doctor doctor);
    @Delete("delete from doctor where d_id=#{d_id}")
    public Integer deleteDoctorById(String d_id);
    @Select("select * from doctor where d_id=#{d_id}")
    public Doctor selectDoctorById(String d_id);
    @Select("select * from doctor")
    public List<Doctor> getAll();
    @Select("select * from doctor where status = 0")
    public List<Doctor> getAllForCheck();
}
