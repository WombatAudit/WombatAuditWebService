package com.wombat.blw.Controller;

import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.OrganizationDTO;
import com.wombat.blw.DTO.SimpleProjectDTO;
import com.wombat.blw.Enum.ErrorCode;
import com.wombat.blw.Form.OrganizationForm;
import com.wombat.blw.Service.OrganizationService;
import com.wombat.blw.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/organizations/{orgId}")
    public ModelAndView getOrgDetail(Map<String, Object> map, @PathVariable("orgId") Integer orgId) {
        OrganizationDTO organizationDTO = organizationService.getOne(orgId);
        List<MemberDTO> memberDTOList = organizationService.getMemberList(orgId);
        List<SimpleProjectDTO> simpleProjectDTOList = projectService.getList(orgId);
        map.put("org", organizationDTO);
        map.put("memberList", memberDTOList);
        map.put("projectList", simpleProjectDTOList);
        return new ModelAndView("organization/detail", map);
    }

    @PutMapping("/organizations")
    public ModelAndView create(@Validated OrganizationForm organizationForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }
        return new ModelAndView("organization/list");
    }

    @PutMapping("/organizations/actions/invite")
    public ModelAndView invite(Integer orgId, Integer userId) {
        organizationService.join(orgId, userId);
        return new ModelAndView("redirect:/organizations/{orgId}");
    }

    @PostMapping("organizations/actions/remove")
    public ModelAndView remove(Integer useId, Integer orgId) {
        organizationService.remove(orgId, useId);
        return new ModelAndView("redirect:/organizations/{orgId}");
    }

    @PostMapping("organizations/actions/delete")
    public ModelAndView delete(Map<String, Object> map, Integer orgId) {
        organizationService.delete(orgId);
        map.put("url", "/wombataudit/organizations");
        map.put("msg", ErrorCode.DELETE_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }
}
