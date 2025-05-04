package za.co.department.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.kafka")
@Data
public class KafkaProps {

    private List<String> bootstrapServers;
    private Topic topic = new Topic();
    private String schemaRegistryUrl;

    @Data
    public static class Topic {
        private String departmentEvents;
    }
}
