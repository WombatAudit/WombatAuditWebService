package com.wombat.blw.Mapper;

import com.wombat.blw.DO.Organization;

import java.util.List;

public interface OrganizationMapper {

    //TODO

    List<Organization> getAll();

    Organization selectByOrganizationId(Integer organizationId);

    void insert(Organization organization);

    void update(Organization organization);

    void delete(Integer organizationId);
}
