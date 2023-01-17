package com.example.cleanarchitecture.application.service;

import com.example.cleanarchitecture.api.dto.request.EmployeeRequest;
import com.example.cleanarchitecture.domain.model.Employee;
import com.example.cleanarchitecture.domain.repository.EmployeeRepository;
import com.example.cleanarchitecture.domain.usecase.EmployeeUseCase;
import com.example.cleanarchitecture.domain.usecase.OrganizationUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService extends EmployeeUseCase {

  @Getter
  private final EmployeeRepository employeeRepository;

  @Getter
  private final OrganizationUseCase organizationUseCase;

  public Employee create(EmployeeRequest request) {
    Employee employee = Employee.builder()
        .name(request.getName())
        .organizationId(request.getOrganizationId())
        .build();

    return save(employee);
  }

}
