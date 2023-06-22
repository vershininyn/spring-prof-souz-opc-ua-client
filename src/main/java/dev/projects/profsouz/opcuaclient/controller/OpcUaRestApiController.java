package dev.projects.profsouz.opcuaclient.controller;

import dev.projects.profsouz.opcuaclient.service.OpcUaFileSystemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        produces = "application/json",
        value = "/opc-ua-api")
public class OpcUaRestApiController {
    //@Autowired
    //private OpcUaWebSocketService webSocketService;

    private final OpcUaFileSystemService fsService;

    @Autowired
    public OpcUaRestApiController(OpcUaFileSystemService fsService) {
        this.fsService = fsService;
    }

    @Tag(name = "", description = "")
    @PostMapping(value = "/connect")
    public ResponseEntity<HttpStatus> connectToASNeGServer() {
        if (!fsService.xmlFileIsExists() && !fsService.createXmlFile()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.ordinal()).build();
        }

        return ResponseEntity.ok().build();
    }

    @Tag(name = "", description = "")
    @PostMapping(value = "/disconnect")
    public ResponseEntity<HttpStatus> disconnectFromASNeGServer() {
        return null;
    }

    @Tag(name = "", description = "")
    @GetMapping(value = "/getValuesInfo")
    public ResponseEntity<HttpStatus> getValuesInfo() {
        return null;
    }
}
