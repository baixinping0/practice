package com.baixinping.cvtepro.entity;

import com.baixinping.cvtepro.common.utils.format.FormatUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CacheModel extends BaseModel{
	
	private String model_code;
	private String cache_info_id;
	private String cache_url;
	private Date cache_update_time;
	
	//试图值
	private String cache_update_timeView;
	
	public String getModel_code() {
		return model_code;
	}
	public void setModel_code(String model_code) {
		this.model_code = model_code;
	}
	public String getCache_update_timeView() {
		return cache_update_timeView;
	}
	public String getCache_info_id() {
		return cache_info_id;
	}
	public void setCache_info_id(String cache_info_id) {
		this.cache_info_id = cache_info_id;
	}
	public String getCache_url() {
		return cache_url;
	}
	public void setCache_url(String cache_url) {
		this.cache_url = cache_url;
	}
	public Date getCache_update_time() {
		return cache_update_time;
	}
	public void setCache_update_time(Date cache_update_time) {
		this.cache_update_time = cache_update_time;
		this.cache_update_timeView = FormatUtil.formatDateTime(cache_update_time.getTime());
	}
	
	
}
