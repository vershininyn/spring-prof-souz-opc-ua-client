package dev.projects.profsouz.opcuaclient.service;

import dev.projects.profsouz.opcuaclient.domain.XmlFilepathDTO;
import dev.projects.profsouz.opcuaclient.entity.OpcUaXmlFilepathEntity;
import dev.projects.profsouz.opcuaclient.repository.OpcUaFileSystemJpaRepository;
import dev.projects.profsouz.opcuaclient.utils.OpcUaFileSystemObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class OpcUaFileSystemService {
    private OpcUaFileSystemJpaRepository uuidXmlFilepathsRepository;

    @Autowired
    public OpcUaFileSystemService(OpcUaFileSystemJpaRepository repository) {
        uuidXmlFilepathsRepository = repository;
    }

    public XmlFilepathDTO createXmlFile(String xmlPath) throws IOException {
        Path xmlFilepath = Path.of(xmlPath);

        XmlFilepathDTO result = OpcUaFileSystemObjectMapper.mapFromStringToXmlFilepathDTO(xmlPath);

        if (!Files.exists(xmlFilepath)) {
            Files.createFile(xmlFilepath);

            OpcUaXmlFilepathEntity entity = OpcUaXmlFilepathEntity.builder()
                    .xmlUUID(result.getXmlUUID())
                    .xmlFilepath(result.getXmlFilepath())
                    .xmlFilename(result.getXmlFilename())
                    .build();

            uuidXmlFilepathsRepository.save(entity);
        }

        return result;
    }

    public XmlFilepathDTO deleteXmlFile(UUID xmlFileUUID) throws IOException {
        OpcUaXmlFilepathEntity entity = uuidXmlFilepathsRepository.findByUUID(xmlFileUUID);

        //TODO: is Optional?

        XmlFilepathDTO result = XmlFilepathDTO.builder()
                .xmlUUID(entity.getXmlUUID())
                .xmlFilepath(entity.getXmlFilepath())
                .xmlFilename(entity.getXmlFilename())
                .isExists(true)
                .build();

        Path xmlPath = Path.of(result.getXmlFilepath());

        if (Files.exists(xmlPath)) {
            Files.delete(xmlPath);

            uuidXmlFilepathsRepository.delete(entity);

            result.setIsExists(false);
        }

        return result;
    }
}
