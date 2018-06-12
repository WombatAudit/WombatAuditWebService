package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Project;
import com.wombat.blw.Form.ProjectForm;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface ProjectMapper {
    @Insert("insert into project " +
            "values (null,#{orgId},#{name},#{description},null,0,null)")
    void createProject(ProjectForm projectForm);

    @Update("update project set end_time=null and status=4 where prj_id=#{projectId}")
    void deleteProject(Integer projectId);

    @Select("select prj_id,org_id,name,description,start_time,status,end_time " +
            "from project where org_id=#{orgId}")
    @Results({
            @Result(property = "prjId", column = "prj_id", javaType = Integer.class),
            @Result(property = "orgId", column = "org_id", javaType = Integer.class),
            @Result(property = "startTime", column = "start_time", javaType = Date.class),
            @Result(property = "endTime", column = "end_time", javaType = Date.class)
    })
    List<Project> getProjectsByOrg(Integer orgId);

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


    @Update("update project set status=2 where prj_id=#{prjId}")
    void passCreation(Integer prjId);
    @Update("update project set status=4 where prj_id=#{prjId}")
    void passReimbursement(Integer prjId);
    @Update("update project set status=0 where prj_id=#{prjId}")
    void rejectCreation(Integer prjId);
    @Update("update project set status=2 where prj_id=#{prjId}")
    void rejectReimbursement(Integer prjId);
}
