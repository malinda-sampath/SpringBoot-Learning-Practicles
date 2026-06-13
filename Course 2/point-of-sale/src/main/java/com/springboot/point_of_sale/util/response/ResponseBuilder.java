package com.springboot.point_of_sale.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseBuilder {

    private ResponseBuilder() {
    }

    // 200
    public static <T> ResponseEntity<StandardResponse<T>> ok(String message, T data) {
        return build(HttpStatus.OK, message, data);
    }

    // 201
    public static <T> ResponseEntity<StandardResponse<T>> created(String message, T data) {
        return build(HttpStatus.CREATED, message, data);
    }

    // 204
    public static ResponseEntity<Void> noContent() {
        return ResponseEntity.noContent().build();
    }

    // ===== ERROR RESPONSES =====

    public static <T> ResponseEntity<StandardResponse<T>> notFound(String message) {
        return build(HttpStatus.NOT_FOUND, message, null);
    }

    public static <T> ResponseEntity<StandardResponse<T>> error(
            HttpStatus status,
            String message,
            T data
    ) {
        if (status.is2xxSuccessful()) {
            throw new IllegalArgumentException("Error status must be 4xx or 5xx");
        }
        return build(status, message, data);
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