package com.example.cleanarchitecture.api.error;

import org.springframework.http.HttpStatus;

public abstract class Error extends RuntimeException {

  private ErrorEnum errorEnum;
  private HttpStatus httpStatus;

  public Error() {
  }

  public Error(ErrorEnum errorsEnum) {
    this.errorEnum = errorsEnum;
    this.httpStatus = HttpStatus.valueOf(errorsEnum.getStatus());
  }

  public Error(String message) {
    super(message);
  }

  public Error(ErrorEnum errorEnum, HttpStatus httpStatus) {
    this.errorEnum = errorEnum;
    this.httpStatus = httpStatus;
  }

  public Error(String message, HttpStatus httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public String getErrorMessageKey() {
    if (errorEnum != null) {
      return errorEnum.getMessageKey();
    }

    return null;
  }

  public int getDeveloperCode() {
    if (errorEnum != null) {
      return errorEnum.getCode();
    }

    else return 0;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public static class NotImplementedError extends Error {
    public NotImplementedError() {
      super("Not Implemented", HttpStatus.NOT_IMPLEMENTED);
    }

    public NotImplementedError(ErrorEnum errorEnum) {
      super(errorEnum, HttpStatus.NOT_IMPLEMENTED);
    }
  }

  public static class RequiredFieldError extends Error {

    public RequiredFieldError(String fieldName) {
      super(String.format("Please insert %s", fieldName), HttpStatus.BAD_REQUEST);
    }

    public RequiredFieldError(ErrorEnum errorEnum) {
      super(errorEnum, HttpStatus.BAD_REQUEST);
    }

  }

  public static class BadRequestError extends Error {

    public BadRequestError() {
      super(ErrorEnum.general_badRequest, HttpStatus.BAD_REQUEST);
    }

    public BadRequestError(String message) {
      super(message, HttpStatus.BAD_REQUEST);
    }

    public BadRequestError(ErrorEnum errorEnum) {
      super(errorEnum, HttpStatus.BAD_REQUEST);
    }

  }

  public static class AccessDeniedError extends Error {

    public AccessDeniedError() {
      super(ErrorEnum.access_denied, HttpStatus.FORBIDDEN);
    }

    public AccessDeniedError(String message) {
      super(message, HttpStatus.FORBIDDEN);
    }

    public AccessDeniedError(ErrorEnum errorEnum) {
      super(errorEnum, HttpStatus.FORBIDDEN);
    }

  }

  public static class UnauthorizedError extends Error {

    public UnauthorizedError() {
      super(ErrorEnum.unauthorized, HttpStatus.UNAUTHORIZED);
    }
  }

  public static class NotFoundError extends Error {

    public NotFoundError() {
      super(ErrorEnum.general_notFound, HttpStatus.NOT_FOUND);
    }

    public NotFoundError(String message) {
      super(message, HttpStatus.NOT_FOUND);
    }

    public NotFoundError(ErrorEnum errorEnum) {
      super(errorEnum, HttpStatus.NOT_FOUND);
    }

  }

}