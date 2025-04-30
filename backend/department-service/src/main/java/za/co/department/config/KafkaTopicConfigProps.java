package za.co.department.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.topic")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaTopicConfigProps {

    private String departmentEvents;

}
