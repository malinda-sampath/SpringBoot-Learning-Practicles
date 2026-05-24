package com.microservice.employee_service.utill.response;

import com.microservice.employee_service.utill.response.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

    private ResponseBuilder(){}

    // 200
    public static <T> ResponseEntity<StandardResponse<T>> ok(String message, T data) {
        return build(HttpStatus.OK, message, data);
    }

    // 201
    public static <T> ResponseEntity<StandardResponse<T>> created(String message, T data) {
        return build(HttpStatus.CREATED, message, data);
    }

    // ===== CORE BUILDER =====

    private static <T> ResponseEntity<StandardResponse<T>> build(
            HttpStatus status,
            String message,
            T data
    ) {
        return ResponseEntity.status(status)
                .body(new StandardResponse<>(
                        status.value(),
                        message,
                        data
                ));
    }
}
