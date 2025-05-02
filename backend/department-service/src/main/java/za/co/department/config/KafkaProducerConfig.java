package za.co.department.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import za.co.common.events.DepartmentEvent;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {

    private final KafkaProps kafkaProps;
    private final ObjectMapper objectMapper;

    @Bean
    public ProducerFactory<String, DepartmentEvent> producerFactory () {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",", kafkaProps.getBootstrapServers()));
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        JsonSerializer<DepartmentEvent> eventJsonSerializer = new JsonSerializer<>(objectMapper);

        return new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(), eventJsonSerializer);

    }

    @Bean
    public KafkaTemplate<String, DepartmentEvent> kafkaTemplate () {
        return new KafkaTemplate<>(producerFactory());
    }
}
