package com.baixinping.cvtepro.service;

import com.baixinping.cvtepro.entity.ProModel;

import java.util.List;



public interface ProModelEbi{

	public ProModel getByName(String model);

	public void insert(ProModel proModel);

	public List<ProModel> find();

	public ProModel get(String id);

	public void update(ProModel proModel);

}
