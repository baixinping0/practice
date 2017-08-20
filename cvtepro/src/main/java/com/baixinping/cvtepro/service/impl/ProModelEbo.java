package com.baixinping.cvtepro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baixinping.cvtepro.dao.ProModelDao;
import com.baixinping.cvtepro.entity.ProModel;
import com.baixinping.cvtepro.service.ProModelEbi;
import org.springframework.stereotype.Service;

@Service
public class ProModelEbo implements ProModelEbi {
	@Resource
	ProModelDao proModelDao;
	@Override
	public ProModel getByName(String model) {
		return proModelDao.getByName(model);
	}
	@Override
	public void insert(ProModel proModel) {
		proModelDao.insert(proModel);
	}
	@Override
	public List<ProModel> find() {
		return proModelDao.list();
	}
	@Override
	public ProModel get(String id) {
		return proModelDao.get(id);
	}
	@Override
	public void update(ProModel proModel) {
		proModelDao.update(proModel);
	}
}
