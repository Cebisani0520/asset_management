package za.co.department.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import za.co.department.avro.DepartmentEventAvro;
import za.co.department.config.KafkaProps;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentProducerService {

    private final KafkaTemplate<String, DepartmentEventAvro> kafkaTemplate;
    private final KafkaProps kafkaProps;

    public void publish (DepartmentEventAvro departmentEvent) {

        String departmentEventsTopic = kafkaProps.getTopic().getDepartmentEvents();

        ProducerRecord<String, DepartmentEventAvro> record = new ProducerRecord<>(departmentEventsTopic, departmentEvent.getEventId(), departmentEvent);

        Map<String, String> customHeaders = Map.of(
                "source", "department-service",
                "traceId", "Sleuth tracing TBA",
                "contentType", "avro/binary",
                "schema", "v1"
        );

        customHeaders.forEach((key, value) ->
                record.headers().add(key, value.getBytes(StandardCharsets.UTF_8))
        );

        kafkaTemplate.send(record);

        log.info("Successfully published DepartmentEvent to kafka topic: {}. \n Content: {} \n Headers: {}", departmentEventsTopic, departmentEvent, customHeaders);

        //Callback fun
//        kafkaTemplate.send(record).addCallback(
//                result -> log.info("Message sent successfully to topic {} with offset {}", departmentEventsTopic, result.getRecordMetadata().offset()),
//                ex -> log.error("Failed to send message to topic {}: {}", departmentEventsTopic, ex.getMessage(), ex)
//        );


    }

}