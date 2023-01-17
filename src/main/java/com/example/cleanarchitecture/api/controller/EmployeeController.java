package com.example.cleanarchitecture.api.controller;

import com.example.cleanarchitecture.api.mapper.EmployeeMapper;
import com.example.cleanarchitecture.api.dto.request.EmployeeRequest;
import com.example.cleanarchitecture.api.dto.response.EmployeeResponse;
import com.example.cleanarchitecture.application.service.EmployeeService;
import com.example.cleanarchitecture.domain.model.Employee;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

  private final EmployeeService employeeService;
  private final EmployeeMapper employeeMapper;

  @PostMapping
  public EmployeeResponse create(@RequestBody @Valid EmployeeRequest request) {
    Employee employee = employeeService.create(request);

    return employeeMapper.mapToResponse(employee);
  }

  @GetMapping("/{id}")
  public EmployeeResponse getEmployee(@PathVariable("id") Long id) {
    Employee employee = employeeService.getEmployee(id);

    return employeeMapper.mapToResponse(employee);
  }

}
