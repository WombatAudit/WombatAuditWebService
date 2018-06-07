package com.wombat.blw.Service.impl;

import com.wombat.blw.DTO.DetailedCompanyDTO;
import com.wombat.blw.DTO.SimpleCompanyDTO;
import com.wombat.blw.Form.CompanyForm;
import com.wombat.blw.Mapper.CompanyMapper;
import com.wombat.blw.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private WebSocket webSocket;

    @Override
    public List<SimpleCompanyDTO> getList() {
        //TODO
        return null;
    }

    @Override
    public DetailedCompanyDTO getDetail(Integer companyId) {
        //TODO
        return null;
    }

    @Override
    public void create(CompanyForm companyForm) {
        //TODO
    }

    @Override
    public void delete(Integer companyId) {
        //TODO
    }

    @Override
    public Integer getCoId(Integer userId) {
        //TODO
        return null;
    }
}
