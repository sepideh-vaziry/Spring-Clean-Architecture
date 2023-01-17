package com.example.cleanarchitecture.application.service;

import com.example.cleanarchitecture.api.dto.request.OrganizationRequest;
import com.example.cleanarchitecture.domain.model.Organization;
import com.example.cleanarchitecture.domain.repository.OrganizationRepository;
import com.example.cleanarchitecture.domain.usecase.OrganizationUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrganizationService extends OrganizationUseCase {

  @Getter
  private final OrganizationRepository organizationRepository;

  public Organization create(OrganizationRequest request) {
    Organization organization = Organization.builder()
        .name(request.getName())
        .employeeCount(0)
        .build();

    return save(organization);
  }

}
