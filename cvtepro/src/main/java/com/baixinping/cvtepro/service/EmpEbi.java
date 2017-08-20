package com.baixinping.cvtepro.service;


import com.baixinping.cvtepro.entity.EmpModel;
import org.springframework.stereotype.Service;

public interface EmpEbi {
	/**
	 * 通过用户名和用户密码获取用户的信息
	 * @param user_name
	 * @param user_pwd
	 * @return
	 */
	public EmpModel getEmpByUserNameAndUserPawd(String user_name, String user_pwd);
}
