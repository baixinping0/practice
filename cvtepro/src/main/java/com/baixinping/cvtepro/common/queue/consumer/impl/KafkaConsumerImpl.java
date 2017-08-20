package com.baixinping.cvtepro.common.queue.consumer.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.baixinping.cvtepro.common.queue.consumer.Consumer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumerImpl implements Consumer {
	private QueueChannel channel;
	@Override
	public void recive() {
			Message msg;		
			while((msg = channel.receive()) != null) {
				
				HashMap map = (HashMap)msg.getPayload();
				Set<Map.Entry> set = map.entrySet();
				for (Map.Entry entry : set) {
					String topic = (String)entry.getKey();
					System.out.println("topic   " + topic);
					ConcurrentHashMap<Integer,List<byte[]>> messages = (ConcurrentHashMap<Integer,List<byte[]>>)entry.getValue();
					Collection<List<byte[]>> values = messages.values();
					for (Iterator<List<byte[]>> iterator = values.iterator(); iterator.hasNext();) {
						List<byte[]> list = iterator.next();
						for (byte[] object : list) {
							String message = new String(object);
							System.out.println("\tMessage: " + message);
						}
					}
				}
			}
		}
	public void setChannel(QueueChannel channel) {
		this.channel = channel;
	}
	
}
