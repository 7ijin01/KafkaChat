package org.example.kafkachat.chat.config;

import com.mongodb.annotations.Beta;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig
{
    @Bean
    public NewTopic chatTopic()
    {
        return TopicBuilder.name("chat-messages")
                .partitions(3)
                .replicas(2)
                .build();
    }
}
