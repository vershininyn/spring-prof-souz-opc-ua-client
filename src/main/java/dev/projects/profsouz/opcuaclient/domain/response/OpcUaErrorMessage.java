package dev.projects.profsouz.opcuaclient.domain.response;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OpcUaErrorMessage {
    private int statusCode;
    private String message;
    private Date timestamp;
}
