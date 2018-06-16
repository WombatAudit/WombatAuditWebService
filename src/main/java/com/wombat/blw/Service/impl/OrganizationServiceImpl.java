package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Organization;
import com.wombat.blw.DO.Participate;
import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.OrganizationDTO;
import com.wombat.blw.Form.OrganizationForm;
import com.wombat.blw.Mapper.OrganizationMapper;
import com.wombat.blw.Mapper.ParticipateMapper;
import com.wombat.blw.Mapper.UserMapper;
import com.wombat.blw.Service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private ParticipateMapper participateMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void create(OrganizationForm organizationForm) {
        Organization organization = new Organization();
        organization.setName(organizationForm.getName());
        organization.setDescription(organizationForm.getDescription());
        organizationMapper.insert(organization);
    }

    @Override
    public void delete(Integer orgId) {
        organizationMapper.delete(orgId);
    }

    @Override
    public OrganizationDTO getOne(Integer orgId) {
        Organization organization;
        organization = organizationMapper.selectByOrganizationId(orgId);
        OrganizationDTO organizationDTO = new OrganizationDTO();
        BeanUtils.copyProperties(organization, organizationDTO);
        return organizationDTO;
    }

    @Override
    public List<MemberDTO> getMemberList(Integer orgId) {
        List<Participate> list = participateMapper.getSome(orgId);
        List<MemberDTO> list1 = new ArrayList<>();
        for (Participate c : list) {
            User user = new User();
            MemberDTO memberDTO = new MemberDTO();
            user = userMapper.findUserByUserId(c.getUserId());
            memberDTO.setRealName(user.getRealName());
            memberDTO.setUserId(user.getUserId());
            list1.add(memberDTO);
        }
        return list1;
    }

    @Override
    public void join(Integer orgId, Integer userId) {
        Participate participate = new Participate();
        participate.setOrganizationId(orgId);
        participate.setUserId(userId);
        participateMapper.insert(participate);
    }

    @Override
    public List<MemberDTO> getMemberListNotIn(Integer orgId) {
        List<Participate> list = participateMapper.getSomeReverse(orgId);
        List<MemberDTO> list1 = new ArrayList<>();
        for (Participate d : list) {
            User user = new User();
            MemberDTO memberDTO = new MemberDTO();
            user = userMapper.findUserByUserId(d.getUserId());
            memberDTO.setRealName(user.getRealName());
            memberDTO.setUserId(user.getUserId());
            list1.add(memberDTO);
        }
        return list1;
    }

    @Override
    public void remove(Integer orgId, Integer userId) {
        participateMapper.delete(orgId, userId);
    }

    @Override
    public List<OrganizationDTO> findOrgsByCoId(Integer coId) {
        List<Organization> organizationList = organizationMapper.findAllOrgsByCoId(coId);
        return organizationList.stream().map(e -> new OrganizationDTO(e.getOrganizationId(), e.getName(), e.getDescription(),
                e.getBudget(), e.getCreateTime())).collect(Collectors.toList());
    }
}
