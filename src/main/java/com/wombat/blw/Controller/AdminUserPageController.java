package com.wombat.blw.Controller;

import com.wombat.blw.Constant.MessageConstant;
import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.*;
import com.wombat.blw.Enum.ProjectStatusEnum;
import com.wombat.blw.Exception.ProjectStatusException;
import com.wombat.blw.Form.NotificationForm;
import com.wombat.blw.Form.ReceiverForm;
import com.wombat.blw.Service.*;
import com.wombat.blw.Util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private MessageService messageService;

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
        List<MemberDTO> memberDTOList = organizationService.findMembersInOrg(orgId);
        List<SimpleProjectDTO> simpleProjectDTOList = projectService.findStartedSimpleList(orgId);
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
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findOverviewByCompanyIdAndType(coId,
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
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findOverviewByCompanyIdAndType(coId,
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
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findOverviewByCompanyIdAndType(coId,
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
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findOverviewByCompanyIdAndType(coId,
                ProjectStatusEnum.REQUEST_CREATION.getCode());
        map.put("projectList", projectOverviewDTOList);
        return new ModelAndView("admin/projectsToCreate");
    }

    @GetMapping("/admin/projects/{id}")
    public ModelAndView adminDetailedProject(Map<String, Object> map, HttpServletRequest request, @PathVariable("id") Integer prjId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        AdminDetailedProjectDTO adminDetailedProjectDTO = projectService.findAdminDetailedProject(prjId);
        map.put("prj", adminDetailedProjectDTO);
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
    public ModelAndView adminAcceptProject(HttpServletRequest request, @PathVariable("prjId") Integer prjId, String feedback) {
        // Todo Deal with the feedback
        SimpleProjectDTO simpleProjectDTO = projectService.findSimpleOne(prjId);
        String message;
        if (simpleProjectDTO.getStatus().equals(ProjectStatusEnum.REQUEST_CREATION.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.IN_PROGRESS.getCode());
            message = String.format(MessageConstant.PROJECT_CREATION_REQUEST_ACCEPTED_PATTERN, projectService.findPrjName(prjId), feedback);
        } else if (simpleProjectDTO.getStatus().equals(ProjectStatusEnum.REQUEST_REIMBURSEMENT.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.COMPLETED.getCode());
            message = String.format(MessageConstant.PROJECT_REIMBURSEMENT_REQUEST_ACCEPTED_PATTERN, projectService.findPrjName(prjId), feedback);
        } else {
            throw new ProjectStatusException();
        }
        NotificationForm notificationForm = new NotificationForm();
        notificationForm.setContent(message);
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        notificationForm.setSenderId(userId);
        notificationForm.setReceiveListId(UUID.randomUUID().toString());
        List<Integer> receiverIdList = new ArrayList<>();
        User user = organizationService.findManager(simpleProjectDTO.getOrgId());
        receiverIdList.add(user.getUserId());
        messageService.sendMessage(notificationForm, receiverIdList);
        return new ModelAndView("redirect:/admin/projects/" + prjId);
    }

    @GetMapping("/admin/projects/{prjId}/actions/reject")
    public ModelAndView adminRejectProject(Map<String, Object> map, HttpServletRequest request, @PathVariable("prjId") Integer prjId, String feedback) {
        // Todo Deal with the feedback
        SimpleProjectDTO simpleProjectDTO = projectService.findSimpleOne(prjId);
        String message;
        if (simpleProjectDTO.getStatus().equals(ProjectStatusEnum.REQUEST_CREATION.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.NOT_STARTED.getCode());
            message = String.format(MessageConstant.PROJECT_CREATION_REQUEST_REJECTED_PATTERN, projectService.findPrjName(prjId), feedback);
        } else if (simpleProjectDTO.getStatus().equals(ProjectStatusEnum.REQUEST_REIMBURSEMENT.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.IN_PROGRESS.getCode());
            message = String.format(MessageConstant.PROJECT_REIMBURSEMENT_REQUEST_REJECTED_PATTERN, projectService.findPrjName(prjId), feedback);
        } else {
            throw new ProjectStatusException();
        }
        NotificationForm notificationForm = new NotificationForm();
        notificationForm.setContent(message);
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        notificationForm.setSenderId(userId);
        notificationForm.setReceiveListId(UUID.randomUUID().toString());
        List<Integer> receiverIdList = new ArrayList<>();
        User user = organizationService.findManager(simpleProjectDTO.getOrgId());
        receiverIdList.add(user.getUserId());
        messageService.sendMessage(notificationForm, receiverIdList);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        map.put("message", "Successfully review");
        return new ModelAndView("admin/success", map);
    }

    @GetMapping("/admin/pages/notifications")
    public ModelAndView adminPageNotification(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<NotificationDTO> notificationDTOList = messageService.findNotRead(userId);
        map.put("notifyList", notificationDTOList);
        return new ModelAndView("admin/notifications", map);
    }

    @PostMapping("/admin/notifications/actions/read")
    public ModelAndView adminNotificationRead(HttpServletRequest request, String listId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        ReceiverForm receiverForm = new ReceiverForm();
        receiverForm.setUserId(userId);
        receiverForm.setListId(listId);
        messageService.setRead(receiverForm);
        return new ModelAndView("redirect:/admin/pages/notifications");
    }
}
