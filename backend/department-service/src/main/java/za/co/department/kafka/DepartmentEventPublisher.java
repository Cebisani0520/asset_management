package za.co.department.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import za.co.common.events.DepartmentEvent;
import za.co.department.config.KafkaProps;

@Component
@RequiredArgsConstructor
@Slf4j
public class DepartmentEventPublisher {

    private final KafkaTemplate<String, DepartmentEvent> kafkaTemplate;

    private final KafkaProps kafkaProps;

    public void publish (DepartmentEvent departmentEvent) {
        String departmentEventsTopic = kafkaProps.getTopic().getDepartmentEvents();
        kafkaTemplate.send(departmentEventsTopic, departmentEvent.getDepartmentId().toString(), departmentEvent);
        log.info("Successfully published DepartmentEvent to kafka topic: {}. \n Content: {}", departmentEventsTopic, departmentEvent);
    }

}