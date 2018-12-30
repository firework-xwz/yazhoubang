package com.student.yazhoubang.dao;

import com.student.yazhoubang.damain.Hospital;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HospitalDao {
    @Insert("Insert into hospital (h_id,position,phone_num,hospital_name)values(#{h_id},#{position},#{phone_num},#{hospital_name}")
    public Integer addHospital(Hospital hospital);
    @Delete("delete from hospital where h_id=#{h_id}")
    public Integer deleteHospital(String h_id);
    @Select("select * from hospital where h_id=#{h_id")
    public Hospital selectHospital(String h_id);
    @Select("select * from hospital")
    public List<Hospital> getAll();

}
