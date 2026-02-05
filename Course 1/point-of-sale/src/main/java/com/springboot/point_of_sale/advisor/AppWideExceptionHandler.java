package com.springboot.point_of_sale.advisor;

import com.springboot.point_of_sale.exception.NotFoundException;
import com.springboot.point_of_sale.util.response.ResponseBuilder;
import com.springboot.point_of_sale.util.response.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse<String>> handleNotFoundException(NotFoundException e) {
        return ResponseBuilder.notFound(e.getMessage());
    }
}
