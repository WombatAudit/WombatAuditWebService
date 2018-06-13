package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Participate;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParticipateMapper {

    @Select("SELECT * FROM participate")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = Integer.class)
    })
    List<Participate> getAll();

    @Select("SELECT * FROM company WHERE org_id = #{organizationId} and user_id = #{userId}")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = Integer.class)
    })
    Participate selectByOrganizationIdAndUserId(Integer organizationId, Integer userId);

    @Select("SELECT * FROM participate WHERE org_id = #{organization}")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = Integer.class)
    })
    List<Participate> getSome(Integer organizationId);

    @Insert("INSERT INTO participate(org_id,user_id,role) VALUES(#{organizationId}," +
            "#{userId},#{role})")
    void insert(Participate participate);


    @Delete("DELETE FROM part WHERE org_id = #{organizationId} and user_id = #{userId}")
    void delete(Integer organizationId, Integer userId);

    @Select("SELECT * FROM participate WHERE user_id not in  (SELECT * FROM participate WHERE org_id = " +
            "#{organization})")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = Integer.class)
    })
    List<Participate> getSomeReverse(Integer organizationId);
}
