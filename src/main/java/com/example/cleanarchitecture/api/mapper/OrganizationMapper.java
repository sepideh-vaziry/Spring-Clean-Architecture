package com.example.cleanarchitecture.api.mapper;

import com.example.cleanarchitecture.api.dto.response.OrganizationResponse;
import com.example.cleanarchitecture.domain.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

  public OrganizationResponse mapToResponse(Organization organization) {
    return OrganizationResponse.builder()
        .id(organization.getId())
        .name(organization.getName())
        .employeeCount(organization.getEmployeeCount())
        .build();
  }

}
