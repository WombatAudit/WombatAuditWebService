package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Organization;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Component
public interface OrganizationMapper {

    @Select("select org_id from organization where co_id=#{coId}")
    List<Integer> selectOrgIdByCoId(int coId);

    @Select("select org_id,co_id,name,description,budget,create_time from organization " +
            "where org_id=#{orgId}")
    Organization selectOrgByOrgId(int orgId);

    @Select("SELECT * FROM organization")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "budget", column = "budget", javaType = BigDecimal.class),
            @Result(property = "createTime", column = "create_time", javaType = Timestamp.class)
    })
    List<Organization> getAll();

    @Select("SELECT * FROM organization WHERE org_id = #{organizationId}")
    @Results({
            @Result(property = "organizationId", column = "org_id", javaType = Integer.class),
            @Result(property = "companyId", column = "co_id", javaType = Integer.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "budget", column = "budget", javaType = BigDecimal.class),
            @Result(property = "createTime", column = "create_time", javaType = Timestamp.class)
    })
    Organization selectByOrganizationId(Integer organizationId);

    @Insert("INSERT INTO organization(org_id,co_id,name,description,budget,create_time) " +
            "VALUES(#{organizationId},#{companyId},#{name},#{description},#{budget},#{createTime})")
    void insert(Organization organization);

    @Update("UPDATE organization SET org_id=#{organizationId},name=#{name},description=#{description}," +
            "budget=#{budget},create_time=#{createTime} WHERE org_id=#{organizationId}")
    void update(Organization organization);

    @Delete("DELETE FROM organization WHERE org_id = #{organizationId}")
    void delete(Integer organizationId);
}
