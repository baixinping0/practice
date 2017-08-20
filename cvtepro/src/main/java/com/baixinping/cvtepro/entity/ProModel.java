package com.baixinping.cvtepro.entity;

import java.util.HashMap;
import java.util.Map;

public class ProModel extends BaseModel{
	public static final String EMP = "EMP";
	public static final String DEPT = "DEPT";
	public static final String EMP_DEPT_RTEE = "EMP_DEPT_TREE";
	
	private String model_code;
	private String model_name;
	private String cache_status;
	
	private String cache_statusView;
	public static final String CACHE_STATUS_OPEN = "1";
	public static final String CACHE_STATUS_CLOSE = "0";
	public static final String CACHE_STATUS_VIEW_CLOSE = "关闭";
	public static final String CACHE_STATUS_VIEW_OPEN = "开启";
	public static Map<String, String> statusMap = new HashMap<String, String>();
	static{
		statusMap.put(CACHE_STATUS_OPEN, CACHE_STATUS_VIEW_OPEN);
		statusMap.put(CACHE_STATUS_CLOSE, CACHE_STATUS_VIEW_CLOSE);
	}
	
	
	public String getCache_statusView() {
		return cache_statusView;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getModel_code() {
		return model_code;
	}
	public void setModel_code(String model_code) {
		this.model_code = model_code;
	}
	public String getCache_status() {
		return cache_status;
	}
	public void setCache_status(String cache_status) {
		this.cache_status = cache_status;
		this.cache_statusView = statusMap.get(cache_status);
	}
}
