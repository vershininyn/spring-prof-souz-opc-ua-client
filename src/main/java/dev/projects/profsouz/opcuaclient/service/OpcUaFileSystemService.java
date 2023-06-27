package dev.projects.profsouz.opcuaclient.service;

import dev.projects.profsouz.opcuaclient.domain.XmlFilepathDTO;
import dev.projects.profsouz.opcuaclient.utils.OpcUaFileSystemObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class OpcUaFileSystemService {
    public XmlFilepathDTO createXmlFile(String xmlPath) throws IOException {
        Path xmlFilepath = Path.of(xmlPath);

        if (!Files.exists(xmlFilepath)) {
            Files.createFile(xmlFilepath);
        }

        return OpcUaFileSystemObjectMapper.mapFromStringToXmlFilepathDTO(xmlPath);

    }

    public XmlFilepathDTO deleteXmlFile(String xmlPath) throws IOException {
        Path xmlFilepath = Path.of(xmlPath);

        if (Files.exists(xmlFilepath)) {
            Files.delete(xmlFilepath);
        }

        return OpcUaFileSystemObjectMapper.mapFromStringToXmlFilepathDTO(xmlPath);
    }
}
