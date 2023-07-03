package dev.projects.profsouz.opcuaclient.domain.response;

import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class WssMetaInfoResponseDTO {
    private String wsHost, wsPort;
    private Boolean isConnected;
    private UUID connectionUUID;
}
