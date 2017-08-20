package com.baixinping.cvtepro.service.impl;

import javax.annotation.Resource;

import com.baixinping.cvtepro.dao.EmpDao;
import com.baixinping.cvtepro.entity.EmpModel;
import com.baixinping.cvtepro.service.EmpEbi;
import org.springframework.stereotype.Service;

@Service
public class EmpEbo implements EmpEbi {
	
	@Resource
	EmpDao empDao;

	@Override
	public EmpModel getEmpByUserNameAndUserPawd(String user_name, String user_pwd) {
		return empDao.getEmpByUserNameAndUserPawd(user_name, user_pwd);
	}
}
