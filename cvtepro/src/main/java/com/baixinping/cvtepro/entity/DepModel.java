package com.baixinping.cvtepro.entity;

import java.io.Serializable;


public class DepModel extends BaseModel implements Serializable{
//	private String dept_code;
	private String dept_name;
	private String charger1;
	private String charger2;
	private String parent_code;

	
	
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getCharger1() {
		return charger1;
	}
	public void setCharger1(String charger1) {
		this.charger1 = charger1;
	}
	public String getCharger2() {
		return charger2;
	}
	public void setCharger2(String charger2) {
		this.charger2 = charger2;
	}
	public String getParent_code() {
		return parent_code;
	}
	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DepModel depModel = (DepModel) o;
		return getId() != null ? getId().equals(depModel.getId()) : depModel.getId() == null;
	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		return result;
	}
}
