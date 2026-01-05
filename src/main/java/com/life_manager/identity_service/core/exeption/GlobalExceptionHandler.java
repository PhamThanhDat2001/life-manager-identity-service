package com.life_manager.identity_service.core.exeption;

import com.life_manager.identity_service.presentation.dto.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Validation error - 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(
            MethodArgumentNotValidException exception
    ) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getFieldError().getDefaultMessage());

        return ResponseEntity.badRequest().body(response);
    }

    // Data integrity - 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolation(
            DataIntegrityViolationException exception
    ) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setCode(HttpStatus.CONFLICT.value());

        String message = "Dữ liệu không hợp lệ";
        Throwable cause = exception.getMostSpecificCause();
        if (cause != null && cause.getMessage().contains("users.username")) {
            message = "Username đã tồn tại";
        }
        response.setMessage(message);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // Fallback - 500
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(
            RuntimeException exception
    ) {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage(exception.getMessage());

        return ResponseEntity.internalServerError().body(response);
    }
}
