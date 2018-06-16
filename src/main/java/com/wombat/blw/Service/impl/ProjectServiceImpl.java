package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Item;
import com.wombat.blw.DO.Organization;
import com.wombat.blw.DO.Project;
import com.wombat.blw.DO.Version;
import com.wombat.blw.DTO.*;
import com.wombat.blw.Form.ProjectForm;
import com.wombat.blw.Mapper.OrganizationMapper;
import com.wombat.blw.Mapper.ProjectMapper;
import com.wombat.blw.Service.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public void create(ProjectForm projectForm) {
        projectMapper.createProject(projectForm);
    }

    @Override
    public void delete(Integer projectId) {
        projectMapper.deleteProject(projectId);
    }

    @Override
    public List<SimpleProjectDTO> getList(Integer orgId) {
        List<Project> projectList = projectMapper.getProjectsByOrg(orgId);
        return projectList.stream().map(e -> new SimpleProjectDTO(e.getPrjId(), e.getName(), e.getStatus(),
                e.getStartTime(), e.getEndTime())).collect(Collectors.toList());
    }

    @Override
    public List<SimpleProjectDTO> findActiveList(Integer orgId) {
        List<Project> projectList = projectMapper.getActiveProjectsByOrg(orgId);
        return projectList.stream().map(e -> new SimpleProjectDTO(e.getPrjId(), e.getName(), e.getStatus(),
                e.getStartTime(), e.getEndTime())).collect(Collectors.toList());
    }

    @Override
    public List<ProjectOverviewDTO> getCompletedList(Integer coId) {
        List<Integer> list = organizationMapper.selectOrgIdByCoId(coId);
        List<ProjectOverviewDTO> res = new ArrayList<>();
        for (Integer orgaId : list) {
            List<Project> orgProject = projectMapper.getProjectsByOrg(orgaId);
            for (Project project : orgProject) {
                ProjectOverviewDTO projectOverviewDTO = new ProjectOverviewDTO();
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
        List<Integer> list = organizationMapper.selectOrgIdByCoId(coId);
        List<ProjectOverviewDTO> res = new ArrayList<>();
        for (Integer orgId : list) {
            Organization organization = organizationMapper.selectOrgByOrgId(orgId);
            List<Project> orgProject = projectMapper.getRequestCreationProjectsByOrg(orgId);
            for (Project project : orgProject) {
                ProjectOverviewDTO projectOverviewDTO = new ProjectOverviewDTO();
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
        List<Integer> list = organizationMapper.selectOrgIdByCoId(coId);
        List<ProjectOverviewDTO> res = new ArrayList<>();
        for (Integer orgId : list) {
            Organization organization = organizationMapper.selectOrgByOrgId(orgId);
            List<Project> orgProject = projectMapper.getRequestReimbursementProjectsByOrg(orgId);
            for (Project project : orgProject) {
                ProjectOverviewDTO projectOverviewDTO = new ProjectOverviewDTO();
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
        List<Integer> list = organizationMapper.selectOrgIdByCoId(coId);
        List<ProjectOverviewDTO> res = new ArrayList<>();
        for (Integer orgId : list) {
            Organization organization = organizationMapper.selectOrgByOrgId(orgId);
            List<Project> orgProject = projectMapper.getInProgressProjectsByOrg(orgId);
            for (Project project : orgProject) {
                ProjectOverviewDTO projectOverviewDTO = new ProjectOverviewDTO();
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
    public List<ProjectOverviewDTO> findOverViewByCompanyIdAndType(Integer coId, Integer type) {
        List<Project> projectList = projectMapper.findByCoIdAndType(coId, type);
        return projectList.stream().map(e -> new ProjectOverviewDTO(e.getPrjId(), e.getName(),
                organizationMapper.findOrgNameById(e.getOrgId()), e.getStartTime(), e.getEndTime()))
                .collect(Collectors.toList());
    }

    @Override
    public Version findLatestDetailVersion(Integer prjId) {
        return projectMapper.findMaxDetailVersion(prjId);
    }

    @Override
    public DetailedProjectDTO findDetailedProject(Integer prjId) {
        Project project = projectMapper.findById(prjId);
        DetailedProjectDTO detailedProjectDTO = new DetailedProjectDTO();
        BeanUtils.copyProperties(project, detailedProjectDTO);
        detailedProjectDTO.setOrgName(organizationMapper.findOrgNameById(project.getOrgId()));
        Version version = projectMapper.findVersion(prjId, project.getVersion());
        detailedProjectDTO.setVersionTime(version.getCreateTime());
        List<Item> itemList = projectMapper.findItems(prjId, version.getVersion());
        List<SimpleItemDTO> simpleItemDTOList = itemList.stream().map(e -> new SimpleItemDTO(e.getItemId(), e.getType(),
                e.getName(), e.getQuantity(), e.getAmount())).collect(Collectors.toList());
        detailedProjectDTO.setSimpleItemDTOList(simpleItemDTOList);
        Function<SimpleItemDTO, BigDecimal> subTotal = simpleItemDTO -> simpleItemDTO.getAmount().multiply(new BigDecimal(simpleItemDTO.getQuantity()));
        BigDecimal totalCost = simpleItemDTOList.stream().map(subTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        detailedProjectDTO.setTotalCost(totalCost);
        return detailedProjectDTO;
    }

    @Override
    public SimpleProjectDTO findSimpleOne(Integer prjId) {
        Project project = projectMapper.findById(prjId);
        SimpleProjectDTO simpleProjectDTO = new SimpleProjectDTO();
        BeanUtils.copyProperties(project, simpleProjectDTO);
        return simpleProjectDTO;
    }

    @Override
    public void updateStatus(Integer prjId, Integer status) {
        projectMapper.updateStatus(prjId, status);
    }
}

