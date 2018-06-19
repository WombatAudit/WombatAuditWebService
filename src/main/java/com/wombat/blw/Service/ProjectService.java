package com.wombat.blw.Service;

import com.wombat.blw.DTO.*;
import com.wombat.blw.Form.ProjectForm;

import java.util.List;

public interface ProjectService {

    List<SimpleProjectDTO> getList(Integer orgId);

    List<SimpleProjectDTO> findStartedSimpleList(Integer orgId);

    SimpleProjectDTO create(ProjectForm projectForm);

    void delete(Integer projectId);

    List<ProjectOverviewDTO> findOverviewByCompanyIdAndType(Integer coId, Integer type);

    List<ProjectOverviewDTO> findRelatedOverviewByType(Integer userId, Integer type);

    AdminDetailedProjectDTO findAdminDetailedProject(Integer prjId);

    SimpleProjectDTO findSimpleOne(Integer prjId);

    void updateStatus(Integer prjId, Integer status);

    ProjectDTO findOne(Integer prjId);

    GeneralDetailedProjectDTO findGeneralDetailedProject(Integer prjId);

    GeneralDetailedProjectDTO findDetailedProjectByVersionId(Integer prjId,Integer versionId);

    int addVersionByProject(int prjId,String tag);
}
