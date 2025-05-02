package za.co.common.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import za.co.common.enums.EventTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DepartmentEvent extends BaseEvent {
    private Long departmentId;
    private String name;
    private String description;
    private EventTypes eventType;

    public DepartmentEvent(EventTypes type, Long departmentId, String name, String description) {
        super(UUID.randomUUID(), "DEPARTMENT_EVENT", LocalDateTime.now());
        this.eventType = type;
        this.departmentId = departmentId;
        this.name = name;
        this.description = description;
    }
}
