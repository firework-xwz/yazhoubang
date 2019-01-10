package com.student.yazhoubang.dao;

import com.student.yazhoubang.damain.Chat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface ChatDao {

    @Select("select * from chat where p_id=#{p_id} and d_id=#{d_id} order by chat_time")
    public List<Chat> selectChatByP_idAndD_id(@Param("p_id")String p_id, @Param("d_id")String d_id);
    @Insert("insert into chat(p_id,d_id,chat_time,content,sender)values(#{p_id},#{d_id},#{chat_time},#{content},#{sender})")
    public Integer insertChatByPatient(@Param("p_id")String p_id, @Param("d_id")String d_id, @Param("chat_time") Date chat_time,@Param("content")String content,@Param("sender")String sender);
}
