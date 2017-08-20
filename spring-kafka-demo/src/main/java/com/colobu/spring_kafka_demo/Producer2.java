package com.colobu.spring_kafka_demo;

import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageChannel;

public class Producer2 {
	private static final String CONFIG = "/context2.xml";
	private static Random rand = new Random();

	public static void main(String[] args) {
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(CONFIG, Producer2.class);
		ctx.start();
//		final MessageChannel channel = ctx.getBean("inputToKafka", MessageChannel.class);
		KafkaTemplate<String, String> t = ctx.getBean("kafkaTemplate", KafkaTemplate.class);
		for (int i = 0; i < 100; i++) {
//			channel.send(MessageBuilder.withPayload("Message-" + rand.nextInt()).setHeader("messageKey", String.valueOf(i)).setHeader("topic", "test").build());
//			channel.send("", "");
			t.send("test", "发送了消息");
		}
		
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ctx.close();
	}
}
