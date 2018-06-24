package com.wombat.blw.Controller;

import com.wombat.blw.Enum.ErrorCode;
import com.wombat.blw.Form.CompanyForm;
import com.wombat.blw.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/companies")
    public ModelAndView create(Map<String, Object> map, @Validated CompanyForm companyForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }
        companyService.create(companyForm);
        map.put("url", "/wombataudit/");
        map.put("msg", ErrorCode.CREATE_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }

    @PostMapping("/companies/actions/delete")
    public ModelAndView delete(Map<String, Object> map, Integer companyId) {
        companyService.delete(companyId);
        map.put("url", "/wombataudit/");
        map.put("msg", ErrorCode.DELETE_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }
}