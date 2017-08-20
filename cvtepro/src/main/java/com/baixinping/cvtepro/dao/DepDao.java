package com.baixinping.cvtepro.dao;


import com.baixinping.cvtepro.entity.DepModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DepDao extends BaseDao<DepModel>{

	public List<DepModel> listByCharger(String user_code);

	public List<DepModel> listAuthDep(String user_code);

	/**
	 * 此方法是根据父菜单找到这个父菜单的直接子菜单
	 * @param dept_code
	 * @return
	 */
	public List<DepModel> listChildByParent(String dept_code);

	/**
	 * 此方法是通过父菜单找到这个父菜单的所有子菜单
	 * @param dept_code
	 * @return
	 */
	public List<DepModel> listAllChildByParent(String dept_code);

	public DepModel getByDepq(DepModel depq);


}
