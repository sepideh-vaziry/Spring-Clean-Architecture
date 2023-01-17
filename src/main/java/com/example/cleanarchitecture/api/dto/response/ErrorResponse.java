package com.example.cleanarchitecture.api.dto.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {

  private String message;
  private int statusCode;
  private int developerCode;

}
