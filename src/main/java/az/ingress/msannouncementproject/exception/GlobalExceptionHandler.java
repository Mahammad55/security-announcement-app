package az.ingress.msannouncementproject.exception;

import az.ingress.msannouncementproject.validation.ValidationError;
import az.ingress.msannouncementproject.dto.response.ErrorResponse;
import az.ingress.msannouncementproject.dto.response.GeneralErrorResponse;
import az.ingress.msannouncementproject.dto.response.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GeneralErrorResponse notFoundExceptionHandler(NotFoundException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss")))
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getContextPath() + request.getServletPath())
                .build();
        return GeneralErrorResponse.builder()
                .errorResponse(errorResponse)
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public GeneralErrorResponse notLoggedInExceptionHandler(AlreadyExistException exception, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss")))
                .status(HttpStatus.CONFLICT.value())
                .path(request.getContextPath() + request.getServletPath())
                .build();
        return GeneralErrorResponse.builder()
                .errorResponse(errorResponse)
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse notLoggedInExceptionHandler(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<ObjectError> allErrors = exception.getAllErrors();
        List<ValidationError> validationErrors = new ArrayList<>();

        for (ObjectError objectError : allErrors) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                validationErrors.add(ValidationError.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build());
            } else {
                validationErrors.add(ValidationError.builder()
                        .field(objectError.getObjectName())
                        .message(objectError.getDefaultMessage())
                        .build());
            }
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss")))
                .status(BAD_REQUEST.value())
                .path(request.getContextPath() + request.getServletPath())
                .build();

        return ValidationErrorResponse.builder()
                .errorResponse(errorResponse)
                .validationErrors(validationErrors)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public GeneralErrorResponse exceptionHandler(Exception exception, HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss")))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(request.getContextPath() + request.getServletPath())
                .build();
        return GeneralErrorResponse.builder()
                .errorResponse(errorResponse)
                .message(exception.getMessage())
                .build();
    }
}