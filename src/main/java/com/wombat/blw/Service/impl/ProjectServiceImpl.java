package com.wombat.blw.Service.impl;

import com.wombat.blw.DTO.DetailedProjectDTO;
import com.wombat.blw.DTO.ProjectOverviewDTO;
import com.wombat.blw.DTO.SimpleProjectDTO;
import com.wombat.blw.Form.ProjectForm;
import com.wombat.blw.Service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Override
    public void create(ProjectForm projectForm) {
        //TODO
    }

    @Override
    public void delete(Integer projectId) {
        //TODO
    }

    @Override
    public List<SimpleProjectDTO> getList(Integer orgId) {
        //TODO
        return null;
    }

    @Override
    public DetailedProjectDTO getDetail(Integer prjId) {
        //TODO
        return null;
    }

    @Override
    public List<ProjectOverviewDTO> getCompletedList(Integer coId) {
        //TODO
        return null;
    }

    @Override
    public List<ProjectOverviewDTO> getRequestCreationList(Integer coId) {
        //TODO
        return null;
    }

    @Override
    public List<ProjectOverviewDTO> getRequestReimbursementList(Integer coId) {
        //TODO
        return null;
    }

    @Override
    public List<ProjectOverviewDTO> getInProgressList(Integer coId) {
        //TODO
        return null;
    }

    @Override
    public void passCreation(Integer prjId) {
        //TODO
    }

    @Override
    public void passReimbursement(Integer prjId) {
        //TODO
    }

    @Override
    public void rejectCreation(Integer prjId) {
        //TODO
    }

    @Override
    public void rejectReimbursement(Integer prjId) {
        //TODO
    }
}

