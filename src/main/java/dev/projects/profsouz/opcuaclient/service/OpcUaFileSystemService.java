package dev.projects.profsouz.opcuaclient.service;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OpcUaFileSystemService {
    @Pattern(regexp = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?",
            message = "The xml filepath must be like pattern 'some/path/to/file.xml'")
    @Value(value = "opc-ua-spring-client.xml-filepath")
    private String xmlFilepath;

    public boolean createXmlFile() {
        try {
            return Files.createFile(Path.of(xmlFilepath)).toFile().exists();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteXmlFile() {
        try {
            return Files.deleteIfExists(Path.of(xmlFilepath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean xmlFileIsExists() {
        return Files.exists(Path.of(xmlFilepath));
    }
}
