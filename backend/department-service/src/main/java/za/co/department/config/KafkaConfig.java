package za.co.department.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaConfig {
    @Value("${kafka.topic.department-events}")
    private String departmentEventsTopic;

    @Bean
    public NewTopic departmentEventsTopic() {
        return TopicBuilder.name(departmentEventsTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
