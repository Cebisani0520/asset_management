package za.co.department.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic departmentEventsTopic(final KafkaTopicConfigProps kafkaTopicConfigProps) {
        return TopicBuilder.name(kafkaTopicConfigProps.getDepartmentEvents())
                .partitions(3)
                .replicas(1)
                .build();
    }
}
