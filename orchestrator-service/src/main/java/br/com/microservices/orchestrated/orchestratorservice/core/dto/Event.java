package br.com.microservices.orchestrated.orchestratorservice.core.dto;
import java.time.LocalDateTime;
import java.util.List;

import br.com.microservices.orchestrated.orchestratorservice.core.enums.EEventSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private String id;
    private String transactionId;
    private String orderId;
    private Order payload; 
    private String status;
    private EEventSource source;
    private List<History> history;
    private LocalDateTime createdAt;
}
