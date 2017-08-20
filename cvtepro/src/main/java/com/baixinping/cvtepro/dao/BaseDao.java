package com.baixinping.cvtepro.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 泛型接口，基础的dao接口
 * @param <T>
 */
public interface BaseDao<T> {

	public List<T> list();

	public T get(Serializable id);

	public void insert(T entity);

	public void update(T entity);

	public void deleteById(Serializable id);
	//批量删除
	public void delete(Serializable[] ids);
}
