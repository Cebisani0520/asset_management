package za.co.department.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import za.co.department.avro.DepartmentEventAvro;
import za.co.department.config.KafkaProps;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentListenerEvent {

    @KafkaListener(topics = "department-events", groupId = "department-event-group-1")
    public void departmentListenerEvent(ConsumerRecord<String, DepartmentEventAvro> consumerRecord) {

        try {
            String key = consumerRecord.key();
            DepartmentEventAvro departmentEventAvro = consumerRecord.value();

            log.info("Avro message received for key: {} , \n value: {} and \n headers: {}",
                    key, departmentEventAvro.toString(), consumerRecord.headers());

        } catch (Exception e) {
            log.error("Error processing message with key {}: {}", consumerRecord.key(), e.getMessage(), e);
        }
    }
}
