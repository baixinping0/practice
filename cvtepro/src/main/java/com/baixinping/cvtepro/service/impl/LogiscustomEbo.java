package com.baixinping.cvtepro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baixinping.cvtepro.dao.LogiscustomDao;
import com.baixinping.cvtepro.entity.LogiscustomModel;
import com.baixinping.cvtepro.service.LogiscustomEbi;
import org.springframework.stereotype.Service;


@Service
public class LogiscustomEbo implements LogiscustomEbi {
	@Resource
	LogiscustomDao logiscustomDao;

	@Override
	public List<LogiscustomModel> find() {
		return logiscustomDao.list();
	}
}
