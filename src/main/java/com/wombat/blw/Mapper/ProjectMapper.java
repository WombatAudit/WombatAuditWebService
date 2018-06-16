package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Item;
import com.wombat.blw.DO.Project;
import com.wombat.blw.DO.Receipt;
import com.wombat.blw.DO.Version;
import com.wombat.blw.Form.ProjectForm;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component
public interface ProjectMapper {

    @Insert("insert into project " +
            "values (null,#{orgId},#{name},#{description},null,0,null)")
    void createProject(ProjectForm projectForm);

    @Update("update project set end_time=null and status=4 where prj_id=#{projectId}")
    void deleteProject(Integer projectId);

    @Select("select name from project where prj_id = #{prjId}")
    @ResultType(String.class)
    String findPrjName(Integer prjId);

    @Select("select prj_id,org_id,name,description,start_time,status,end_time " +
            "from project where org_id=#{orgId}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "prj_id", javaType = String.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "version", column = "version", javaType = Integer.class)
    })
    List<Project> getProjectsByOrg(Integer orgId);

    @Select("select prj_id,org_id,name,description,start_time,status,end_time " +
            "from project where org_id=#{orgId} and status != 0")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "prj_id", javaType = String.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "version", column = "version", javaType = Integer.class)
    })
    List<Project> getActiveProjectsByOrg(Integer orgId);

    @Select("select prj_id,org_id,name,description,start_time,status,end_time " +
            "from project where prj_id=#{prjId}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class)
    })
    Project getProjectByPrjId(Integer prjId);

    @Select("select prj_id,org_id,name,description,start_time,status,end_time " +
            "from project where org_id=#{orgId} and status=1")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class)
    })
    List<Project> getRequestCreationProjectsByOrg(Integer orgId);

    @Select("select prj_id,org_id,name,description,start_time,status,end_time " +
            "from project where org_id=#{orgId} and status=3")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class)
    })
    List<Project> getRequestReimbursementProjectsByOrg(Integer orgId);

    @Select("select prj_id,org_id,name,description,start_time,status,end_time " +
            "from project where org_id=#{orgId} and status=2")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class)
    })
    List<Project> getInProgressProjectsByOrg(Integer orgId);

    @Select("select prj_id, project.org_id, project.name, project.description, start_time, status, end_time from project join organization " +
            "on project.org_id = organization.org_id where organization.co_id = #{coId} and project.status = #{type}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "prj_id", javaType = String.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "version", column = "version", javaType = Integer.class)
    })
    List<Project> findByCoIdAndType(@Param("coId") Integer coId, @Param("type") Integer type);

    @Select("select * from project where prj_id = #{prjId}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "status", column = "status", javaType = Integer.class),
            @Result(property = "version", column = "version", javaType = Integer.class)
    })
    Project findById(Integer prjId);

    @Select("select * from version where prj_id = #{prjId} and version = (select max(version) from version where prj_id = #{prjId})")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "version", column = "version", javaType = Integer.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    Version findMaxDetailVersion(Integer prjId);

    @Select("select * from item natural join detail where prj_id = #{prjId} and version = #{version}")
    @Results({
            @Result(property = "itemId", column = "item_id", javaType = Integer.class),
            @Result(property = "type", column = "type", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class),
            @Result(property = "description", column = "description", javaType = String.class),
            @Result(property = "quantity", column = "quantity", javaType = Integer.class),
            @Result(property = "amount", column = "amount", javaType = BigDecimal.class),
            @Result(property = "rcptId", column = "rcpt_id", javaType = Integer.class)
    })
    List<Item> findItems(@Param("prjId") Integer prjId, @Param("version") Integer version);

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

    @Select("select * from receipt where rcpt_id = #{rcptId}")
    @Results({
            @Result(property = "rcptId", column = "rcpt_id", javaType = Integer.class),
            @Result(property = "receipt", column = "receipt", javaType = String.class),
            @Result(property = "invoice", column = "invoice", javaType = String.class),
            @Result(property = "transaction", column = "transaction", javaType = String.class),
            @Result(property = "attachment", column = "attachment", javaType = String.class)
    })
    Receipt findReceipt(Integer rcptId);

    @Update("update project set status = #{status} where prj_id = #{prjId}")
    void updateStatus(@Param("prjId") Integer prjId, @Param("status") Integer status);

    @Select("select * from version where prj_id = #{prjId} and version = #{version}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "version", column = "version", javaType = Integer.class),
            @Result(property = "createTime", column = "create_time", javaType = Date.class)
    })
    Version findVersion(@Param("prjId") Integer prjId, @Param("version") Integer version);
}
