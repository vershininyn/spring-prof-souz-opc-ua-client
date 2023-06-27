package dev.projects.profsouz.opcuaclient.controller;

import dev.projects.profsouz.opcuaclient.domain.response.OpcUaErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.util.Date;

@ControllerAdvice
public class OpcUaUniversalControllerAdvice {
    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<OpcUaErrorMessage> handleIOException(RuntimeException ex, WebRequest request) {
        OpcUaErrorMessage message = OpcUaErrorMessage.builder()
                .message(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(new Date())
                .build();

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
