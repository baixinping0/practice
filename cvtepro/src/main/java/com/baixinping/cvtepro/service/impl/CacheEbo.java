package com.baixinping.cvtepro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baixinping.cvtepro.common.utils.num.UUIDUtils;
import com.baixinping.cvtepro.dao.CacheDao;
import com.baixinping.cvtepro.entity.CacheModel;
import com.baixinping.cvtepro.service.CacheEbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CacheEbo implements CacheEbi {
	@Autowired
	CacheDao cacheDao;

	@Override
	public void insert(CacheModel cacheModel) {
		cacheModel.setId(UUIDUtils.getUuid());
		cacheDao.insert(cacheModel);
	}

	@Override
	public List<CacheModel> find() {
		return cacheDao.list();
	}

	@Override
	public CacheModel getById(String id) {
		return cacheDao.get(id);
	}

	@Override
	public void update(CacheModel cacheModel) {
		cacheDao.update(cacheModel);
	}

	@Override
	public CacheModel getByUrl(String methodKey) {
		return cacheDao.getByUrl(methodKey);
	}
}
