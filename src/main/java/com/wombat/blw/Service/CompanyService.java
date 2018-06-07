package com.wombat.blw.Service;

import com.wombat.blw.DTO.DetailedCompanyDTO;
import com.wombat.blw.DTO.SimpleCompanyDTO;
import com.wombat.blw.Form.CompanyForm;

import java.util.List;

public interface CompanyService {

    List<SimpleCompanyDTO> getList();

    DetailedCompanyDTO getDetail(Integer companyId);

    void create(CompanyForm companyForm);

    void delete(Integer companyId);

    Integer getCoId(Integer userId);
}
