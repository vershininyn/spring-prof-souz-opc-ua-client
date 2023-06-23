package dev.projects.profsouz.opcuaclient.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        produces = "application/json",
        value = "/opc-ua-ws-api")
public class OpcUaWebSocketController {
    @Tag(name = "", description = "")
    @PostMapping(value = "/connect")
    public ResponseEntity<HttpStatus> connectToASNeGServer() {
        return ResponseEntity.ok().build();
    }

    @Tag(name = "", description = "")
    @PostMapping(value = "/disconnect")
    public ResponseEntity<HttpStatus> disconnectFromASNeGServer() {
        return null;
    }

}
