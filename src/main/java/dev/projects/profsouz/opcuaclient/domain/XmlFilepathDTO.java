package dev.projects.profsouz.opcuaclient.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class XmlFilepathDTO {
    private String xmlFilename;
    private String xmlFilepath;
    private UUID xmlUUID;
    private Boolean isExists;
}
