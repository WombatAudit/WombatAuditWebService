package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Organization;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Component
public interface OrganizationMapper {

    @Select("select org_id from organization where co_id=#{coId}")
    List<Integer> selectOrgIdByCoId(int coId);

    @Select("select org_id,co_id,name,description,budget,create_time from organization where org_id = #{orgId}")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "budget", column = "budget", javaType = BigDecimal.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    Organization selectOrgByOrgId(int orgId);

    @Select("SELECT * FROM organization")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "budget", column = "budget", javaType = BigDecimal.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    List<Organization> getAll();

    @Select("SELECT * FROM organization WHERE org_id = #{organizationId}")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "budget", column = "budget", javaType = BigDecimal.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    Organization selectByOrganizationId(Integer organizationId);

    @Insert("insert into organization(co_id,name,description,budget) values (#{companyId}, #{name}, #{description}, " +
            "#{budget})")
    @Options(useGeneratedKeys = true, keyColumn = "org_id", keyProperty = "organizationId")
    void create(Organization organization);

    @Update("UPDATE organization SET org_id=#{organizationId},name=#{name},description=#{description}," +
            "budget=#{budget},create_time=#{createTime} WHERE org_id=#{organizationId}")
    void update(Organization organization);

    @Delete("DELETE FROM organization WHERE org_id = #{organizationId}")
    void delete(Integer organizationId);

    @Select("select * from organization where co_id = #{coId}")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "budget", column = "budget", javaType = BigDecimal.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    List<Organization> findAllOrgsByCoId(Integer coId);

    @Select("select name from organization where org_id = #{orgId}")
    @ResultType(String.class)
    String findOrgNameById(Integer orgId);

    @Select("select * from organization natural join participate where user_id = #{userId}")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "budget", column = "budget", javaType = BigDecimal.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    List<Organization> findOrgsByUserId(Integer userId);

    @Select("select * from organization natural join participate where user_id = #{userId} and role = #{role}")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "budget", column = "budget", javaType = BigDecimal.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    List<Organization> findJoinedOrgWithRole(@Param("userId") Integer userId, @Param("role") Integer role);
}
