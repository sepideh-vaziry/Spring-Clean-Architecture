package com.example.cleanarchitecture.api.mapper;

import com.example.cleanarchitecture.api.dto.response.EmployeeResponse;
import com.example.cleanarchitecture.domain.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

  public EmployeeResponse mapToResponse(Employee employee) {
    return EmployeeResponse.builder()
        .id(employee.getId())
        .name(employee.getName())
        .personalCode(employee.getPersonalCode())
        .organizationId(employee.getOrganizationId())
        .build();
  }

}
