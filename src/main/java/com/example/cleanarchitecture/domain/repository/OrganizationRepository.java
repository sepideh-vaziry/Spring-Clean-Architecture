package com.example.cleanarchitecture.domain.repository;

import com.example.cleanarchitecture.domain.model.Organization;
import java.util.List;
import java.util.Optional;

public interface OrganizationRepository {

  Organization save(Organization organization);

  Optional<Organization> findById(Long id);

  List<Organization> findAll();

}
