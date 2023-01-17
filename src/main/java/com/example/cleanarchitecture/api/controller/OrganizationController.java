package com.example.cleanarchitecture.api.controller;

import com.example.cleanarchitecture.api.mapper.EmployeeMapper;
import com.example.cleanarchitecture.api.mapper.OrganizationMapper;
import com.example.cleanarchitecture.api.dto.request.OrganizationRequest;
import com.example.cleanarchitecture.api.dto.response.EmployeeResponse;
import com.example.cleanarchitecture.api.dto.response.OrganizationResponse;
import com.example.cleanarchitecture.application.service.EmployeeService;
import com.example.cleanarchitecture.application.service.OrganizationService;
import com.example.cleanarchitecture.domain.model.Employee;
import com.example.cleanarchitecture.domain.model.Organization;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrganizationController {

  private final OrganizationService organizationService;
  private final EmployeeService employeeService;
  private final OrganizationMapper organizationMapper;
  private final EmployeeMapper employeeMapper;

  @PostMapping
  public OrganizationResponse create(@RequestBody @Valid OrganizationRequest request) {
    Organization organization = organizationService.create(request);

    return organizationMapper.mapToResponse(organization);
  }

  @GetMapping("{id}")
  public OrganizationResponse getOrganization(@PathVariable("id") Long id) {
    Organization organization = organizationService.getOrganization(id);

    return organizationMapper.mapToResponse(organization);
  }

  @GetMapping("/{id}/employees")
  public List<EmployeeResponse> getEmployees(@PathVariable("id") Long id) {
    List<Employee> employees = employeeService.getEmployees(id);

    return employees.stream()
        .map(employeeMapper::mapToResponse)
        .collect(Collectors.toList());
  }

}
