package com.baixinping.cvtepro.service;

import com.baixinping.cvtepro.entity.ArticleModel;

import java.io.Serializable;
import java.util.List;

public interface ArticleEbi{
	public List<ArticleModel> list();
	public ArticleModel get(Serializable id);
	public void insert(ArticleModel entity);
	public void update(ArticleModel entity);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
}
