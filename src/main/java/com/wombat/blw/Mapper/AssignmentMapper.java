package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Assignment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface AssignmentMapper {

    @Select("select * from assignment where item_id = #{itemId}")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
    })
    List<Assignment> getListByItemId(Integer itemId);

    @Select("select user_id,item_id,status,max(start_date) as max_date " +
            "from assignment " +
            "where user_id=#{userId}")
    @Results({
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "startTime", column = "max_date", javaType = Date.class),
    })
    List<Assignment> getListByUserId(Integer userId);

    @Insert("insert into assignment(user_id, item_id, status) values (#{userId}, #{itemId}, #{status})")
    void create(Assignment assignment);
}
