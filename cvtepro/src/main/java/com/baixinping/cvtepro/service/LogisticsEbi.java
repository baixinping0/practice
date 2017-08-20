package com.baixinping.cvtepro.service;

import com.baixinping.cvtepro.entity.LogisticsModel;

import java.util.List;


public interface LogisticsEbi {

	public List<LogisticsModel> find();

	public LogisticsModel get(String id);

	public void send(String logisticsId, String logiscustomId);

}
