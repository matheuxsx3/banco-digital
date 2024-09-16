package br.com.microservices.orchestrated.orchestratorservice.core.dto;
import java.time.LocalDateTime;

import br.com.microservices.orchestrated.orchestratorservice.core.enums.EEventSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private EEventSource source;
    private String status;
    private String message;
    private LocalDateTime createdAt;
}
