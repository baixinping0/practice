package com.baixinping.cvtepro.service;

import com.baixinping.cvtepro.entity.CacheModel;

import java.util.List;


public interface CacheEbi {

	public void insert(CacheModel cacheModel);

	public List<CacheModel> find();

	public CacheModel getById(String id);

	public void update(CacheModel cacheModel);

	public CacheModel getByUrl(String methodKey);

}
