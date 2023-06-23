package dev.projects.profsouz.opcuaclient.utils;

import dev.projects.profsouz.opcuaclient.domain.XmlFilepathDTO;

import java.nio.file.Path;

public class OpcUaFileSystemObjectMapper {

    public static XmlFilepathDTO mapFromStringToXmlFilepathDTO(String xmlFilepath) {
        Path path = Path.of(xmlFilepath);

        return XmlFilepathDTO.builder()
                .xmlFilename(path.getFileName().toString())
                .xmlFilepath(path.toAbsolutePath().toString())
                .isExists(path.toFile().exists())
                .build();

    }


}
