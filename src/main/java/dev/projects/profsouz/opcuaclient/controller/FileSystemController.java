package dev.projects.profsouz.opcuaclient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.projects.profsouz.opcuaclient.domain.XmlFilepathDTO;
import dev.projects.profsouz.opcuaclient.service.OpcUaFileSystemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(
        produces = "application/json",
        value = "/opc-ua-fs-api")
public class FileSystemController {

    @Autowired
    private OpcUaFileSystemService fsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Tag(name = "It's the create xml file endpoint", description = "Create xml file if it is needed")
    @PostMapping(value = "/createXmlFile")
    public ResponseEntity<XmlFilepathDTO> createTemplateXmlFile(@RequestBody String filepathToTemplateXml) {
        XmlFilepathDTO xmlFilepathDTO = fsService.createXmlFile(filepathToTemplateXml);

        //TODO: tomorrow it's will be finished

        return xmlFilepathDTO.getIsExists()
                ? (ResponseEntity.created(URI.create("/"+xmlFilepathDTO.getXmlUUID())).body(xmlFilepathDTO))
                : (ResponseEntity.internalServerError().build());
    }

    @Tag(name = "It's the delete xml file endpoint", description = "Delete xml file if it is exists")
    @PutMapping(value = "/deleteXmlFile")
    public ResponseEntity<XmlFilepathDTO> deleteTemplateXmlFile(@RequestBody String filepathToTemplateXml) {
        XmlFilepathDTO xmlFilepathDTO = fsService.deleteXmlFile(filepathToTemplateXml);

        //TODO: tomorrow it's will be finished

        return xmlFilepathDTO.getIsExists()
                ? (ResponseEntity.created(URI.create("/"+xmlFilepathDTO.getXmlUUID())).body(xmlFilepathDTO))
                : (ResponseEntity.internalServerError().build());
    }

}
