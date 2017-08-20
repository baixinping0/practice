package com.baixinping.cvtepro.service;

import com.baixinping.cvtepro.entity.DepModel;
import com.baixinping.cvtepro.entity.EmpModel;
import com.baixinping.cvtepro.entity.MenuModel;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;



public interface DepEbi {

	public List<DepModel> list();
	public DepModel get(Serializable id);
	public void insert(DepModel entity);
	public void update(DepModel entity);
	public void deleteById(Serializable id);
	public void delete(Serializable[] ids);
	public List<DepModel> listByCharger(String user_code);
	public List<DepModel> listAuthDep(String user_code);
	public List<DepModel> listDepByEmp(EmpModel empModel);
	public List<MenuModel> getRootMenu(HttpSession session, String dept_name);
	public List<MenuModel> getLeavel2Menu(HttpSession session, String parent_id);
}
