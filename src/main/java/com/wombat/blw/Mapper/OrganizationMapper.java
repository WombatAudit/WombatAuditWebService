package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Organization;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Select;
=======
>>>>>>> df37c87ba64d7a4a4d212c0e07bef85d08f62a5d
import org.springframework.stereotype.Component;

import java.util.List;
<<<<<<< HEAD
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
=======

>>>>>>> df37c87ba64d7a4a4d212c0e07bef85d08f62a5d
}
