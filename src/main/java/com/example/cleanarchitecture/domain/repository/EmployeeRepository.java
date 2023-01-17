package com.example.cleanarchitecture.domain.repository;

import com.example.cleanarchitecture.domain.model.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

  Employee save(Employee employee);

  Optional<Employee> findById(Long id);

  List<Employee> findAllByOrganizationId(Long organizationId);

}
