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

    //获取所有有权限的医生
    @Select("select * from doctor where status = 1")
    public List<Doctor> getAllTrue();
    //获取所有无权限的医生
    @Select("select * from doctor where status = 0")
    public List<Doctor> getAllFalse();
    //移除医生权限
    @Update("update doctor set status=2 where d_id=#{d_id}")
    public Integer setDoctorFalse(String d_id);
    //授予医生权限
    @Update("update doctor set status=1 where d_id=#{d_id}")
    public Integer setDoctorTrue(String d_id);
    //拒绝医生申请权限
    @Update("update doctor set status=3 where d_id=#{d_id}")
    public Integer refuseDoctor(String d_id);
}
