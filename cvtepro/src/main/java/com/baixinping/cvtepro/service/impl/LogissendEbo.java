package com.baixinping.cvtepro.service.impl;

import javax.annotation.Resource;

import com.baixinping.cvtepro.dao.LogissendDao;
import com.baixinping.cvtepro.entity.LogissendModel;
import com.baixinping.cvtepro.service.LogissendEbi;
import org.springframework.stereotype.Service;


@Service
public class LogissendEbo implements LogissendEbi {
	@Resource
	LogissendDao logissendDao;

	@Override
	public LogissendModel getBylogisticsId(String logisticsId) {
		
		return logissendDao.getBylogisticsId(logisticsId);
	}
	@Override
	public void update(LogissendModel sendmodel) {
		logissendDao.update(sendmodel);
	}

	@Override
	public void insert(LogissendModel sendmodel) {
		logissendDao.insert(sendmodel);
	}
}
