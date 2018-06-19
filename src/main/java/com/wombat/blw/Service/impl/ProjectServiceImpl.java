package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Item;
import com.wombat.blw.DO.Organization;
import com.wombat.blw.DO.Project;
import com.wombat.blw.DO.Version;
import com.wombat.blw.DTO.*;
import com.wombat.blw.Enum.ProjectStatusEnum;
import com.wombat.blw.Form.ProjectForm;
import com.wombat.blw.Mapper.OrganizationMapper;
import com.wombat.blw.Mapper.ProjectMapper;
import com.wombat.blw.Service.ProjectService;
import com.wombat.blw.Util.DateUtil;
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
    public SimpleProjectDTO create(ProjectForm projectForm) {
        Project project = new Project();
        BeanUtils.copyProperties(projectForm, project);
        project.setStatus(ProjectStatusEnum.NOT_STARTED.getCode());
        project.setStartTime(DateUtil.str2Date(projectForm.getStartTime(), DateUtil.FORMAT_YMDTHM));
        project.setEndTime(DateUtil.str2Date(projectForm.getEndTime(), DateUtil.FORMAT_YMDTHM));
        projectMapper.create(project);
        Version version = new Version();
        version.setTag("Initial");
        projectMapper.createVersion(version);
        projectMapper.addProjectVersion(project.getPrjId(), version.getVersionId());
        projectMapper.updateProjectVersion(project.getPrjId(), 1);
        Project newProject = projectMapper.findById(project.getPrjId());
        SimpleProjectDTO simpleProjectDTO = new SimpleProjectDTO();
        BeanUtils.copyProperties(newProject, simpleProjectDTO);
        return simpleProjectDTO;
    }

    @Override
    public void delete(Integer projectId) {
        //Todo
    }

    @Override
    public List<SimpleProjectDTO> getList(Integer orgId) {
        List<Project> projectList = projectMapper.findProjectsByOrg(orgId);
        return projectList.stream().map(e -> new SimpleProjectDTO(e.getPrjId(), e.getOrgId(), e.getName(), e.getStatus(),
                e.getStartTime(), e.getEndTime())).collect(Collectors.toList());
    }

    @Override
    public List<SimpleProjectDTO> findStartedSimpleList(Integer orgId) {
        List<Project> projectList = projectMapper.findByOrgIdAndNotInStatus(orgId, ProjectStatusEnum.NOT_STARTED.getCode());
        return projectList.stream().map(e -> new SimpleProjectDTO(e.getPrjId(), e.getOrgId(), e.getName(), e.getStatus(),
                e.getStartTime(), e.getEndTime())).collect(Collectors.toList());
    }

    @Override
    public List<ProjectOverviewDTO> findOverviewByCompanyIdAndType(Integer coId, Integer type) {
        List<Project> projectList = projectMapper.findByCoIdAndType(coId, type);
        return projectList.stream().map(e -> new ProjectOverviewDTO(e.getPrjId(), e.getName(),
                organizationMapper.findOrgNameById(e.getOrgId()), e.getStartTime(), e.getEndTime()))
                .collect(Collectors.toList());
    }

    @Override
    public AdminDetailedProjectDTO findAdminDetailedProject(Integer prjId) {
        Project project = projectMapper.findById(prjId);
        AdminDetailedProjectDTO adminDetailedProjectDTO = new AdminDetailedProjectDTO();
        BeanUtils.copyProperties(project, adminDetailedProjectDTO);
        adminDetailedProjectDTO.setOrgName(organizationMapper.findOrgNameById(project.getOrgId()));
        Version version = projectMapper.findVersion(project.getVersionId());
        adminDetailedProjectDTO.setVersionTime(version.getCreateTime());
        List<Item> itemList = projectMapper.findItems(version.getVersionId());
        List<SimpleItemDTO> simpleItemDTOList = itemList.stream().map(e -> new SimpleItemDTO(e.getItemId(), e.getType(),
                e.getName(), e.getQuantity(), e.getAmount())).collect(Collectors.toList());
        adminDetailedProjectDTO.setSimpleItemDTOList(simpleItemDTOList);
        Function<SimpleItemDTO, BigDecimal> subTotal = simpleItemDTO -> simpleItemDTO.getAmount().multiply(new BigDecimal(simpleItemDTO.getQuantity()));
        BigDecimal totalCost = simpleItemDTOList.stream().map(subTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
        adminDetailedProjectDTO.setTotalCost(totalCost);
        return adminDetailedProjectDTO;
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

    @Override
    public List<ProjectOverviewDTO> findRelatedOverviewByType(Integer userId, Integer type) {
        List<Organization> organizationList = organizationMapper.findOrgsByUserId(userId);
        List<Project> projectList = new ArrayList<>();
        for (Organization organization : organizationList) {
            List<Project> subList = projectMapper.findByOrgIdAndStatus(organization.getOrganizationId(), type);
            projectList.addAll(subList);
        }
        return projectList.stream().map(e -> new ProjectOverviewDTO(e.getPrjId(), e.getName(),
                organizationMapper.findOrgNameById(e.getOrgId()), e.getStartTime(), e.getEndTime())).collect(Collectors.toList());
    }

    @Override
    public ProjectDTO findOne(Integer prjId) {
        Project project = projectMapper.findById(prjId);
        ProjectDTO projectDTO = new ProjectDTO();
        BeanUtils.copyProperties(project, projectDTO);
        return projectDTO;
    }

    @Override
    public GeneralDetailedProjectDTO findGeneralDetailedProject(Integer prjId) {
        Project project = projectMapper.findById(prjId);
        GeneralDetailedProjectDTO generalDetailedProjectDTO = new GeneralDetailedProjectDTO();
        BeanUtils.copyProperties(project, generalDetailedProjectDTO);
        generalDetailedProjectDTO.setOrgName(organizationMapper.findOrgNameById(project.getOrgId()));
        Version version = projectMapper.findVersion(project.getVersionId());
        generalDetailedProjectDTO.setVersionId(version.getVersionId());
        generalDetailedProjectDTO.setVersionTag(version.getTag());
        generalDetailedProjectDTO.setVersionTime(version.getCreateTime());
        List<Item> itemList = projectMapper.findItems(version.getVersionId());
        List<ItemDTO> itemDTOList = itemList.stream().map(e -> new ItemDTO(e.getItemId(), e.getType(),
                e.getName(), e.getDescription(), e.getQuantity(), e.getAmount(), projectMapper.findPrjName(prjId), prjId))
                .collect(Collectors.toList());
        generalDetailedProjectDTO.setItemDTOList(itemDTOList);
        return generalDetailedProjectDTO;
    }

    @Override
    public int addVersionByProject(int prjId, String tag) {
        Version version=new Version();
        version.setTag(tag);
        projectMapper.createVersion(version);
        projectMapper.addProjectVersion(version.getVersionId(),prjId);
        return version.getVersionId();
    }

    @Override
    public GeneralDetailedProjectDTO findDetailedProjectByVersionId(Integer prjId,Integer versionId) {
        Project project = projectMapper.findById(prjId);
        GeneralDetailedProjectDTO generalDetailedProjectDTO = new GeneralDetailedProjectDTO();
        BeanUtils.copyProperties(project, generalDetailedProjectDTO);
        generalDetailedProjectDTO.setOrgName(organizationMapper.findOrgNameById(project.getOrgId()));
        Version version = projectMapper.findVersion(versionId);
        generalDetailedProjectDTO.setVersionId(version.getVersionId());
        generalDetailedProjectDTO.setVersionTag(version.getTag());
        generalDetailedProjectDTO.setVersionTime(version.getCreateTime());
        List<Item> itemList = projectMapper.findItems(version.getVersionId());
        List<ItemDTO> itemDTOList = itemList.stream().map(e -> new ItemDTO(e.getItemId(), e.getType(),
                e.getName(), e.getDescription(), e.getQuantity(), e.getAmount(), projectMapper.findPrjName(prjId), prjId))
                .collect(Collectors.toList());
        generalDetailedProjectDTO.setItemDTOList(itemDTOList);
        return generalDetailedProjectDTO;
    }

    @Override
    public List<VersionDTO> findVersionList(Integer prjId) {
        List<Version> versionList = projectMapper.findVersionByPrjId(prjId);
        return versionList.stream().map(e -> new VersionDTO(e.getVersionId(), e.getTag(), e.getCreateTime())).collect(Collectors.toList());
    }

    @Override
    public void updateVersion(Integer prjId, Integer versionId) {
        projectMapper.updateProjectVersion(prjId, versionId);
    }
}