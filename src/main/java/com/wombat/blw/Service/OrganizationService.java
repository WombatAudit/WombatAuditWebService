package com.wombat.blw.Service;

import com.wombat.blw.DTO.MemberDTO;
import com.wombat.blw.DTO.OrganizationDTO;
import com.wombat.blw.Form.OrganizationForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrganizationService {

    List<OrganizationDTO> findOrgsByCoId(Integer coId);

    void create(OrganizationForm organizationForm);

    void delete(Integer orgId);

    OrganizationDTO getOne(Integer orgId);

    List<MemberDTO> getMemberList(Integer orgId);

    void join(Integer orgId, Integer userId);

    List<MemberDTO> getMemberListNotIn(Integer orgId);

    void remove(Integer orgId, Integer userId);
}
