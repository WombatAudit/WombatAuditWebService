package com.wombat.blw.Controller;

import com.wombat.blw.DO.Company;
import com.wombat.blw.DTO.DetailedCompanyDTO;
import com.wombat.blw.Enum.ErrorCode;
import com.wombat.blw.Form.CompanyForm;
import com.wombat.blw.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public ModelAndView getList(Map<String, Object> map) {
        List<Company> simpleCompanyDTOList = companyService.getList();
        map.put("companyList", simpleCompanyDTOList);
        return new ModelAndView("company/list", map);
    }

    @PostMapping("/companies")
    public ModelAndView create(Map<String, Object> map, @Validated CompanyForm companyForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO
        }
        companyService.create(companyForm);
        map.put("url", "/wombataudit/companies");
        map.put("msg", ErrorCode.CREATE_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }

    @PostMapping("/companies/actions/delete")
    public ModelAndView delete(Map<String, Object> map, Integer companyId) {
        companyService.delete(companyId);
        map.put("url", "/wombataudit/companies");
        map.put("msg", ErrorCode.DELETE_SUCCESS.getMessage());
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/companies/{id}")
    public ModelAndView viewDetail(Map<String, Object> map, @PathVariable("id") Integer id) {
        DetailedCompanyDTO detailedCompanyDTO = companyService.getDetail(id);
        map.put("co", detailedCompanyDTO);
        return new ModelAndView("/company/detail", map);
    }
}
