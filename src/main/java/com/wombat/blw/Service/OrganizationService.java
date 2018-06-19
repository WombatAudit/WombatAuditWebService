package com.wombat.blw.Service;

import com.wombat.blw.DO.User;
import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.OrganizationDTO;
import com.wombat.blw.DTO.SimpleOrganizationDTO;
import com.wombat.blw.Form.OrganizationForm;

import java.util.List;

public interface OrganizationService {

    List<OrganizationDTO> findCompanyOrgs(Integer coId);

    List<OrganizationDTO> findJoinedOrgs(Integer userId);

    OrganizationDTO create(Integer userId, OrganizationForm organizationForm);

    void delete(Integer orgId);

    OrganizationDTO getOne(Integer orgId);

    List<MemberDTO> findMembersInOrg(Integer orgId);

    void join(Integer orgId, Integer userId);

    List<MemberDTO> findMemberListNotIn(Integer coId, Integer orgId);

    void remove(Integer orgId, Integer userId);

    List<SimpleOrganizationDTO> findSimpleManagedList(Integer userId);

    User findManager(Integer orgId);

    String findOrgName(Integer orgId);
}
