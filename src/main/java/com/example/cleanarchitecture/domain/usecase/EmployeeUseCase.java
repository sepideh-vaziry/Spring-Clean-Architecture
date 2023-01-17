package com.example.cleanarchitecture.domain.usecase;

import com.example.cleanarchitecture.api.error.Error.NotFoundError;
import com.example.cleanarchitecture.api.error.ErrorEnum;
import com.example.cleanarchitecture.domain.model.Employee;
import com.example.cleanarchitecture.domain.repository.EmployeeRepository;
import java.util.List;
import java.util.Random;

public abstract class EmployeeUseCase {

  protected abstract EmployeeRepository getEmployeeRepository();

  protected abstract OrganizationUseCase getOrganizationUseCase();

  protected Employee save(Employee employee) {
    employee.setPersonalCode(generatePersonalCode());
    employee = getEmployeeRepository().save(employee);

    getOrganizationUseCase().increaseEmployee(employee.getOrganizationId());

    return employee;
  }

  public Employee getEmployee(Long id) {
    return getEmployeeRepository().findById(id)
        .orElseThrow(() -> new NotFoundError(ErrorEnum.employee_notFound));
  }

  public List<Employee> getEmployees(Long organizationId) {
    return getEmployeeRepository().findAllByOrganizationId(organizationId);
  }

  private int generatePersonalCode() {
    Random r = new Random( System.currentTimeMillis() );
    return 10000 + r.nextInt(20000);
  }

}
