package com.wombat.blw.Controller;

import com.wombat.blw.DTO.SimpleCompanyDTO;
import com.wombat.blw.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("signIn");
    }

    @RequestMapping("/pages/signUp")
    public ModelAndView signUp(Map<String, Object> map) {
        List<SimpleCompanyDTO> simpleCompanyDTOList = companyService.getList();
        map.put("companyList", simpleCompanyDTOList);
        return new ModelAndView("signUp", map);
    }

    @RequestMapping("/pages/signIn")
    public ModelAndView signIn() {
        return new ModelAndView("signIn");
    }

    @RequestMapping("/pages/companyRegister")
    public ModelAndView registerCompany() {
        return new ModelAndView("companyRegister");
    }
}
