package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Item;
import com.wombat.blw.DO.Project;
import com.wombat.blw.DO.Receipt;
import com.wombat.blw.DO.Version;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public interface ProjectMapper {

    @Insert("insert into project(org_id, name, description, status, start_time, end_time) values (#{orgId}, #{name}, " +
            "#{description}, #{status}, #{startTime}, #{endTime})")
    @Options(useGeneratedKeys = true, keyProperty = "prjId", keyColumn = "prj_id")
    void create(Project project);

    @Select("select name from project where prj_id = #{prjId}")
    @ResultType(String.class)
    String findPrjName(Integer prjId);

    @Select("select * from project where org_id = #{orgId}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "prj_id", javaType = String.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "versionId", column = "version_id", javaType = Integer.class)
    })
    List<Project> findProjectsByOrg(Integer orgId);

    @Select("select * from project where org_id = #{orgId} and status != #{status}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "prj_id", javaType = String.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "versionId", column = "version_id", javaType = Integer.class)
    })
    List<Project> findByOrgIdAndNotInStatus(@Param("orgId") Integer orgId, @Param("status") Integer status);

    @Select("select prj_id, project.org_id, project.name, project.description, start_time, status, end_time, version_id from project join organization " +
            "on project.org_id = organization.org_id where organization.co_id = #{coId} and project.status = #{status}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "prj_id", javaType = String.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "versionId", column = "version_id", javaType = Integer.class)
    })
    List<Project> findByCoIdAndType(@Param("coId") Integer coId, @Param("status") Integer status);

    @Select("select * from project where org_id = #{orgId} and status = #{status}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "versionId", column = "version_id", javaType = Integer.class)
    })
    List<Project> findByOrgIdAndStatus(@Param("orgId") Integer orgId, @Param("status") Integer status);

    @Select("select * from project where prj_id = #{prjId}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "versionId", column = "version_id", javaType = Integer.class)
    })
    Project findById(Integer prjId);

    @Select("select * from item natural join detail where version_id = #{versionId}")
    @Results({
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "type", column = "type", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "quantity", column = "quantity", javaType = Integer.class),
            @Result(property = "amount", column = "amount", javaType = BigDecimal.class),
            @Result(property = "rcptId", column = "rcpt_id", javaType = Integer.class)
    })
    List<Item> findItems(Integer versionId);

    @Select("select * from item where item_id = #{itemId}")
    @Results({
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "type", column = "type", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "quantity", column = "quantity", javaType = Integer.class),
            @Result(property = "amount", column = "amount", javaType = BigDecimal.class),
            @Result(property = "rcptId", column = "rcpt_id", javaType = Integer.class)
    })
    Item findItem(Integer itemId);

    @Update("update project set status = #{status} where prj_id = #{prjId}")
    void updateStatus(@Param("prjId") Integer prjId, @Param("status") Integer status);

    @Select("select * from version where version_id = #{versionId}")
    @Results({
            @Result(property = "versionId", column = "version_id", javaType = Integer.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class),
            @Result(property = "tag", column = "tag", javaType = String.class)
    })
    Version findVersion(Integer versionId);

    @Update("update project set versionId = #{versionId} where prj_id = #{prjId}")
    void updateProjectVersion(@Param("prjId") Integer prjId, @Param("versionId") Integer versionId);

    @Insert("insert into project_version(version_id, prj_id) values (#{versionId}, #{prjId})")
    void addProjectVersion(@Param("versionId") Integer versionId, @Param("prjId") Integer prjId);

    @Insert("insert into version(tag) values (#{tag})")
    @Options(useGeneratedKeys = true, keyColumn = "version_id",keyProperty = "versionId")
    void createVersion(Version version);
}
