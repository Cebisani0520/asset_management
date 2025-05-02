package za.co.department.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.kafka")
@Getter
@Setter
public class KafkaProps {

    private List<String> bootstrapServers;
    private Topic topic = new Topic();

    @Getter
    @Setter
    public static class Topic {
        private String departmentEvents;
    }
}
