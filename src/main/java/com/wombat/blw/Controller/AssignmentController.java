package com.wombat.blw.Controller;

import com.wombat.blw.DTO.DetailedAssignmentDTO;
import com.wombat.blw.DTO.SimpleAssignmentDTO;
import com.wombat.blw.Enum.ErrorCode;
import com.wombat.blw.Form.ReceiptForm;
import com.wombat.blw.Service.AssignmentService;
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
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/projects/{id}/assignments")
    public ModelAndView getListFromPrj(Map<String, Object> map, @PathVariable("id") Integer prjId) {
        List<SimpleAssignmentDTO> simpleAssignmentDTOList = assignmentService.getListByPrjId(prjId);
        map.put("asgnList", simpleAssignmentDTOList);
        return new ModelAndView("assignment/list", map);
    }

    @GetMapping("/assignments")
    public ModelAndView getList(HttpServletRequest request, Map<String, Object> map) {
        Integer userId = UserUtil.getUserId(request, redisTemplate);
        List<SimpleAssignmentDTO> simpleAssignmentDTOList = assignmentService.getListByUserId(userId);
        map.put("asgnList", simpleAssignmentDTOList);
        return new ModelAndView("assignment/list", map);
    }

    @GetMapping("/assignments/{id}")
    public ModelAndView getDetail(Map<String, Object> map, @PathVariable("id") Integer itemId) {
        DetailedAssignmentDTO detailedAssignmentDTO = assignmentService.getDetail(itemId);
        map.put("asgn", detailedAssignmentDTO);
        return new ModelAndView("assignment/detail", map);
    }

    @PostMapping("/assignments/actions/submit")
    public ModelAndView submit(Map<String, Object> map, Integer itemId, @Validated ReceiptForm receiptForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }
        assignmentService.updateReceipt(itemId, receiptForm);
        map.put("msg", ErrorCode.SUBMIT_SUCCESS);
        map.put("url", "/wombataudit/assignments/" + itemId);
        return new ModelAndView("common/success", map);
    }
}
