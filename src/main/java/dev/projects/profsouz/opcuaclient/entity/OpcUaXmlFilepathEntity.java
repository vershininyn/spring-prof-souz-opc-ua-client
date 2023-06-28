package dev.projects.profsouz.opcuaclient.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "opc_ua_xml_filepath_table", schema = "public")
@Entity(name = "opc_ua_xml_filepath_entity")
public class OpcUaXmlFilepathEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opc-ua-xml-filepath-sequence-generator")
    @SequenceGenerator(name = "opc-ua-xml-filepath-sequence-generator", sequenceName = "opc_ua_xml_filepath_sequence", allocationSize = 1, initialValue = 1)*/
    private Long id;

    @Column(name = "xml_uuid", nullable = false, unique = true)
    @Setter
    private UUID xmlUUID;

    @Column(name = "xml_filepath", nullable = false)
    @Setter
    private String xmlFilepath;

    @Column(name = "xml_filename", nullable = false)
    @Setter
    private String xmlFilename;
}
