package com.baixinping.cvtepro.entity;


import lombok.ToString;

@ToString
public class EmpModel extends BaseModel{
	//用户登录成功之后在session中保存用户信息的键值
	public static final String EMP_LOGIN_USER_OBJECT_NAME = "EMP_LOGIN_USER_OBJECT_NAME";
	//用户登录时候回去用户所拥有权限的部门信息在session中存储的键值，查询的过程中将会改变
	public static final String EMP_DEP_LIST = "EMP_DEP_LIST";
	//用户进行查询部门信息操作的时候，用次键值保存用户原始拥有的部门信息不改变，每次查询以此为元数据进行查询
	public static final String EMP_DEP_LIST_SEARCH = "EMP_DEP_LIST_SEARCH";
	//用户在进行查询部门信息时候用此键值存储当前用户是有条件查询还是无条件查询,true代表查询操作
	public static final String EMP_DEP_LIST_OPER = "EMP_DEP_LIST_PRO";
	
	private String user_pwd;
	private String user_name;
	private String user_account;
	private String dept_code;
	
	
	
	public String getDept_code() {
		return dept_code;
	}
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_account() {
		return user_account;
	}
	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}
	
	public static String getEmpLoginUserObjectName() {
		return EMP_LOGIN_USER_OBJECT_NAME;
	}
}
