package com.baixinping.cvtepro.service.impl;

import com.baixinping.cvtepro.common.utils.num.UUIDUtils;
import com.baixinping.cvtepro.dao.ArticleDao;
import com.baixinping.cvtepro.entity.ArticleModel;
import com.baixinping.cvtepro.service.ArticleEbi;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Service
public class ArticleEbo implements ArticleEbi {

	@Resource
	ArticleDao articleDao;

	@Override
	public List<ArticleModel> list() {
		return articleDao.list();
	}

	@Override
	public ArticleModel get(Serializable id) {
		return articleDao.get(id);
	}

	@Override
	public void insert(ArticleModel articleModel) { 
		articleModel.setId(UUIDUtils.getUuid());
		articleModel.setCreated_time(new Date());
		articleModel.setUpdated_time(new Date());
		articleModel.setIs_del(ArticleModel.IS_DEL_NO);
		articleDao.insert(articleModel);
	}

	@Override
	public void update(ArticleModel articleModel) {
		articleModel.setUpdated_time(new Date());
		articleDao.update(articleModel);
	}

	@Override
	public void deleteById(Serializable id) {
		articleDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		// TODO Auto-generated method stub
	}
}
