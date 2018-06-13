package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Company;
import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.DetailedCompanyDTO;
import com.wombat.blw.DTO.SimpleCompanyDTO;
import com.wombat.blw.Form.CompanyForm;
import com.wombat.blw.Mapper.CompanyMapper;
import com.wombat.blw.Mapper.UserMapper;
import com.wombat.blw.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private UserMapper userMapper;

//    @Autowired
//    private WebSocket webSocket;

    @Override
    public List<SimpleCompanyDTO> getList() {
        List<Company> list = companyMapper.getAll();
        List<SimpleCompanyDTO> newList = new ArrayList<>();
        for (Company c : list) {
            SimpleCompanyDTO s = new SimpleCompanyDTO();
            s.setCompanyId(c.getCompanyId());
            s.setName(c.getName());
            newList.add(s);
        }
        return newList;
    }

    @Override
    public DetailedCompanyDTO getDetail(Integer companyId) {
        DetailedCompanyDTO detailedCompanyDTO = new DetailedCompanyDTO();
        Company company = companyMapper.selectByCompanyId(companyId);
        detailedCompanyDTO.setAccount(company.getAccount());
        detailedCompanyDTO.setAccountBank(company.getAccountBank());
        detailedCompanyDTO.setCompanyId(company.getCompanyId());
        detailedCompanyDTO.setName(company.getName());
        detailedCompanyDTO.setDescription(company.getDescription());
        detailedCompanyDTO.setTaxId(company.getTaxId());
        return detailedCompanyDTO;
    }

    @Override
    public void create(CompanyForm companyForm) {
        Company company = new Company();
        company.setAccount(companyForm.getAccount());
        company.setAccountBank(companyForm.getAccountBank());
        company.setDescription(companyForm.getDescription());
        company.setTaxId(companyForm.getTaxId());
        company.setName(companyForm.getName());
        companyMapper.insert(company);
    }

    @Override
    public void delete(Integer companyId) {
        companyMapper.delete(companyId);
    }

    @Override
    public Integer getCoId(Integer userId) {
        User user;
        user = userMapper.findUserByUserId(userId);
        int CoId;
        CoId = user.getCompanyId();
        return CoId;
    }
}
