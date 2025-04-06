package za.co.common.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEvent {
    private UUID eventId = UUID.randomUUID();
    private String eventType;
    private LocalDateTime timestamp = LocalDateTime.now();

//    public BaseEvent(UUID eventId, String eventType, LocalDateTime timestamp) {
//        this.eventId = eventId;
//        this.eventType = eventType;
//        this.timestamp = timestamp;
//    }
}
