package com.example.cleanarchitecture.domain.usecase;

import com.example.cleanarchitecture.api.error.Error.NotFoundError;
import com.example.cleanarchitecture.api.error.ErrorEnum;
import com.example.cleanarchitecture.domain.model.Organization;
import com.example.cleanarchitecture.domain.repository.OrganizationRepository;

public abstract class OrganizationUseCase {

  protected abstract OrganizationRepository getOrganizationRepository();

  protected Organization save(Organization organization) {
    return getOrganizationRepository().save(organization);
  }

  protected void increaseEmployee(Long id) {
    Organization organization = getOrganizationRepository().findById(id)
        .orElseThrow(() -> new NotFoundError(ErrorEnum.organization_notFound));

    organization.setEmployeeCount(organization.getEmployeeCount() + 1);
    getOrganizationRepository().save(organization);
  }

  public Organization getOrganization(Long id) {
    return getOrganizationRepository().findById(id)
        .orElseThrow(() -> new NotFoundError(ErrorEnum.organization_notFound));
  }

}
