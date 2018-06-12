package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Organization;
import com.wombat.blw.DO.Project;
import com.wombat.blw.DTO.DetailedProjectDTO;
import com.wombat.blw.DTO.ProjectOverviewDTO;
import com.wombat.blw.DTO.SimpleProjectDTO;
import com.wombat.blw.Form.ProjectForm;
import com.wombat.blw.Mapper.OrganizationMapper;
import com.wombat.blw.Mapper.ProjectMapper;
import com.wombat.blw.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectMapper projectMapper;
private OrganizationMapper organizationMapper;

    @Override
    public void create(ProjectForm projectForm) {
        //TODO
        projectMapper.createProject(projectForm);
    }

    @Override
    public void delete(Integer projectId) {
        //TODO
        projectMapper.deleteProject(projectId);
    }

    @Override
    public List<SimpleProjectDTO> getList(Integer orgId) {
        //TODO
        List<Project> list=projectMapper.getProjectsByOrg(orgId);
        List<SimpleProjectDTO> res=new ArrayList<>();
        for(Project project:list) {
            SimpleProjectDTO simpleProjectDTO = new SimpleProjectDTO();
            simpleProjectDTO.setName(project.getName());
            simpleProjectDTO.setProjectId(project.getPrjId());
            res.add(simpleProjectDTO);
        }
        return res;
    }

    @Override
    public DetailedProjectDTO getDetail(Integer prjId) {
        //TODO
        Project project=projectMapper.getProjectByPrjId(prjId);
        DetailedProjectDTO detailedProjectDTO = new DetailedProjectDTO();
        detailedProjectDTO.setName(project.getName());
        detailedProjectDTO.setProjectId(project.getPrjId());
        detailedProjectDTO.setDescription(project.getDescription());
        detailedProjectDTO.setStartTime(project.getStartTime());
        detailedProjectDTO.setStatues(project.getStatus());
        return detailedProjectDTO;
    }

    @Override
    public List<ProjectOverviewDTO> getCompletedList(Integer coId) {
        //TODO
        List<Integer> list=organizationMapper.selectOrgIdByCoId(coId);
        List<ProjectOverviewDTO> res=new ArrayList<>();
        for(Integer orgaId:list) {
            List<Project> orgProject=projectMapper.getProjectsByOrg(orgaId);
            for(Project project:orgProject) {
                ProjectOverviewDTO projectOverviewDTO=new ProjectOverviewDTO();
                projectOverviewDTO.setName(project.getName());
                projectOverviewDTO.setOrgName(organizationMapper.selectOrgByOrgId(
                        project.getOrgId()).getName());
                projectOverviewDTO.setStartTime(project.getStartTime());
                projectOverviewDTO.setEndTime(project.getEndTime());
                res.add(projectOverviewDTO);
            }
        }
        return res;
    }

    @Override
    public List<ProjectOverviewDTO> getRequestCreationList(Integer coId) {
        //TODO
        List<Integer> list=organizationMapper.selectOrgIdByCoId(coId);
        List<ProjectOverviewDTO> res=new ArrayList<>();
        for(Integer orgId:list) {
            Organization organization=organizationMapper.selectOrgByOrgId(orgId);
            List<Project> orgProject=projectMapper.getRequestCreationProjectsByOrg(orgId);
            for(Project project:orgProject) {
                ProjectOverviewDTO projectOverviewDTO=new ProjectOverviewDTO();
                projectOverviewDTO.setName(project.getName());
                projectOverviewDTO.setOrgName(organization.getName());
                projectOverviewDTO.setStartTime(project.getStartTime());
                projectOverviewDTO.setEndTime(project.getEndTime());
                res.add(projectOverviewDTO);
            }
        }
        return res;
    }

    @Override
    public List<ProjectOverviewDTO> getRequestReimbursementList(Integer coId) {
        //TODO
        List<Integer> list=organizationMapper.selectOrgIdByCoId(coId);
        List<ProjectOverviewDTO> res=new ArrayList<>();
        for(Integer orgId:list) {
            Organization organization=organizationMapper.selectOrgByOrgId(orgId);
            List<Project> orgProject=projectMapper.getRequestReimbursementProjectsByOrg(orgId);
            for(Project project:orgProject) {
                ProjectOverviewDTO projectOverviewDTO=new ProjectOverviewDTO();
                projectOverviewDTO.setName(project.getName());
                projectOverviewDTO.setOrgName(organization.getName());
                projectOverviewDTO.setEndTime(project.getEndTime());
                projectOverviewDTO.setStartTime(project.getStartTime());
                res.add(projectOverviewDTO);
            }
        }
        return res;
    }

    @Override
    public List<ProjectOverviewDTO> getInProgressList(Integer coId) {
        //TODO
        List<Integer> list=organizationMapper.selectOrgIdByCoId(coId);
        List<ProjectOverviewDTO> res=new ArrayList<>();
        for(Integer orgId:list) {
            Organization organization=organizationMapper.selectOrgByOrgId(orgId);
            List<Project> orgProject=projectMapper.getInProgressProjectsByOrg(orgId);
            for(Project project:orgProject) {
                ProjectOverviewDTO projectOverviewDTO=new ProjectOverviewDTO();
                projectOverviewDTO.setName(project.getName());
                projectOverviewDTO.setEndTime(project.getEndTime());
                projectOverviewDTO.setOrgName(organization.getName());
                projectOverviewDTO.setStartTime(project.getStartTime());
                res.add(projectOverviewDTO);
            }
        }
        return res;
    }

    @Override
    public void passCreation(Integer prjId) {
        //TODO
        projectMapper.passCreation(prjId);
    }

    @Override
    public void passReimbursement(Integer prjId) {
        //TODO
        projectMapper.passReimbursement(prjId);
    }

    @Override
    public void rejectCreation(Integer prjId) {
        //TODO
        projectMapper.rejectCreation(prjId);
    }

    @Override
    public void rejectReimbursement(Integer prjId) {
        //TODO
        projectMapper.rejectReimbursement(prjId);
    }
}

