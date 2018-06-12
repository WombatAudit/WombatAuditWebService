package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Organization;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface OrganizationMapper {

    //TODO
//
//    List<Organization> getAll();
//
//    Organization selectByOrganizationId(Integer organizationId);
//
//    void insert(Organization organization);
//
//    void update(Organization organization);
//
//    void delete(Integer organizationId);
    @Select("select org_id from organization where co_id=#{coId}")
    List<Integer> selectOrgIdByCoId(int coId);
@Select("select org_id,co_id,name,description,budget,create_time from organization " +
        "where org_id=#{orgId}")
    Organization selectOrgByOrgId(int orgId);
}
