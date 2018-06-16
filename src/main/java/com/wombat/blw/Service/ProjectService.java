package com.wombat.blw.Service;

import com.wombat.blw.DO.Version;
import com.wombat.blw.DTO.DetailedItemDTO;
import com.wombat.blw.DTO.DetailedProjectDTO;
import com.wombat.blw.DTO.ProjectOverviewDTO;
import com.wombat.blw.DTO.SimpleProjectDTO;
import com.wombat.blw.Form.ProjectForm;

import java.util.List;

public interface ProjectService {

    List<SimpleProjectDTO> getList(Integer orgId);

    List<SimpleProjectDTO> findActiveList(Integer orgId);

    void create(ProjectForm projectForm);

    void delete(Integer projectId);

    List<ProjectOverviewDTO> getCompletedList(Integer coId);

    List<ProjectOverviewDTO> getRequestCreationList(Integer coId);

    List<ProjectOverviewDTO> getRequestReimbursementList(Integer coId);

    List<ProjectOverviewDTO> getInProgressList(Integer coId);

    List<ProjectOverviewDTO> findOverViewByCompanyIdAndType(Integer coId, Integer type);

    DetailedProjectDTO findDetailedProject(Integer prjId);

    Version findLatestDetailVersion(Integer prjId);

    SimpleProjectDTO findSimpleOne(Integer prjId);

    void updateStatus(Integer prjId, Integer status);
}
