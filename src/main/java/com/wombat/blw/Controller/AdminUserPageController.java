package com.wombat.blw.Controller;

import com.wombat.blw.DTO.*;
import com.wombat.blw.Enum.ProjectStatusEnum;
import com.wombat.blw.Exception.ProjectStatusException;
import com.wombat.blw.Service.*;
import com.wombat.blw.Util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class AdminUserPageController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/admin/organizations")
    public ModelAndView adminOrganizations(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        SimpleCompanyDTO simpleCompanyDTO = companyService.findSimpleOne(userId);
        List<OrganizationDTO> organizationDTOList = organizationService.findCompanyOrgs(simpleCompanyDTO.getCompanyId());
        map.put("orgList", organizationDTOList);
        return new ModelAndView("admin/organizations");
    }

    @GetMapping("/admin/organizations/{id}")
    public ModelAndView adminDetailedOrganization(Map<String, Object> map, HttpServletRequest request, @PathVariable("id") Integer orgId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        OrganizationDTO organizationDTO = organizationService.getOne(orgId);
        List<MemberDTO> memberDTOList = organizationService.getMemberList(orgId);
        List<SimpleProjectDTO> simpleProjectDTOList = projectService.findActiveList(orgId);
        map.put("org", organizationDTO);
        map.put("memberList", memberDTOList);
        map.put("projectList", simpleProjectDTOList);
        return new ModelAndView("admin/detailedOrganization");
    }

    @GetMapping("/admin/projects/toReimburse")
    public ModelAndView adminProjectsToReimburse(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        Integer coId = companyService.getCoId(userId);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findOverViewByCompanyIdAndType(coId,
                ProjectStatusEnum.REQUEST_REIMBURSEMENT.getCode());
        map.put("projectList", projectOverviewDTOList);
        return new ModelAndView("admin/projectsToReimburse");
    }

    @GetMapping("/admin/projects/inProgress")
    public ModelAndView adminProjectsInProgress(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        Integer coId = companyService.getCoId(userId);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findOverViewByCompanyIdAndType(coId,
                ProjectStatusEnum.IN_PROGRESS.getCode());
        map.put("projectList", projectOverviewDTOList);
        return new ModelAndView("admin/projectsInProgress");
    }

    @GetMapping("/admin/projects/deferred")
    public ModelAndView adminProjectsDeferred(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        Integer coId = companyService.getCoId(userId);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findOverViewByCompanyIdAndType(coId,
                ProjectStatusEnum.DEFERRED.getCode());
        map.put("projectList", projectOverviewDTOList);
        return new ModelAndView("admin/projectsDeferred");
    }

    @GetMapping("/admin/projects/toCreate")
    public ModelAndView adminProjectsToCreate(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        Integer coId = companyService.getCoId(userId);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findOverViewByCompanyIdAndType(coId,
                ProjectStatusEnum.REQUEST_CREATION.getCode());
        map.put("projectList", projectOverviewDTOList);
        return new ModelAndView("admin/projectsToCreate");
    }

    @GetMapping("/admin/projects/{id}")
    public ModelAndView adminDetailedProject(Map<String, Object> map, HttpServletRequest request, @PathVariable("id") Integer prjId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        DetailedProjectDTO detailedProjectDTO = projectService.findDetailedProject(prjId);
        map.put("prj", detailedProjectDTO);
        return new ModelAndView("admin/detailedProject");
    }

    @GetMapping("/admin/projects/{prjId}/items/{itemId}")
    public ModelAndView adminDetailedItem(Map<String, Object> map, HttpServletRequest request, @PathVariable("prjId") Integer prjId, @PathVariable("itemId") Integer itemId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        DetailedItemDTO detailedItemDTO = itemService.findDetailedItem(prjId, itemId);
        map.put("item", detailedItemDTO);
        return new ModelAndView("admin/detailedItem", map);
    }

    @GetMapping("/admin/projects/{prjId}/actions/accept")
    public ModelAndView adminAcceptProject(@PathVariable("prjId") Integer prjId, String feedback) {
        // Todo Deal with the feedback

        SimpleProjectDTO simpleProjectDTO = projectService.findSimpleOne(prjId);
        if (simpleProjectDTO.getStatus().equals(ProjectStatusEnum.REQUEST_CREATION.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.IN_PROGRESS.getCode());
        } else if (simpleProjectDTO.getStatus().equals(ProjectStatusEnum.REQUEST_REIMBURSEMENT.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.COMPLETED.getCode());
        } else {
            throw new ProjectStatusException();
        }
        return new ModelAndView("redirect:/admin/projects/" + prjId);
    }

    @GetMapping("/admin/projects/{prjId}/actions/reject")
    public ModelAndView adminRejectProject(Map<String, Object> map, HttpServletRequest request, @PathVariable("prjId") Integer prjId, String feedback) {
        // Todo Deal with the feedback

        SimpleProjectDTO simpleProjectDTO = projectService.findSimpleOne(prjId);
        if (simpleProjectDTO.getStatus().equals(ProjectStatusEnum.REQUEST_CREATION.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.NOT_STARTED.getCode());
        } else if (simpleProjectDTO.getStatus().equals(ProjectStatusEnum.REQUEST_REIMBURSEMENT.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.IN_PROGRESS.getCode());
        } else {
            throw new ProjectStatusException();
        }
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        map.put("message", "Successfully review");
        return new ModelAndView("admin/success", map);
    }
}
