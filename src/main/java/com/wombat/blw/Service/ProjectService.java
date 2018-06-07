package com.wombat.blw.Service;

import com.wombat.blw.DTO.DetailedProjectDTO;
import com.wombat.blw.DTO.ProjectOverviewDTO;
import com.wombat.blw.DTO.SimpleProjectDTO;
import com.wombat.blw.Form.ProjectForm;

import java.util.List;

public interface ProjectService {

    List<SimpleProjectDTO> getList(Integer orgId);

    void create(ProjectForm projectForm);

    void delete(Integer projectId);

    DetailedProjectDTO getDetail(Integer prjId);

    List<ProjectOverviewDTO> getCompletedList(Integer coId);

    List<ProjectOverviewDTO> getRequestCreationList(Integer coId);

    List<ProjectOverviewDTO> getRequestReimbursementList(Integer coId);

    List<ProjectOverviewDTO> getInProgressList(Integer coId);

    void passCreation(Integer prjId);

    void passReimbursement(Integer prjId);

    void rejectCreation(Integer prjId);

    void rejectReimbursement(Integer prjId);
}
