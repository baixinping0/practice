package com.baixinping.cvtepro.dao;


import com.baixinping.cvtepro.entity.EmpModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EmpDao extends BaseDao<EmpModel>{
	/**
	 * 通过用户名和用户密码获取用户信息,用户登录操作
	 * @param user_name
	 * @param user_pwd
	 * @return
	 */
	public EmpModel getEmpByUserNameAndUserPawd(@Param("user_name")String user_name, @Param("user_pwd")String user_pwd);
}
