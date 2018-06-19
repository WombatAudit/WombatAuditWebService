package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Participate;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ParticipateMapper {

    @Select("SELECT * FROM participate")
    @Results({
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = Integer.class)
    })
    List<Participate> getAll();

    @Select("SELECT * FROM participate WHERE org_id = #{organization}")
    @Results({
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = Integer.class)
    })
    List<Participate> getSome(Integer organizationId);

    @Insert("INSERT INTO participate(org_id, user_id, role) VALUES (#{orgId}, #{userId}, #{role})")
    void insert(Participate participate);


    @Delete("DELETE FROM part WHERE org_id = #{organizationId} and user_id = #{userId}")
    void delete(Integer organizationId, Integer userId);

    @Select("SELECT * FROM participate WHERE user_id not in  (SELECT * FROM participate WHERE org_id = " +
            "#{organization})")
    @Results({
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = Integer.class)
    })
    List<Participate> getSomeReverse(Integer organizationId);

    @Select("select * from participate where user_id = #{userId} and org_id = #{orgId}")
    @Results({
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = Integer.class)
    })
    Participate findOne(@Param("userId") Integer userId, @Param("orgId") Integer orgId);

    @Select("select * from participate where org_id = #{orgId} and role = #{role}")
    @Results({
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "userId", column = "user_id", javaType = Integer.class),
            @Result(property = "role", column = "role", javaType = Integer.class)
    })
    List<Participate> findRoleOfOrg(@Param("orgId") Integer orgId, @Param("role") Integer role);
}
