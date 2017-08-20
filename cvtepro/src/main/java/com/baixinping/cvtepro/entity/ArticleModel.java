package com.baixinping.cvtepro.entity;

import com.baixinping.cvtepro.common.utils.format.FormatUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ArticleModel extends BaseModel{
//	private String id;
	private String title;
	private String content;
	private Date created_time;
	private Date updated_time;
	private Character is_del;
	
	private String created_timeView;
	private String updated_timeView;
	private String is_delView;
	
	public static Map<Character, String> IS_DEL_MAP = new HashMap<>();
	public static Character IS_DEL_NO = '0';
	public static Character IS_DEL_YES = '1';
	public static String IS_DEL_NO_VIEW = "否";
	public static String IS_DEL_YES_VIEW = "是";
	static{
		IS_DEL_MAP.put(IS_DEL_NO, IS_DEL_NO_VIEW);
		IS_DEL_MAP.put(IS_DEL_YES, IS_DEL_YES_VIEW);
	}
	
	
	public String getIs_delView() {
		return is_delView;
	}
	public String getCreated_timeView() {
		return created_timeView;
	}
	public String getUpdated_timeView() {
		return updated_timeView;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
		this.created_timeView = FormatUtil.formatDateTime(created_time.getTime());
	}
	public Date getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
		this.updated_timeView = FormatUtil.formatDateTime(updated_time.getTime());
	}
	public Character getIs_del() {
		return is_del;
	}
	public void setIs_del(Character is_del) {
		this.is_del = is_del;
	}
}
