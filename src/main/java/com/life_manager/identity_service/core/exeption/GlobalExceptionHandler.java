package com.life_manager.identity_service.core.exeption;

import com.life_manager.identity_service.core.dto.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Validation error - 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(
            MethodArgumentNotValidException exception
    ) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);

        } catch (IllegalArgumentException e) {

        }

        ApiResponse<Object> response = new ApiResponse<>();
        response.setCode(errorCode.getCode());
        response.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<Object>> handleAppException(
            AppException exception
    ) {
        ErrorCode errorResponse = exception.getErrorCode();
        ApiResponse<Object> response = new ApiResponse<>();
        response.setCode(errorResponse.getCode());
        response.setMessage(errorResponse.getMessage());

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
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException() {
        ApiResponse<Object> response = new ApiResponse<>();
        response.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        response.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.internalServerError().body(response);
    }

//    // ===== VALIDATION (PARAM / PATH) =====
//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<ApiResponse<Void>> handleConstraintViolation(
//            ConstraintViolationException ex) {
//
//        ConstraintViolation<?> violation =
//                ex.getConstraintViolations().stream().findFirst().orElse(null);
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(
//                        ApiResponse.<Void>builder()
//                                .timestamp(Instant.now())
//                                .error(
//                                        ApiError.builder()
//                                                .code(ErrorCode.VALIDATION_ERROR.getCode())
//                                                .message(ErrorCode.VALIDATION_ERROR.getMessage())
//                                                .details(
//                                                        violation != null
//                                                                ? Details.builder()
//                                                                .field(
//                                                                        violation
//                                                                                .getPropertyPath()
//                                                                                .toString())
//                                                                .reason(
//                                                                        violation
//                                                                                .getMessage())
//                                                                .build()
//                                                                : null)
//                                                .build())
//                                .build());
//    }
}
