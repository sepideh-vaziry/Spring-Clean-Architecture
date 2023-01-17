package com.example.cleanarchitecture.api.error;

import com.example.cleanarchitecture.api.dto.response.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

  private static final Map<String, String> CONSTRAINS_MAP = Map.of();

  private static final Map<String, Integer> CONSTRAINS_DEVELOPER_CODE_MAP = Map.of();

  private final MessageSource messageSource;

  public ErrorHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<ErrorResponse> handleException(Exception exception) {
    String message = exception.getMessage();
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    int developerCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    if (exception instanceof Error) {
      String messageKey = ((Error) exception).getErrorMessageKey();
      developerCode = ((Error) exception).getDeveloperCode();

      if (messageKey != null) {
        message = getMessage(messageKey);
      }

      if (((Error) exception).getHttpStatus() != null) {
        httpStatus = ((Error) exception).getHttpStatus();
      }
    } else {
      LOGGER.error("Exception: ", exception);
    }

    ErrorResponse body = new ErrorResponse(message, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
  public ResponseEntity<Object> handleIllegalArgumentException(Exception exception) {
    String message = exception.getMessage();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    int developerCode = HttpStatus.BAD_REQUEST.value();

    ErrorResponse body = new ErrorResponse(message, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(value = {DataAccessException.class})
  public ResponseEntity<Object> handleDataAccessException(DataAccessException exception) {
    String message = exception.getMessage();
    HttpStatus httpStatus = HttpStatus.FORBIDDEN;
    int developerCode = HttpStatus.FORBIDDEN.value();

    ErrorResponse body = new ErrorResponse(message, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(value = {EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(Exception exception) {
    String message = exception.getMessage();
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    int developerCode = HttpStatus.NOT_FOUND.value();

    ErrorResponse body = new ErrorResponse(message, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(value = {UnsupportedEncodingException.class})
  public ResponseEntity<Object> handleUnsupportedEncodingException(Exception exception) {
    String message = exception.getMessage();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    int developerCode = HttpStatus.BAD_REQUEST.value();

    ErrorResponse body = new ErrorResponse(message, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(value = {IOException.class})
  public ResponseEntity<Object> handleIOException(Exception exception) {
    String message = exception.getMessage();
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    int developerCode = HttpStatus.INTERNAL_SERVER_ERROR.value();

    ErrorResponse body = new ErrorResponse(message, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationExceptions(
      MethodArgumentNotValidException exception
  ) {
    Map<String, String> errorMap = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errorMap.put(fieldName, errorMessage);
    });

    String json = null;
    try {
      json = new ObjectMapper().writeValueAsString(errorMap);
    } catch (JsonProcessingException e) {
      LOGGER.error("Exception", e);
    }

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    int developerCode = HttpStatus.BAD_REQUEST.value();

    ErrorResponse body = new ErrorResponse(json, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<Object> handleMissingParameterExceptions(
      MissingServletRequestParameterException exception
  ) {
    String message = exception.getMessage();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    int developerCode = HttpStatus.BAD_REQUEST.value();

    ErrorResponse body = new ErrorResponse(message, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(BindException.class)
  public ResponseEntity<Object> handleBindExceptions(BindException exception) {
    Map<String, String> errorMap = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errorMap.put(fieldName, errorMessage);
    });

    String json = null;
    try {
      json = new ObjectMapper().writeValueAsString(errorMap);
    } catch (JsonProcessingException e) {
      LOGGER.error("Exception", e);
    }

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    int developerCode = HttpStatus.BAD_REQUEST.value();

    ErrorResponse body = new ErrorResponse(json, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<Object> handleDataIntegrity(DataIntegrityViolationException exception) {
    String message = getMessage("error_general_duplicate");
    HttpStatus httpStatus = HttpStatus.CONFLICT;
    int developerCode = 40901;

    if (exception.getMessage() != null) {
      String exceptionMessage = exception.getMessage().toLowerCase();
      for (Map.Entry<String, String> entry : CONSTRAINS_MAP.entrySet()) {
        if (exceptionMessage.contains(entry.getKey())) {
          message = getMessage(entry.getValue());
          developerCode = CONSTRAINS_DEVELOPER_CODE_MAP.get(entry.getKey());
        }
      }
    }

    ErrorResponse body = new ErrorResponse(message, httpStatus.value(), developerCode);

    return new ResponseEntity<>(body, httpStatus);
  }

  private String getMessage(String code) {
    return messageSource.getMessage(code, new String[]{}, Locale.ENGLISH);
  }

}
