package com.baixinping.cvtepro.common.queue.producer;


public interface  Producer {
	public  void send(String topic, String data, CallBack callBack);
	public  void send(String topic, String data);
	interface CallBack{
		public void succeed(Object object);
		public void fail(Throwable throwable);
	}
}
