package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Assignment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface AssignmentMapper {

    @Select("select * from assignment where item_id = #{itemId}")
    @Results({
            @Result(property = "assignerId", column = "assigner_id", javaType = Integer.class),
            @Result(property = "assigneeId", column = "assignee_id", javaType = Integer.class),
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
    })
    List<Assignment> findListByItemId(Integer itemId);

    @Insert("insert into assignment(assigner_id, assignee_id, item_id, status) values (#{assignerId}, #{assigneeId}, #{itemId}, #{status})")
    void create(Assignment assignment);

    @Select("select * from assignment where assigner_id = #{assignerId}")
    @Results({
            @Result(property = "assignerId", column = "assigner_id", javaType = Integer.class),
            @Result(property = "assigneeId", column = "assignee_id", javaType = Integer.class),
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
    })
    List<Assignment> findListByAssignerId(Integer assignerId);

    @Select("select * from assignment where assigner_id = #{assignerId} and status = #{status}")
    @Results({
            @Result(property = "assignerId", column = "assigner_id", javaType = Integer.class),
            @Result(property = "assigneeId", column = "assignee_id", javaType = Integer.class),
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
    })
    List<Assignment> findListByAssignerIdAndStatus(@Param("assignerId") Integer assignerId, @Param("status") Integer status);

    @Select("select * from assignment where assignee_id = #{assigneeId} and status = #{status}")
    @Results({
            @Result(property = "assignerId", column = "assigner_id", javaType = Integer.class),
            @Result(property = "assigneeId", column = "assignee_id", javaType = Integer.class),
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
    })
    List<Assignment> findListByAssigneeIdAndStatus(@Param("assigneeId") Integer assigneeId, @Param("status") Integer status);

    @Update("update assignment set status = #{status} where item_id = #{itemId} and assignee_id = #{assigneeId}")
    void updateStatus(@Param("itemId") Integer itemId, @Param("assigneeId") Integer assigneeId, @Param("status") Integer status);
}
