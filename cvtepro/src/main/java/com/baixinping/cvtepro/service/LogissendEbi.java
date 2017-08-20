package com.baixinping.cvtepro.service;

import com.baixinping.cvtepro.entity.LogissendModel;

public interface LogissendEbi{

	public LogissendModel getBylogisticsId(String id);

	public void update(LogissendModel sendmodel);

	public void insert(LogissendModel sendmodel);

}
