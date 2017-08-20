package com.baixinping.cvtepro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.baixinping.cvtepro.common.queue.producer.Producer;
import com.baixinping.cvtepro.common.utils.num.UUIDUtils;
import com.baixinping.cvtepro.dao.LogiscustomDao;
import com.baixinping.cvtepro.dao.LogisticsDao;
import com.baixinping.cvtepro.entity.LogiscustomModel;
import com.baixinping.cvtepro.entity.LogissendModel;
import com.baixinping.cvtepro.entity.LogisticsModel;
import com.baixinping.cvtepro.service.LogissendEbi;
import com.baixinping.cvtepro.service.LogisticsEbi;
import org.springframework.stereotype.Service;


@Service
public class LogisticsEbo implements LogisticsEbi {
	@Resource
	LogisticsDao logisticsDao;
	@Resource
	LogiscustomDao logiscustomDao;
	@Resource
	LogissendEbi logissendEbi;
	@Resource
	Producer producer;

	@Override
	public List<LogisticsModel> find() {
		return logisticsDao.list();
	}

	@Override
	public LogisticsModel get(String id) {
		return logisticsDao.get(id);
	}

	@Override
	public void send(String logisticsId, String logiscustomId) {
		LogisticsModel logisticsModel = get(logisticsId.trim());
		LogiscustomModel logiscustomModel = logiscustomDao.get(logiscustomId.trim());
		//执行订单发送流程
		String topic = logiscustomModel.getLogiscustom_topic();
		String data = JSON.toJSONString(logisticsModel);
		producer.send(topic, data, new Producer.CallBack() {
			@Override
			public void succeed(Object object) {
				//将发送失败的订单保存在数据库中
				LogisticsModel logisticsModel = JSON.parseObject(data, LogisticsModel.class);
				LogissendModel sendmodel = logissendEbi.getBylogisticsId(logisticsModel.getId());
				if(sendmodel == null){
					sendmodel = new LogissendModel();
					sendmodel.setId(UUIDUtils.getUuid());
					sendmodel.setLogistics_code(logisticsModel.getId());
					sendmodel.setLogissend_status(LogissendModel.LOGIS_SEND_STATATUS_SUCCEED);
					logissendEbi.insert(sendmodel);
				}else{
					sendmodel.setLogissend_status(LogissendModel.LOGIS_SEND_STATATUS_SUCCEED);
					logissendEbi.update(sendmodel);
				}
			}

			@Override
			public void fail(Throwable throwable) {
				//将发送失败的订单保存在数据库中
				LogisticsModel logisticsModel = JSON.parseObject(data, LogisticsModel.class);
				LogissendModel sendmodel = logissendEbi.getBylogisticsId(logisticsModel.getId());
				if(sendmodel == null){
					sendmodel = new LogissendModel();
					sendmodel.setId(UUIDUtils.getUuid());
					sendmodel.setLogistics_code(logisticsModel.getId());
					sendmodel.setLogissend_status(LogissendModel.LOGIS_SEND_STATATUS_FAILURE);
					logissendEbi.insert(sendmodel);
				}
			}
		});
	}
}
