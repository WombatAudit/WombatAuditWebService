package com.wombat.blw.Controller;

import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.*;
import com.wombat.blw.Enum.AssignmentStatusEnum;
import com.wombat.blw.Enum.ProjectStatusEnum;
import com.wombat.blw.Exception.InvalidParameterException;
import com.wombat.blw.Exception.ProjectStatusException;
import com.wombat.blw.Form.ItemForm;
import com.wombat.blw.Form.OrganizationForm;
import com.wombat.blw.Form.ProjectForm;
import com.wombat.blw.Form.ReceiptForm;
import com.wombat.blw.Service.*;
import com.wombat.blw.Util.FileUtil;
import com.wombat.blw.Util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class GeneralUserPageController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/general/organizations")
    public ModelAndView generalOrganizations(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<OrganizationDTO> organizationDTOList = organizationService.findJoinedOrgs(userId);
        map.put("orgList", organizationDTOList);
        return new ModelAndView("general/organizations", map);
    }

    @GetMapping("/general/organizations/{id}")
    public ModelAndView generalDetailedOrganization(Map<String, Object> map, HttpServletRequest request, @PathVariable("id") Integer orgId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        OrganizationDTO organizationDTO = organizationService.getOne(orgId);
        List<MemberDTO> memberDTOList = organizationService.findMembersInOrg(orgId);
        List<SimpleProjectDTO> simpleProjectDTOList = projectService.findStartedSimpleList(orgId);
        map.put("org", organizationDTO);
        map.put("memberList", memberDTOList);
        map.put("projectList", simpleProjectDTOList);
        if (userService.ifManagesOrg(userId, orgId)) {
            map.put("manage", "Yes");
        }
        return new ModelAndView("general/detailedOrganization", map);
    }

    @GetMapping("/general/organizations/pages/create")
    public ModelAndView generalPageCreateOrganization(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        return new ModelAndView("general/newOrganization", map);
    }

    @PostMapping("/general/organizations")
    public ModelAndView generalCreateOrganization(HttpServletRequest request, @Validated OrganizationForm organizationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
            throw new InvalidParameterException();
        }
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        OrganizationDTO organizationDTO = organizationService.create(userId, organizationForm);
        Integer orgId = organizationDTO.getOrganizationId();
        return new ModelAndView("redirect:/general/organizations/" + orgId);
    }

    @GetMapping("/general/organizations/{id}/invite")
    public ModelAndView generalPageOrganizationInvite(Map<String, Object> map, HttpServletRequest request, @PathVariable("id") Integer orgId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        OrganizationDTO organizationDTO = organizationService.getOne(orgId);
        map.put("org", organizationDTO);
        List<MemberDTO> memberDTOList = organizationService.findMemberListNotIn(simpleUserDTO.getCompanyId(), orgId);
        map.put("memberList", memberDTOList);
        return new ModelAndView("general/organizationInvite", map);
    }

    @PostMapping("/general/organizations/{id}/actions/invite")
    public ModelAndView generalOrganizationInvite(Integer memberId, @PathVariable("id") Integer orgId) {
        organizationService.join(orgId, memberId);
        return new ModelAndView("redirect:/general/organizations/" + orgId);
    }

    @PostMapping("/general/organizations/{id}/actions/remove")
    public ModelAndView generalOrganizationsRemove(Integer memberId, @PathVariable("id") Integer orgId) {
        organizationService.remove(orgId, memberId);
        return new ModelAndView("redirect:/general/organizations" + orgId);
    }

    @GetMapping("/general/projects/pages/create")
    public ModelAndView generalPageCreateProject(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<SimpleOrganizationDTO> simpleOrganizationDTOList = organizationService.findSimpleManagedList(userId);
        map.put("orgList", simpleOrganizationDTOList);
        return new ModelAndView("general/newProject", map);
    }

    @PostMapping("/general/projects")
    public ModelAndView generalCreateProject(@Validated ProjectForm projectForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
            throw new InvalidParameterException();
        }
        SimpleProjectDTO simpleProjectDTO = projectService.create(projectForm);
        return new ModelAndView("redirect:/general/projects/" + simpleProjectDTO.getPrjId());
    }

    @GetMapping("/general/projects/{id}")
    public ModelAndView generalDetailedProject(@PathVariable("id") Integer prjId) {
        ProjectDTO projectDTO = projectService.findOne(prjId);
        return new ModelAndView("redirect:/general/projects/" + prjId + "/" + projectDTO.getVersionId());
    }

    @GetMapping("/general/projects/{prjId}/{versionId}")
    public ModelAndView generalDetailedProjectVersion(Map<String, Object> map, HttpServletRequest request, @PathVariable("prjId") Integer prjId, @PathVariable("versionId") Integer versionId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        GeneralDetailedProjectDTO generalDetailedProjectDTO = projectService.findDetailedProjectByVersionId(prjId, versionId);
        map.put("prj", generalDetailedProjectDTO);
        List<VersionDTO> versionDTOList = projectService.findVersionList(prjId);
        map.put("vcsList", versionDTOList);
        if (userService.ifManagesPrj(userId, generalDetailedProjectDTO.getPrjId())) {
            map.put("manage", "Yes");
        }
        return new ModelAndView("general/detailedProject", map);
    }

    @GetMapping("/general/projects/{prjId}/{versionId}/actions/confirm")
    public ModelAndView generalDetailedProjectVersion(@PathVariable("prjId") Integer prjId, @PathVariable("versionId") Integer versionId) {
        projectService.updateVersion(prjId, versionId);
        return new ModelAndView("redirect:/general/projects/" + prjId + "/" + versionId);
    }

    @GetMapping("/general/projects/{prjId}/actions/request")
    public ModelAndView generalDetailedProjectVersion(@PathVariable("prjId") Integer prjId) {
        ProjectDTO projectDTO = projectService.findOne(prjId);
        if (projectDTO.getStatus().equals(ProjectStatusEnum.NOT_STARTED.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.REQUEST_CREATION.getCode());
            //TODO notify
        } else if (projectDTO.getStatus().equals(ProjectStatusEnum.IN_PROGRESS.getCode())) {
            projectService.updateStatus(prjId, ProjectStatusEnum.REQUEST_REIMBURSEMENT.getCode());
            //TODO notify
        } else {
            throw new ProjectStatusException();
        }
        return new ModelAndView("redirect:/general/projects/" + prjId);
    }

    @GetMapping("/general/projects/toCreate")
    public ModelAndView generalProjectsRequestCreation(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findRelatedOverviewByType(userId,
                ProjectStatusEnum.REQUEST_CREATION.getCode());
        map.put("prjList", projectOverviewDTOList);
        return new ModelAndView("general/projectsToCreate", map);
    }

    @GetMapping("/general/projects/inProgress")
    public ModelAndView generalProjectsInProgress(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findRelatedOverviewByType(userId,
                ProjectStatusEnum.IN_PROGRESS.getCode());
        map.put("prjList", projectOverviewDTOList);
        return new ModelAndView("general/projectsInProgress", map);
    }

    @GetMapping("/general/projects/toReimburse")
    public ModelAndView generalProjectsRequestReimbursement(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findRelatedOverviewByType(userId,
                ProjectStatusEnum.REQUEST_REIMBURSEMENT.getCode());
        map.put("prjList", projectOverviewDTOList);
        return new ModelAndView("general/projectsToReimburse", map);
    }

    @GetMapping("/general/projects/deferred")
    public ModelAndView generalProjectsDeferred(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findRelatedOverviewByType(userId,
                ProjectStatusEnum.DEFERRED.getCode());
        map.put("prjList", projectOverviewDTOList);
        return new ModelAndView("general/projectsDeferred", map);
    }

    @GetMapping("/general/projects/notStarted")
    public ModelAndView generalProjectsNotStarted(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.findRelatedOverviewByType(userId,
                ProjectStatusEnum.NOT_STARTED.getCode());
        map.put("prjList", projectOverviewDTOList);
        return new ModelAndView("general/projectsNotStarted", map);
    }

    @GetMapping("/general/projects/{id}/pages/update")
    public ModelAndView generalPageProjectBasicUpdate(Map<String, Object> map, HttpServletRequest request, @PathVariable("id") Integer prjId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        ProjectDTO projectDTO = projectService.findOne(prjId);
        map.put("prj", projectDTO);
        List<SimpleOrganizationDTO> simpleOrganizationDTOList = organizationService.findSimpleManagedList(userId);
        map.put("orgList", simpleOrganizationDTOList);
        return new ModelAndView("general/editProjectBasic", map);
    }

    @PostMapping("/general/projects/{prjId}/{versionId}/items")
    public ModelAndView generalAddItem(@Validated ItemForm itemForm, BindingResult bindingResult, @PathVariable("prjId") Integer prjId, @PathVariable("versionId") Integer versionId) {
        if (bindingResult.hasErrors()) {
            throw new InvalidParameterException();
        }
        itemService.createItem(versionId, itemForm);
        return new ModelAndView("redirect:/general/projects/" + prjId);
    }

    @PostMapping("/general/projects/{id}/versions")
    public ModelAndView generalProjectsVersionCreate(@PathVariable("id") Integer prjId, String tag){
        int versionId=projectService.addVersionByProject(prjId,tag);
        return new ModelAndView("redirect:/general/projects/"+prjId+"/"+versionId);
    }

    @GetMapping("/general/projects/{prjId}/{versionId}/items/{itemId}/actions/delete")
    public ModelAndView generalDeleteItem(@PathVariable("versionId") Integer versionId, @PathVariable("prjId") Integer prjId, @PathVariable("itemId") Integer itemId) {
        itemService.deleteItemFromDetail(versionId, itemId);
        return new ModelAndView("redirect:/general/projects/" + prjId);
    }

    @GetMapping("/general/projects/{prjId}/items/{itemId}/pages/assign")
    public ModelAndView generalPageItemAssign(Map<String, Object> map, HttpServletRequest request, @PathVariable("prjId") Integer prjId, @PathVariable("itemId") Integer itemId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        ItemDTO itemDTO = itemService.findOne(prjId, itemId);
        map.put("item", itemDTO);
        SimpleProjectDTO simpleProjectDTO = projectService.findSimpleOne(prjId);
        List<MemberDTO> memberDTOList = assignmentService.findMembersNotAssign(simpleProjectDTO.getOrgId(), itemId);
        map.put("memberList", memberDTOList);
        List<MemberDTO> receiverList = assignmentService.findAssigneeList(itemId);
        if (receiverList != null && receiverList.size() != 0) {
            map.put("receiverList", receiverList);
        }
        return new ModelAndView("general/itemAssign", map);
    }

    @PostMapping("/general/projects/{prjId}/items/{itemId}/actions/assign")
    public ModelAndView generalItemAssign(HttpServletRequest request, @PathVariable("itemId") Integer itemId, @PathVariable("prjId") Integer prjId, Integer assigneeId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        assignmentService.create(itemId, assigneeId, userId);
        return new ModelAndView("redirect:/general/projects/" + prjId);
    }

    @GetMapping("/general/projects/{prjId}/items/{itemId}")
    public ModelAndView generalDetailedItem(Map<String, Object> map, HttpServletRequest request, @PathVariable("itemId") Integer itemId, @PathVariable("prjId") Integer prjId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        DetailedItemDTO detailedItemDTO = itemService.findDetailedItem(prjId, itemId);
        map.put("item", detailedItemDTO);
        List<MemberDTO> receiverList = assignmentService.findAssigneeList(itemId);
        if (receiverList != null && receiverList.size() != 0) {
            map.put("receiverList", receiverList);
        }
        User user = userService.findReceiptSubmitter(detailedItemDTO.getRcptId());
        if (user != null) {
            map.put("submitter", user.getRealName());
        }
        return new ModelAndView("general/detailedItem", map);
    }

    @GetMapping("/general/assignments/pages/assigned/inProgress")
    public ModelAndView generalPageAssignedInProgress(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<SimpleAssignmentDTO> simpleAssignmentDTOList = assignmentService.findSimpleAssignedInStatus(userId, AssignmentStatusEnum.IN_PROGRESS.getCode());
        map.put("assignmentList", simpleAssignmentDTOList);
        return new ModelAndView("general/assignedInProgress", map);
    }

    @GetMapping("/general/assignments/pages/assigned/completed")
    public ModelAndView generalPageAssignedCompleted(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<SimpleAssignmentDTO> simpleAssignmentDTOList = assignmentService.findSimpleAssignedInStatus(userId, AssignmentStatusEnum.COMPLETED.getCode());
        map.put("assignmentList", simpleAssignmentDTOList);
        return new ModelAndView("general/assignedCompleted", map);
    }

    @GetMapping("/general/assignments/pages/received/inProgress")
    public ModelAndView generalPageReceivedInProgress(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<SimpleAssignmentDTO> simpleAssignmentDTOList = assignmentService.findSimpleReceivedInStatus(userId, AssignmentStatusEnum.IN_PROGRESS.getCode());
        map.put("assignmentList", simpleAssignmentDTOList);
        return new ModelAndView("general/receivedInProgress", map);
    }

    @GetMapping("/general/assignments/pages/received/completed")
    public ModelAndView generalPageReceivedCompleted(Map<String, Object> map, HttpServletRequest request) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        List<SimpleAssignmentDTO> simpleAssignmentDTOList = assignmentService.findSimpleReceivedInStatus(userId, AssignmentStatusEnum.COMPLETED.getCode());
        map.put("assignmentList", simpleAssignmentDTOList);
        return new ModelAndView("general/receivedCompleted", map);
    }

    @GetMapping("/general/assignments/{prjId}/{itemId}/pages/submit")
    public ModelAndView generalDetailedItemSubmit(Map<String, Object> map, HttpServletRequest request, @PathVariable("itemId") Integer itemId, @PathVariable("prjId") Integer prjId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);
        DetailedItemDTO detailedItemDTO = itemService.findDetailedItem(prjId, itemId);
        map.put("item", detailedItemDTO);
        List<MemberDTO> receiverList = assignmentService.findAssigneeList(itemId);
        if (receiverList != null && receiverList.size() != 0) {
            map.put("receiverList", receiverList);
        }
        User user = userService.findReceiptSubmitter(detailedItemDTO.getRcptId());
        if (user != null) {
            map.put("submitter", user.getRealName());
        }
        return new ModelAndView("general/detailedItemSubmit", map);
    }

    @PostMapping("/general/assignments/{prjId}/{itemId}/actions/submit")
    public ModelAndView generalItemSubmit(HttpServletRequest request, @PathVariable("itemId") Integer itemId, @PathVariable("prjId") Integer prjId, MultipartFile invoice, MultipartFile receipt, MultipartFile transaction, MultipartFile attachment) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        ReceiptForm receiptForm = new ReceiptForm();
        if (receipt != null) {
            String receiptUrl = FileUtil.imageUpload(receipt);
            receiptForm.setReceipt(receiptUrl);
        }
        if (invoice != null) {
            String invoiceUrl = FileUtil.imageUpload(invoice);
            receiptForm.setInvoice(invoiceUrl);
        }
        if (transaction != null) {
            String transactionUrl = FileUtil.imageUpload(transaction);
            receiptForm.setTransaction(transactionUrl);
        }
        if (attachment != null) {
            // TODO
        }
        assignmentService.assignmentSubmit(receiptForm, itemId, userId);
        return new ModelAndView("redirect:/general/assignments/" + prjId + "/" + itemId + "/pages/submit");
    }
}
