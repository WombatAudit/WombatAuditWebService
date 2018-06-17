package com.wombat.blw.Controller;

import com.wombat.blw.DTO.*;
import com.wombat.blw.Exception.InvalidParameterException;
import com.wombat.blw.Form.OrganizationForm;
import com.wombat.blw.Form.ProjectForm;
import com.wombat.blw.Service.OrganizationService;
import com.wombat.blw.Service.ProjectService;
import com.wombat.blw.Service.UserService;
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
        List<MemberDTO> memberDTOList = organizationService.getMemberList(orgId);
        List<SimpleProjectDTO> simpleProjectDTOList = projectService.findActiveList(orgId);
        map.put("org", organizationDTO);
        map.put("memberList", memberDTOList);
        map.put("projectList", simpleProjectDTOList);
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
    public ModelAndView generalDetailedProject(Map<String, Object> map, HttpServletRequest request, @PathVariable("id") Integer prjId) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        SimpleUserDTO simpleUserDTO = userService.findSimpleOne(userId);
        map.put("user", simpleUserDTO);

        return new ModelAndView("general/detailedProject", map);
    }
}
