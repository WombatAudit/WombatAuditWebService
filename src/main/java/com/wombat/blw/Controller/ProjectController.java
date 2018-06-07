package com.wombat.blw.Controller;

import com.wombat.blw.DTO.DetailedProjectDTO;
import com.wombat.blw.DTO.ProjectOverviewDTO;
import com.wombat.blw.DTO.SimpleProjectDTO;
import com.wombat.blw.Enum.ErrorCode;
import com.wombat.blw.Form.ProjectForm;
import com.wombat.blw.Service.CompanyService;
import com.wombat.blw.Service.ProjectService;
import com.wombat.blw.Util.UserUtil;
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
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/projects")
    public ModelAndView getList(Map<String, Object> map, Integer orgId) {
        List<SimpleProjectDTO> simpleProjectDTOList = projectService.getList(orgId);
        map.put("prjList", simpleProjectDTOList);
        return new ModelAndView("project/list", map);
    }

    @GetMapping("/projects/{id}")
    public ModelAndView getDetail(Map<String, Object> map, @PathVariable("id") Integer prjId) {
        DetailedProjectDTO detailedProjectDTO = projectService.getDetail(prjId);
        map.put("prj", detailedProjectDTO);
        return new ModelAndView("project/detail", map);
    }

    @PostMapping("/projects")
    public ModelAndView create(Map<String, Object> map, @Validated ProjectForm projectForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }
        projectService.create(projectForm);
        map.put("msg", ErrorCode.CREATE_SUCCESS);
        map.put("url", "/wombataudit/projects");
        return new ModelAndView("common/success", map);
    }

    @PostMapping("/projects/actions/delete")
    public ModelAndView delete(Map<String, Object> map, Integer prjId) {
        projectService.delete(prjId);
        map.put("msg", ErrorCode.DELETE_SUCCESS);
        map.put("url", "/wombataudit/projects");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/projects/status/completed")
    public ModelAndView getCompletedList(HttpServletRequest request, Map<String, Object> map) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        Integer coId = companyService.getCoId(userId);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.getCompletedList(coId);
        map.put("prjList", projectOverviewDTOList);
        return new ModelAndView("project/overview-completed", map);
    }

    @GetMapping("/projects/status/request-creation")
    public ModelAndView getRequestCreationList(HttpServletRequest request, Map<String, Object> map) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        Integer coId = companyService.getCoId(userId);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.getRequestCreationList(coId);
        map.put("prjList", projectOverviewDTOList);
        return new ModelAndView("project/overview-request-creation", map);
    }

    @GetMapping("/projects/status/request-reimbursement")
    public ModelAndView getReimbursementList(HttpServletRequest request, Map<String, Object> map) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        Integer coId = companyService.getCoId(userId);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.getRequestReimbursementList(coId);
        map.put("prjList", projectOverviewDTOList);
        return new ModelAndView("project/overview-request-reimbursement", map);
    }

    @GetMapping("/projects/status/in-progress")
    public ModelAndView getInProgressList(HttpServletRequest request, Map<String, Object> map) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        Integer coId = companyService.getCoId(userId);
        List<ProjectOverviewDTO> projectOverviewDTOList = projectService.getInProgressList(coId);
        map.put("prjList", projectOverviewDTOList);
        return new ModelAndView("project/overview-in-progress", map);
    }

    @PostMapping("/projects/actions/pass-creation")
    public ModelAndView passCreation(Map<String, Object> map, Integer prjId, String feedback) {
        projectService.passCreation(prjId);
        map.put("msg", ErrorCode.REVIEW_SUCCESS);
        map.put("url", "");
        return new ModelAndView("common/success", map);
    }

    @PostMapping("/projects/actions/pass-reimbursement")
    public ModelAndView passReimbursement(Map<String, Object> map, Integer prjId, String feedback) {
        projectService.passReimbursement(prjId);
        map.put("msg", ErrorCode.REVIEW_SUCCESS);
        map.put("url", "");
        return new ModelAndView("common/success", map);
    }

    @PostMapping("/projects/actions/reject-creation")
    public ModelAndView rejectCreation(Map<String, Object> map, Integer prjId, String feedback) {
        projectService.rejectCreation(prjId);
        return new ModelAndView("common/success", map);
    }

    @PostMapping("/projects/actions/reject-reimbursement")
    public ModelAndView rejectReimbursement(Map<String, Object> map, Integer prjId, String feedback) {
        projectService.passReimbursement(prjId);
        return new ModelAndView("common/success", map);
    }
}
