package com.baixinping.cvtepro.common.config;

import com.baixinping.cvtepro.common.queue.producer.Producer;
import com.baixinping.cvtepro.common.queue.producer.impl.KafkaProducerImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Value("${queueClass}")
    String queueClass  ;
    @Bean
    public Producer getProducer() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Producer producer = new KafkaProducerImpl();
        if (queueClass != null)
               producer = (Producer) Class.forName(queueClass).newInstance();
        return producer;
    }
}
