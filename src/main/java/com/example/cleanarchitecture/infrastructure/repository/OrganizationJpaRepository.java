package com.example.cleanarchitecture.infrastructure.repository;

import com.example.cleanarchitecture.domain.model.Organization;
import com.example.cleanarchitecture.domain.repository.OrganizationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationJpaRepository extends JpaRepository<Organization, Long>,
    OrganizationRepository {

}
