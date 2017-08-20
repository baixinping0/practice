package com.baixinping.cvtepro.common.queue.producer.impl;

import javax.annotation.Resource;

import com.baixinping.cvtepro.common.queue.producer.AbstractProducer;
import com.baixinping.cvtepro.common.queue.producer.Producer;
import com.baixinping.cvtepro.common.utils.num.UUIDUtils;
import com.baixinping.cvtepro.entity.LogissendModel;
import com.baixinping.cvtepro.entity.LogisticsModel;
import com.baixinping.cvtepro.service.LogissendEbi;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import com.alibaba.fastjson.JSON;

public class KafkaProducerImpl extends AbstractProducer {
	@Resource
	private KafkaTemplate<String,String> kafkaTemplate;

	@Override
	public void send(String topic, final String data, CallBack callBack) {
		ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, data);
		send.addCallback(new SuccessCallback<Object>() {
			@Override
			public void onSuccess(Object arg0) {
				if (callBack != null)
					callBack.succeed(arg0);
			}
		}, new FailureCallback() {
			@Override
			public void onFailure(Throwable arg0) {
				if (callBack != null)
					callBack.fail(arg0);
			}
		});
	}
	public void setKafkaTemplate(KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
}
