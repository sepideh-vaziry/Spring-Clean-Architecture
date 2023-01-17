package com.example.cleanarchitecture.api.dto.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponse implements Serializable {

  private Long id;
  private String name;
  private int employeeCount;

}
