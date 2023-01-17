package com.example.cleanarchitecture.infrastructure.repository;

import com.example.cleanarchitecture.domain.model.Employee;
import com.example.cleanarchitecture.domain.repository.EmployeeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee, Long>, EmployeeRepository {

}
