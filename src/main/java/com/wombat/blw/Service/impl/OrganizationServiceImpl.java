package com.wombat.blw.Service.impl;

import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.OrganizationDTO;
import com.wombat.blw.Form.OrganizationForm;
import com.wombat.blw.Service.OrganizationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Override
    public Page<OrganizationDTO> getOrgPage(Integer userId, Pageable pageable) {
        //TODO
        return null;
    }

    @Override
    public void create(OrganizationForm organizationForm) {
        //TODO
    }

    @Override
    public void delete(Integer orgId) {
        //TODO
    }

    @Override
    public OrganizationDTO getOne(Integer orgId) {
        //TODO
        return null;
    }

    @Override
    public List<MemberDTO> getMemberList(Integer orgId) {
        //TODO
        return null;
    }

    @Override
    public void join(Integer orgId, Integer userId) {
        //TODO
    }

    @Override
    public List<MemberDTO> getMemberListNotIn(Integer orgId) {
        //TODO
        return null;
    }

    @Override
    public void remove(Integer orgId, Integer userId) {
        //TODO
    }
}
