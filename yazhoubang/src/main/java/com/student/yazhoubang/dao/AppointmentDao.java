package com.student.yazhoubang.dao;

import com.student.yazhoubang.damain.Appointment;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;

@Mapper
public interface AppointmentDao {
    @Select("select * from appointment where p_id=#{p_id}")
    public Appointment selectAppointemntById(String p_id);
    @Delete("DELETE FROM `yazhoubang`.`appointment` WHERE (`p_id` = #{p_id});")
    public Integer delectAppointment(String p_id);
    @Insert("INSERT INTO `yazhoubang`.`appointment` (`d_id`, `p_id`, `s_time`,`status`) VALUES (#{d_id},#{p_id},#{s_time},#{status})")
    public Integer insertAppointment(Appointment appointment);
    @Update("UPDATE `yazhoubang`.`appointment` SET `s_time` = #{s_time} WHERE (`p_id` = #{p_id})")
    public Integer updateAppointment(Timestamp s_time, String p_id);
    @Select("select d_id from yazhoubang.cure where p_id=#{p_id}")
    public String selectD_id(String p_id);
}
