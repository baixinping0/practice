package com.baixinping.cvtepro.entity;

import java.util.HashMap;
import java.util.Map;


public class LogissendModel extends BaseModel {
	private String logistics_code;
	private String logissend_status;
	
	private String logissend_statusView;
	
	public Map<String, String> logissend_statusMap = new HashMap<String, String>();
	
	public static final String LOGIS_SEND_STATATUS_SUCCEED = "1";
	public static final String LOGIS_SEND_STATATUS_FAILURE = "0";
	public static final String LOGIS_SEND_STATATUS_SUCCEED_VIEW = "成功";
	public static final String LOGIS_SEND_STATATUS_FAILURE_VIEW = "失败";
	
	
	
	public String getLogissend_statusView() {
		return logissend_statusView;
	}
	public String getLogistics_code() {
		return logistics_code;
	}
	public void setLogistics_code(String logistics_code) {
		this.logistics_code = logistics_code;
	}
	public String getLogissend_status() {
		return logissend_status;
	}
	public void setLogissend_status(String logissend_status) {
		this.logissend_status = logissend_status;
		this.logissend_statusView = logissend_statusMap.get(logissend_status);
	}
}
