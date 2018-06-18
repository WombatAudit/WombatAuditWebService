package com.wombat.blw.Service.impl;

import com.wombat.blw.DO.Organization;
import com.wombat.blw.DO.Participate;
import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.OrganizationDTO;
import com.wombat.blw.DTO.SimpleOrganizationDTO;
import com.wombat.blw.Enum.OrgRoleEnum;
import com.wombat.blw.Exception.BaseException;
import com.wombat.blw.Form.OrganizationForm;
import com.wombat.blw.Mapper.OrganizationMapper;
import com.wombat.blw.Mapper.ParticipateMapper;
import com.wombat.blw.Mapper.UserMapper;
import com.wombat.blw.Service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public OrganizationDTO create(Integer userId, OrganizationForm organizationForm) {
        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationForm, organization);
        organization.setBudget(new BigDecimal(10000));
        organizationMapper.create(organization);
        Participate participate = new Participate();
        participate.setOrgId(organization.getOrganizationId());
        participate.setUserId(userId);
        participate.setRole(OrgRoleEnum.MANAGER.getCode());
        participateMapper.insert(participate);
        Organization newOrganization = organizationMapper.selectOrgByOrgId(organization.getOrganizationId());
        OrganizationDTO organizationDTO = new OrganizationDTO();
        BeanUtils.copyProperties(newOrganization, organizationDTO);
        return organizationDTO;
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
        User user = findManager(orgId);
        organizationDTO.setManager(user.getRealName());
        return organizationDTO;
    }

    @Override
    public List<MemberDTO> findMembersInOrg(Integer orgId) {
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
        participate.setOrgId(orgId);
        participate.setUserId(userId);
        participate.setRole(OrgRoleEnum.MEMBER.getCode());
        participateMapper.insert(participate);
    }

    @Override
    public List<MemberDTO> findMemberListNotIn(Integer coId, Integer orgId) {
        List<User> userList = userMapper.findGeneralMembersOfCompanyNotIn(coId, orgId);
        return userList.stream().map(e -> new MemberDTO(e.getUserId(), e.getRealName())).collect(Collectors.toList());
    }

    @Override
    public void remove(Integer orgId, Integer userId) {
        participateMapper.delete(orgId, userId);
    }

    @Override
    public List<OrganizationDTO> findCompanyOrgs(Integer coId) {
        List<Organization> organizationList = organizationMapper.findAllOrgsByCoId(coId);
        return organizationList.stream().map(e -> new OrganizationDTO(e.getOrganizationId(), e.getName(), e.getDescription(),
                e.getBudget(), e.getCreateTime())).collect(Collectors.toList());
    }

    @Override
    public List<OrganizationDTO> findJoinedOrgs(Integer userId) {
        List<Organization> organizationList = organizationMapper.findOrgsByUserId(userId);
        return organizationList.stream().map(e -> new OrganizationDTO(e.getOrganizationId(), e.getName(), e.getDescription(),
                e.getBudget(), e.getCreateTime())).collect(Collectors.toList());
    }

    @Override
    public List<SimpleOrganizationDTO> findSimpleManagedList(Integer userId) {
        List<Organization> organizationList = organizationMapper.findJoinedOrgWithRole(userId, OrgRoleEnum.MANAGER.getCode());
        return organizationList.stream().map(e -> new SimpleOrganizationDTO(e.getOrganizationId(), e.getName())).collect(Collectors.toList());
    }

    @Override
    public User findManager(Integer orgId) {
        List<Participate> participateList = participateMapper.findRoleOfOrg(orgId, OrgRoleEnum.MANAGER.getCode());
        if (participateList.size() != 1) {
            throw new BaseException();
        }
        return userMapper.findUserByUserId(participateList.get(0).getUserId());
    }
}
