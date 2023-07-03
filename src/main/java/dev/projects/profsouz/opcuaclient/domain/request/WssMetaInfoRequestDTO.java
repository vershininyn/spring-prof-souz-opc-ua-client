package dev.projects.profsouz.opcuaclient.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WssMetaInfoRequestDTO {
    private String wsHost, wsPort;
}
